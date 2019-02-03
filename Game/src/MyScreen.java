import bensAbstract.Screen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyScreen extends Screen
{
	
	private Game game;
	
	@Override
	public void update()
	{
		pixels = new BufferedImage(getPanel().getWidth(), getPanel().getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)pixels.getGraphics();
		g.drawImage(game.updateCurFrame(), 0, 0, null);
	}
	
	public MyScreen(int fps, Game game)
	{
		super(fps);
		this.game = game;
	}

}
