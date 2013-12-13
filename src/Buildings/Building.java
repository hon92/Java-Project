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
    protected int width;
    protected int height;
    protected String team;

    protected boolean selected = false;

    public Building(GameBoard gameBoard, int x, int y, String team, int w, int h)
    {
        this.team = team;
        this.gameBoard = gameBoard;
        this.locationX = x;
        this.locationY = y;
        this.width = w;
        this.height = h;
        actions = new ArrayList<Action>();

    }

    public abstract void drawBuilding(Graphics g);

    public boolean isCrashed()
    {
        return (currentHp <= 0) ? true : false;
    }

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

    public List<Action> getActions()
    {
        return actions;
    }

    public BufferedImage getSourceImg()
    {
        return sourceImg;
    }

    public String getPlayer()
    {
        return team;
    }

    public abstract String getName();

    public abstract int getMaxHp();

    public abstract int getCurrentHp();

    public abstract BufferedImage getIcon();

    public void tick()
    {
        if (isCrashed())
        {
            deleteBuilding();
        }
    }

    public int getBuildingWidth()
    {
        return width;
    }

    public int getBuildingHeight()
    {
        return height;
    }

    public void setHp(int value)
    {
        currentHp += value;
    }

    public void deleteBuilding()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                gameBoard.setBuildingObjectField(locationX + i, locationY + j, null);
                gameBoard.setFieldIndex(locationX + i, locationY + j, 0);
            }
        }

        if (getPlayer() == "Blue")
        {
            gameBoard.getBluePlayer().getBuildings().remove(this);

        }
        else
        {
            gameBoard.getRedPlayer().getBuildings().remove(this);

        }

        gameBoard.getBuildings().remove(this);
    }
}
