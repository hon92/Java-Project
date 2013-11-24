/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Data.GameData;
import GameElement.Grass;
import Unit.Unit;
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
 * @author Honza
 */
public class Villager extends Unit
{
    private int carriedResources;

    private static int attack;
    private static int armor;

    private static BufferedImage villagerDown;
    private static BufferedImage villagerIcon;
    private static BufferedImage villagerTop;
    private static BufferedImage villagerLeft;
    private static BufferedImage villagerRight;
    private static BufferedImage villagerRightBot;
    private static BufferedImage villagerRightTop;
    private static BufferedImage villagerLeftTop;
    private static BufferedImage villagerLeftBot;

    public Villager(GameBoard gameBoard, int x, int y, int dir)
    {
        super(gameBoard, x, y, dir);

        maxHp = 50;
        currentHp = 10;
        speed = 2;

        try
        {
            villagerDown = ImageIO.read(new File("src/Resources/villagerImg/villagerDown.png"));
            villagerIcon = ImageIO.read(new File("src/Resources/villagerImg/villagerIcon.png"));
            villagerTop = ImageIO.read(new File("src/Resources/villagerImg/villagerTop.png"));
            villagerRightBot = ImageIO.read(new File("src/Resources/villagerImg/villagerRightBot.png"));
            villagerRight = ImageIO.read(new File("src/Resources/villagerImg/villagerRight.png"));
            villagerLeftBot = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftBot.png"));
            villagerLeftTop = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftTop.png"));
            villagerRightTop = ImageIO.read(new File("src/Resources/villagerImg/villagerRightTop.png"));
            villagerLeft = ImageIO.read(new File("src/Resources/villagerImg/villagerLeft.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }
        gameBoard.setUnitField(x, y, this);
        gameBoard.setUnitField(x, y + 1, this);
        //gameBoard.setFieldIndex(x, y + 1, 11);
    }

    public void move()
    {

    }

    public double getHpDown()
    {
        return ((currentHp / (double) maxHp));
    }

    public void build()
    {

    }

    @Override
    public void move(int x, int y)
    {

    }

    @Override
    public void drawUnit(Graphics g)
    {
        if (isSelected())
        {
            g.setColor(Color.red);
            g.fillRect(gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE - 10), 25, 5);
            g.setColor(Color.green);
            g.fillRect(gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE - 10), (int) (25 * getHpDown()), 5);
        }
        if((direction>=247) && (direction<292))
           {
               g.drawImage(villagerTop, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);     
           }
        else if ((direction>=78) && (direction < 123))
        {
            g.drawImage(villagerDown, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        
        else if ((direction >=158) && (direction <203))
        {
            g.drawImage(villagerLeft, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        
        else if ((direction >=338) || (direction <23))
        {
            g.drawImage(villagerRight, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        
        else if ((direction <338) && (direction >=292))
        {
            g.drawImage(villagerRightTop, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        
        else if ((direction >=203) && (direction <247))
        {
            g.drawImage(villagerLeftTop, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        
        else if ((direction >=123) && (direction <158))
        {
            g.drawImage(villagerLeftBot, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        
        else if ((direction >=23) && (direction <78))
        {
            g.drawImage(villagerRightBot, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
    }

    @Override
    public String getName()
    {
        return "Villager";
    }

    @Override
    public int getAttack()
    {
        return attack;
    }

    @Override
    public int getArmor()
    {
        return armor;
    }

    @Override
    public int getHp()
    {
        return currentHp;
    }

    @Override
    public int getMaxHp()
    {
        return maxHp;
    }

    @Override
    public BufferedImage getIcon()
    {
        return villagerIcon;
    }

}
