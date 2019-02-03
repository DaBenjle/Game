import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener
{
	public Dimension pos;
	private int width, height;
	
	public Player(int width, int height)
	{
		pos = new Dimension(0, 0);
		this.width = width;
		this.height = height;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();
		c = Character.toLowerCase(c);
		switch(c)
		{
			case 'w':
				if(pos.height > 0)
				{
					pos.height--;
				}
				break;
			case 's':
				if(pos.height < height)
				{
					pos.height++;
				}
				break;
			case 'a':
				if(pos.width > 0)
				{
					pos.width--;
				}
				break;
			case 'd':
				if(pos.width < width)
				{
					pos.width++;
				}
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// do nothing
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// do nothing
	}
	
}
