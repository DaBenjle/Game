import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Player implements KeyListener
{
	public ArrayList<Coordinate> pos;
	private Coordinate foodPos;
	private Direction dir;
	private int width, height;
	public boolean dead = false, start = false, win = false;

	public Player(int width, int height)
	{
		Random rand = new Random();
		pos = new ArrayList<>();
		pos.add(new Coordinate(rand.nextInt(width), rand.nextInt(height)));
		dir = Direction.values()[rand.nextInt(Direction.values().length)];
		this.width = width;
		this.height = height;
		setFood();
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
		if(eat)
		{
			setFood();
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
		if(temp != null)
		{
			pos.add(temp);
		}
		
		Coordinate targetLocation = pos.get(0).duplicate();;
		
		if(x)
		{
			if (positive)
			{
				targetLocation.x++;
			}
			else
			{
				targetLocation.x--;
			}
		}
		else
		{
			if (positive)
			{
				targetLocation.y++;
			}
			else
			{
				targetLocation.y--;
			}
		}
		
		if(targetLocation.isOutside(width, height))
		{
			dead = true;
			return;
		}
		
		for(Coordinate cur : pos)
		{
			if(targetLocation.equals(cur))
			{
				dead = true;
				return;
			}
		}
		pos.set(0, targetLocation);
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
	
	public void setFood()
	{
		if(pos.size() == width * height)
		{
			win = true;
			System.out.println(win);
			return;
		}
		Random rand = new Random();
		int x = rand.nextInt(width); int y = rand.nextInt(height);
		
		for(int i = 0; i < pos.size(); i++)
		{
			Coordinate cur = pos.get(i);
			if(x == cur.x && y == cur.y)
			{
				x = rand.nextInt(width);
				y = rand.nextInt(height);
				i = 0;
			}
		}
		foodPos = new Coordinate(x, y);
	}
	
	public Coordinate getFoodPos()
	{
		return foodPos;
	}
	
	public boolean onFood()
	{
		return pos.get(0).equals(foodPos);
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
		
		public boolean isOutside(int w, int h)
		{
			return x > w || y > h || x < 0 || y < 0;
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
