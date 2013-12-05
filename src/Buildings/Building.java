/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Honza
 */
public abstract class Building
{

    protected String buildingName = null;
    protected BuildingType buildingType = null;
    protected BufferedImage sourceImg = null;
    protected BufferedImage iconImg = null;
    protected GameBoard gameBoard = null;
    protected List<Action> actions = null;
    
    protected int buildTime = -1;
    protected int currentHp = -1;
    protected int maxHp = -1;
    protected int locationX = -1;
    protected int locationY = -1;
    
    protected String team;

    protected boolean selected = false;

    public Building(GameBoard gameBoard, int x, int y, String team)
    {
        this.team=team;
        this.gameBoard = gameBoard;
        this.locationX = x;
        this.locationY = y;
        actions = new ArrayList<Action>();
    }

    public abstract void drawBuilding(Graphics g);

    public int getLocationX()
    {
        return locationX;
    }

    public int getLocationY()
    {
        return locationY;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public double getHpDown()
    {
        return ((currentHp / (double) maxHp));
    }

    public List<Action> getActions() {
        return actions;
    }

    public BufferedImage getSourceImg() {
        return sourceImg;
    }
    
    public abstract String getName();

    public abstract int getMaxHp();

    public abstract int getCurrentHp();

    public abstract BufferedImage getIcon();

    public abstract void tick();
}
