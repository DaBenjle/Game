import bensAbstract.Screen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class MyGame extends Screen
{
	private Random random = new Random();
	@Override
	public void update()
	{
		pixels = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)pixels.getGraphics();
		int height = random.nextInt(200);
		g2.drawRect(5, 5, 20, height);
	}
	
	public MyGame()
	{
		super(50);
		JFrame frame = new JFrame("Name");
		frame.setPreferredSize(new Dimension(300, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.repaint();
		start();
	}

	public static void main(String[] args)
	{
		new MyGame();
	}

}
