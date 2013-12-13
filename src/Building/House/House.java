/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Building.House;

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
public class House extends Building
{

    public House(GameBoard gameBoard, int x, int y, String team)
    {
        super(gameBoard, x, y, team, 5, 4);
        initHouse();

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                gameBoard.setBuildingObjectField(x + i, y + j, this);
                gameBoard.setFieldIndex(x + i, y + j, 15);
            }
        }

        if (team == "Blue")
        {
            gameBoard.getBluePlayer().addBuilding(this);
            gameBoard.getBluePlayer().setMaxPop(5);
        }

        if (team == "Red")
        {
            gameBoard.getRedPlayer().addBuilding(this);
            gameBoard.getRedPlayer().setMaxPop(5);
        }

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

    private void initHouse()
    {
        buildingName = "House";
        buildingType = BuildingType.HOUSE;

        buildTime = 30;
        maxHp = 500;
        currentHp = maxHp;
        try
        {
            if (team == "Blue")
            {
                sourceImg = ImageIO.read(new File("src/Building/House/HouseBlue.png"));
            }
            else
            {
                sourceImg = ImageIO.read(new File("src/Building/House/HouseRed.png"));
            }
            iconImg = ImageIO.read(new File("src/Building/House/houseIcon.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(House.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getName()
    {
        return "House";
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

    @Override
    public void tick()
    {
        super.tick(); //To change body of generated methods, choose Tools | Templates.
    }

}
