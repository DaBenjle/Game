package bensAbstract;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class Screen
{
	protected boolean gameRunning = true;
	protected BufferedImage pixels;
	protected JPanel panel;
	private int SKIP_TICKS;
	private static final int MAX_FRAMESKIP = 10;
	
	private long nextGameTick = System.currentTimeMillis();
	
	public Screen(int ticksPerSec)
	{
		if((1000 % ticksPerSec) != 0)
		{
			throw new IllegalArgumentException("1000 must be divisible by ticks per second. Your ticks per second was: " + ticksPerSec);
		}
		SKIP_TICKS = 1000 / ticksPerSec;
		panel = new JPanel();
	}
	
	public void start()
	{
		while(gameRunning)
		{
			screenUpdate();
		}
	}
	
	private void screenUpdate()
	{
		int loops = 0;
        while( System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIP)
        {
            update();
            nextGameTick += SKIP_TICKS;
            loops++;
        }

        display();
	}
	
	private void display()
	{
		Graphics2D g2 = (Graphics2D)panel.getGraphics();
		g2.drawImage(pixels, 0, 0, pixels.getWidth(), pixels.getHeight(), null);
	}
	
	/*
	 * Should change your pixels
	 */
	protected abstract void update();

}
