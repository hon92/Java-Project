/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Buildings.Action;
import Controls.Dijkstra;
import Controls.ListItem;
import Data.UnitType;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Honza
 */
public abstract class Unit
{

    protected static int createTime;
    protected int currentHp;
    protected int maxHp;
    protected int speed;
    protected int pixelX;
    protected int pixelY;
    protected int newPixelX;
    protected int newPixelY;
    protected int direction;
    protected UnitType typeUnit;
    protected BufferedImage sourceImg;
    protected GameBoard gameBoard;
    protected ArrayList<ListItem> moves;
    protected String team;
    protected boolean isFinish = true;
    protected boolean selected = false;
    protected Dijkstra dd = null;
    protected int t;
    protected int currentPoint = 1;
    protected ArrayList<Action> actions;
    private boolean readyToMove = true;
    private boolean isOnWay = false;

    public Unit(GameBoard gameBoard, int x, int y, int dir, String team)
    {
        this.gameBoard = gameBoard;
        pixelX = x * 25;
        pixelY = y * 25;
        direction = dir;
        this.team = team;
        moves = new ArrayList<>();
        actions = new ArrayList<>();
        if (team == "Blue")
        {
            gameBoard.getBluePlayer().setActualPop(1);
        }

        if (team == "Red")
        {
            gameBoard.getRedPlayer().setActualPop(1);
        }

    }

    public void move(int x, int y)
    {
        if (!isOnWay)
        {
            currentPoint = 1;
            t = 0;
            moves.clear();
            dd = new Dijkstra(new ListItem(pixelX / 25, pixelY / 25), new ListItem(x, y), gameBoard);
            moves = dd.getPath();
            isFinish = false;

            if (moves.size() > 1)
            {
                popPosition();
                newPixelX = x * 25;
                newPixelY = (y * 25);
                isOnWay = true;
            }
        }
        else
        {
            int xx = moves.get(currentPoint - 1).getX();
            int yy = moves.get(currentPoint - 1).getY();
            gameBoard.setFieldIndex(xx, yy + 1, 0);
            gameBoard.setUnitField(xx, yy, null);
            gameBoard.setUnitField(xx, yy + 1, null);

            currentPoint = 1;
            t = 0;
            moves.clear();

            dd = new Dijkstra(new ListItem(pixelX / 25, pixelY / 25), new ListItem(x, y), gameBoard);
            moves = dd.getPath();
            isFinish = false;

            if (moves.size() > 1)
            {
                popPosition();
                newPixelX = x * 25;
                newPixelY = (y * 25);
                isOnWay = true;
            }

        }

    }

    private void pushPosition()
    {
        gameBoard.setFieldIndex(pixelX / 25, pixelY / 25 + 1, 11);
        gameBoard.setUnitField(pixelX / 25, pixelY / 25, this);
        gameBoard.setUnitField(pixelX / 25, pixelY / 25 + 1, this);
    }

    private void popPosition()
    {
        gameBoard.setFieldIndex(pixelX / 25, pixelY / 25 + 1, 0);
        gameBoard.setUnitField(pixelX / 25, pixelY / 25, null);
        gameBoard.setUnitField(pixelX / 25, pixelY / 25 + 1, null);
    }

    public void movePixel()
    {
        if ((pixelX == newPixelX) && (pixelY == newPixelY - 25)) // finish
        {
            pushPosition();
            isFinish = true;
            readyToMove = true;
            isOnWay = false;
            if (currentPoint > 1)
            {
                int xx = moves.get(currentPoint - 1).getX();
                int yy = moves.get(currentPoint - 1).getY();
                gameBoard.setFieldIndex(xx, yy + 1, 0);
                gameBoard.setUnitField(xx, yy, null);
                gameBoard.setUnitField(xx, yy + 1, null);
            }
            return;
        }

        if (t == 25)
        {
            pushPosition();

            if (currentPoint > 1)
            {
                int xx = moves.get(currentPoint - 1).getX();
                int yy = moves.get(currentPoint - 1).getY();
                gameBoard.setFieldIndex(xx, yy + 1, 0);
                gameBoard.setUnitField(xx, yy, null);
                gameBoard.setUnitField(xx, yy + 1, null);
            }
            if (currentPoint + 1 < moves.size())
            {
                currentPoint++;
            }

            t = 0;

        }
        if (currentPoint < moves.size())
        {
            int newx = moves.get(currentPoint).getX() * 25;
            int newy = moves.get(currentPoint).getY() * 25;
            if (newx > pixelX)
            {
                direction = 0;
                pixelX += 1;
            }
            if (newy > pixelY)
            {
                direction = 90;
                pixelY += 1;
            }
            if (newx < pixelX)
            {
                direction = 180;
                pixelX += -1;
            }
            if (newy < pixelY)
            {
                direction = 270;
                pixelY += -1;
            }
            t++;
        }

    }

    public boolean isAlive()
    {
        return currentHp <= 0 ? false : true;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int dir)
    {
        direction = dir;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean set)
    {
        selected = set;
    }

    static public int getCreateTime()
    {
        return createTime;
    }

    public int getX()
    {
        return pixelX;
    }

    public int getY()
    {
        return pixelY;
    }

    public String getPlayer()
    {
        return team;
    }

    public Dijkstra getDijkstra()
    {
        return dd;
    }

    public List<Action> getActions()
    {
        return actions;
    }

    public abstract void drawUnit(Graphics g);

    public abstract String getName();

    public abstract int getAttack();

    public abstract int getArmor();

    public abstract int getHp();

    public abstract void setHp();

    public abstract int getMaxHp();

    public abstract BufferedImage getIcon();

    public abstract void tick();

    public abstract double getHpDown();

}
