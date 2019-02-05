import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Player implements KeyListener
{
	public ArrayList<Coordinate> pos;
	private Direction dir;
	private int width, height;
	public boolean dead = false, start = false;

	public Player(int width, int height)
	{
		Random rand = new Random();
		pos = new ArrayList<>();
		pos.add(new Coordinate(rand.nextInt(width), rand.nextInt(height)));
		dir = Direction.values()[rand.nextInt(Direction.values().length)];
		this.width = width;
		this.height = height;
	}

	public void move(boolean eat)
	{
		switch (dir)
		{
		case NORTH:
			specifiedMove(false, false, eat);
			break;
		case SOUTH:
			specifiedMove(false, true, eat);
			break;
		case WEST:
			specifiedMove(true, false, eat);
			break;
		case EAST:
			specifiedMove(true, true, eat);
			break;
		}
	}

	private void specifiedMove(boolean x, boolean positive, boolean eat)
	{
		Coordinate temp = null;
		if(eat)
		{
			temp = pos.get(pos.size() - 1).duplicate();
		}
		for(int i = pos.size() - 1; i > 0; i--)
		{
			Coordinate cur = pos.get(i);
			cur.setValues(pos.get(i - 1));
		}
		if(eat)
		{
			//should always work without this vvv if, but to be safe
			if(temp != null)
			{
				pos.add(temp);
			}
		}
		
		if(x)
		{
			if (positive)
			{
				if (pos.get(0).x < width - 1)
				{
					pos.get(0).x++;
				}
				else
				{
					dead = true;
				}
			}
			else
			{
				if (pos.get(0).x > 0)
				{
					pos.get(0).x--;
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
				if (pos.get(0).y < height - 1)
				{
					pos.get(0).y++;
				}
				else
				{
					dead = true;
				}
			}
			else
			{
				if (pos.get(0).y > 0)
				{
					pos.get(0).y--;
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
		public int x, y;
		public Coordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public void setValues(Coordinate input)
		{
			x = input.x;
			y = input.y;
		}
		
		public Coordinate duplicate()
		{
			return new Coordinate(x, y);
		}
		
		@Override
		public boolean equals(Object input)
		{
			if(input instanceof Coordinate)
			{
				Coordinate coord = (Coordinate)input;
				return x == coord.x && y == coord.y;
			}
			return false;
		}
	}
}
