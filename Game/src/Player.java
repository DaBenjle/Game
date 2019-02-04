import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Player implements KeyListener
{
	public Dimension pos;
	private Direction dir;
	private int width, height;
	public boolean dead = false, start = false;

	public Player(int width, int height)
	{
		Random rand = new Random();
		pos = new Dimension(rand.nextInt(width), rand.nextInt(height));
		dir = Direction.values()[rand.nextInt(Direction.values().length)];
		this.width = width;
		this.height = height;
	}

	public void move()
	{
		switch (dir)
		{
		case NORTH:
			specifiedMove(false, false);
			break;
		case SOUTH:
			specifiedMove(false, true);
			break;
		case WEST:
			specifiedMove(true, false);
			break;
		case EAST:
			specifiedMove(true, true);
			break;
		}
	}

	private void specifiedMove(boolean x, boolean positive)
	{
		if (x)
		{
			if (positive)
			{
				if (pos.width < width - 1)
				{
					pos.width++;
				}
				else
				{
					dead = true;
				}
			}
			else
			{
				if (pos.width > 0)
				{
					pos.width--;
				}
				else
				{
					dead = true;
				}
			}
		}
		else
		{
			if (positive)
			{
				if (pos.height < height - 1)
				{
					pos.height++;
				}
				else
				{
					dead = true;
				}
			}
			else
			{
				if (pos.height > 0)
				{
					pos.height--;
				}
				else
				{
					dead = true;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();
		c = Character.toLowerCase(c);
		if (!start)
		{
			start = true;
		}
		switch (c)
		{
		case 'w':
			dir = Direction.NORTH;
			break;
		case 's':
			dir = Direction.SOUTH;
			break;
		case 'a':
			dir = Direction.WEST;
			break;
		case 'd':
			dir = Direction.EAST;
			break;
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

	public static enum Direction
	{
		NORTH, SOUTH, EAST, WEST;
	}

	public static class Coordinate
	{
		private int x, y;
		public Coordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
