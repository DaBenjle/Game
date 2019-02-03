import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game
{
	
	public static int sideLength = 10, addSideLength = 6;
	public static int half = sideLength / 2, addHalf = addSideLength / 2;
	public static int XLENGTH = 5, YLENGTH = 4;
	private MyScreen screen;
	private BufferedImage curFrame;
	private JFrame frame;
	private Player player;
	private int fifthFrame = 0;
	
	public Game()
	{
		screen = new MyScreen(50, this);
		initFrame();
		player = new Player(XLENGTH - 1, YLENGTH - 1);
		frame.addKeyListener(player);
		screen.start();
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
				int fourthX = width / (XLENGTH + 1); int fourthY = height / (YLENGTH + 1);
				int xPix = (x + 1) * fourthX;
				int yPix = (y + 1) * fourthY;
				if(x == player.pos.width && y == player.pos.height)
				{
					if(fifthFrame == 0)
					{
						g.setColor(java.awt.Color.GREEN);
						g.fillRect(xPix - half - addHalf, yPix - half - addHalf, sideLength + addSideLength, sideLength + addSideLength);
					}
					g.setColor(java.awt.Color.RED);
				}
				else
				{
					g.setColor(java.awt.Color.WHITE);
				}
				g.fillRect(xPix - half, yPix - half, sideLength, sideLength);
			}
		}
		fifthFrame++;
		if(fifthFrame == 15)
		{
			fifthFrame = 0;
		}
		return curFrame;
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
}
