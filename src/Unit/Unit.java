/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Data.UnitType;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
    protected int locationX;
    protected int locationY;
    protected int direction;
    protected UnitType typeUnit;
    protected BufferedImage sourceImg;
    protected GameBoard gameBoard;
    
    

    protected boolean selected = false;

    public Unit(GameBoard gameBoard, int x, int y, int dir)
    {
        this.gameBoard = gameBoard;
        locationX = x;
        locationY = y;
        direction = dir;
    }

    public abstract void move(int x, int y);

    public abstract void drawUnit(Graphics g);

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
        return locationX * 25;
    }

    public int getY()
    {
        return locationY * 25;
    }

    public abstract String getName();

    public abstract int getAttack();

    public abstract int getArmor();

    public abstract int getHp();

    public abstract int getMaxHp();

    public abstract BufferedImage getIcon();

    public abstract void tick();

    public abstract double getHpDown();

}
