/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Building.Farm;

import Buildings.Building;
import Buildings.BuildingType;
import Data.GameData;
import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adam
 */
public class Farm extends Building
{

    private int maxFood;
    private int currentFood;

    public Farm(GameBoard gameBoard, int x, int y, String team)
    {
        super(gameBoard, x, y, team, 6, 6);
        initFarm();

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                gameBoard.setBuildingObjectField(x + i, y + j, this);
                gameBoard.setFieldIndex(x + i, y + j, 20);
            }
        }

        if (team == "Blue")
        {
            gameBoard.getBluePlayer().addBuilding(this);
        }

        if (team == "Red")
        {
            gameBoard.getRedPlayer().addBuilding(this);
        }

    }

    public void deleteFarm()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                System.out.println(this.getLocationX() + i);
                gameBoard.setBuildingObjectField(this.getLocationX() + i, this.getLocationY() + j, null);
                if (gameBoard.getFieldIndex(this.getLocationX() + i, this.getLocationY() + j) == 20)
                {
                    gameBoard.setFieldIndex(this.getLocationX() + i, this.getLocationY() + j, 0);
                }
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

    @Override
    public void drawBuilding(Graphics g)
    {
        if (isSelected())
        {
            int imageWidth = sourceImg.getWidth();

            g.setColor(Color.red);
            g.fillRect(gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE - 10), imageWidth, 5);
            g.setColor(Color.green);
            g.fillRect(gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE - 10), (int) (imageWidth * getHpDown()), 5);
        }
        if (sourceImg != null)
        {
            g.drawImage(sourceImg, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
    }

    private void initFarm()
    {
        buildingName = "Farm";
        buildingType = BuildingType.FARM;

        maxFood = currentFood = 500;

        buildTime = 30;
        maxHp = 500;
        currentHp = maxHp;
        try
        {

            sourceImg = ImageIO.read(new File("src/Building/Farm/farm.png"));

            iconImg = ImageIO.read(new File("src/Building/Farm/farmIcon.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Farm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getName()
    {
        return "Farm";
    }

    @Override
    public int getMaxHp()
    {
        return maxHp;
    }

    @Override
    public int getCurrentHp()
    {
        return currentHp;
    }

    @Override
    public BufferedImage getIcon()
    {
        return iconImg;
    }

    public void setCurrentFood(int x)
    {
        currentFood -= x;
    }

    public int getCurrentFood()
    {
        return currentFood;
    }

    @Override
    public void tick()
    {
        super.tick(); //To change body of generated methods, choose Tools | Templates.
    }

}
