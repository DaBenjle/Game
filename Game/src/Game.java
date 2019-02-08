import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Game
{
	
	public static final int SIDE_LENGTH = 10, ADD_SIDE_LENGTH = 6;
	public static final int HALF = SIDE_LENGTH / 2, ADD_HALF = ADD_SIDE_LENGTH / 2;
	public static final int XLENGTH = 5, YLENGTH = 4;
	public static final int SPECIFIED_FRAME = 15;
	public static final int MOVE_TIME = 450;
	
	private MyScreen screen;
	private BufferedImage curFrame;
	private JFrame frame;
	private Player player;
	private int curFrameInt = 0;
	private Timer move;
	private boolean started = false;
	
	public Game()
	{
		screen = new MyScreen(50, this);
		initFrame();
		player = new Player(XLENGTH, YLENGTH);
		frame.addKeyListener(player);
		screen.start();
	}
	
	public void start()
	{
		move = new Timer();
		move.scheduleAtFixedRate(new TimerTask()
				{
					@Override
					public void run()
					{
						boolean food = player.onFood();
						player.move(food);
						if(player.dead)
						{
							die();
						}
					}
				}, 1000, MOVE_TIME);
	}
	
	private void die()
	{
		move.cancel();
		curFrameInt = SPECIFIED_FRAME + player.pos.size() - 1;
	}
	
	private void initFrame()
	{
		frame = new JFrame("Game");
		frame.setPreferredSize(new Dimension(300, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(screen.getPanel());
		frame.pack();
		frame.repaint();
	}
	
	public BufferedImage updateCurFrame()
	{
		int width = screen.getPanel().getWidth(); int height = screen.getPanel().getHeight();
		curFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)curFrame.getGraphics();
		for(int x = 0; x < XLENGTH; x++)
		{
			for(int y = 0; y < YLENGTH; y++)
			{
				Player.Coordinate curCoord = new Player.Coordinate(x, y);
				int fourthX = width / (XLENGTH + 1); int fourthY = height / (YLENGTH + 1);
				int xPix = (x + 1) * fourthX;
				int yPix = (y + 1) * fourthY;
				boolean playerPos = false;
				for(int i = 0; i < player.pos.size(); i++)
				{
					Player.Coordinate cur = player.pos.get(i);
					if(cur.equals(curCoord))
					{
						playerPos = true;
						break;
					}
				}
				if(playerPos)
				{
					if(curFrameInt == 0)
					{
						g.setColor(java.awt.Color.GREEN);
						g.fillRect(xPix - HALF - ADD_HALF, yPix - HALF - ADD_HALF, SIDE_LENGTH + ADD_SIDE_LENGTH, SIDE_LENGTH + ADD_SIDE_LENGTH);
					}
					g.setColor(java.awt.Color.RED);
				}
				else if(player.getFoodPos().equals(curCoord))
				{
					g.setColor(java.awt.Color.YELLOW);
				}
				else
				{
					g.setColor(java.awt.Color.WHITE);
				}
				g.fillRect(xPix - HALF, yPix - HALF, SIDE_LENGTH, SIDE_LENGTH);
			}
		}
		
		curFrameInt++;
		if(curFrameInt == SPECIFIED_FRAME)
		{
			curFrameInt = 0;
		}
		
		if(!started)
		{
			if(player.start)
			{
				started = true;
				start();
			}
		}
		
		return curFrame;
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
}
