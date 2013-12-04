package Controls;

public class ListItem
{

    private int x;
    private int y;
    private int value;
    private boolean visited;
    private boolean examined;
    private int direction;

    public ListItem(int x, int y)
    {
        this.direction = 0;
        this.x = x;
        this.y = y;
        this.value = 99;
        this.visited = false;
        this.examined = false;
    }

    public void setDirection(int x)
    {
        direction = x;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setItem(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean notExamined()
    {
        if (examined == false)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setExamined()
    {
        examined = true;
    }

    public boolean notVisited()
    {
        if (visited == false)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setVisited()
    {
        visited = true;
    }

    public void setValue(int x)
    {
        this.value = x;
    }

    public int getValue()
    {
        return this.value;
    }

    public String getItem()
    {
        return x + " + " + y;
    }
}
