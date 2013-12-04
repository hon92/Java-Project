/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Controls.Dijkstra;
import Controls.ListItem;
import Data.GameData;
import GameElement.Grass;
import Unit.Unit;
import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    private int newLocationX;
    private int newLocationY;
    int time = 0;
    private ArrayList<ListItem> moves;
    private int currentPoint = 1;
    private boolean isFinish = true;
    private Dijkstra dd = null;

    public Villager(GameBoard gameBoard, int x, int y, int dir)
    {
        super(gameBoard, x, y, dir);

        maxHp = 50;
        currentHp = 10;
        speed = 2;
        moves = new ArrayList<>();
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
        gameBoard.setFieldIndex(x, y + 1, 11);

        newLocationX = locationX;
        newLocationY = locationY;
    }

    public void move()
    {

        if ((locationX == newLocationX) && (locationY == newLocationY))
        {
            isFinish = true;
        }
        else
        {

            //gameBoard.setFieldIndex(locationX, locationY + 1, 0);
            gameBoard.setUnitField(locationX, locationY, null);
            gameBoard.setUnitField(locationX, locationY + 1, null);

            if (currentPoint < moves.size())
            {
                gameBoard.setFieldIndex(locationX, locationY + 1, 0);
                gameBoard.setUnitField(locationX, locationY, null);
                gameBoard.setUnitField(locationX, locationY + 1, null);
                
                locationX = moves.get(currentPoint).getX();
                locationY = moves.get(currentPoint).getY();
                this.direction = moves.get(currentPoint).getDirection();
                //gameBoard.setFieldIndex(locationX, locationY + 1, 11);
                gameBoard.setUnitField(locationX, locationY, this);
                gameBoard.setUnitField(locationX, locationY + 1, this);

                if (currentPoint == moves.size() - 1)
                {
                    direction = moves.get(moves.size() - 2).getDirection();
                }

                //System.out.println("New Location:  " + "Lx: " + locationX + "  " + "Ly: " + locationY);
            }
            currentPoint++;

        }
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
        currentPoint = 1;
        moves.clear();

        dd = new Dijkstra(new ListItem(locationX, locationY), new ListItem(x, y), gameBoard.getFieldArray(), gameBoard);

        moves = dd.getPath();
        isFinish = false;
//        for (ListItem l : moves)
//        {
//            System.out.println(l.getItem());
//        }
        newLocationX = x;
        newLocationY = y - 1;

    }

    public Dijkstra getDijkstra()
    {
        return dd;
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
        if ((direction >= 247) && (direction < 292))
        {
            g.drawImage(villagerTop, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
        else if ((direction >= 78) && (direction < 123))
        {
            g.drawImage(villagerDown, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }

        else if ((direction >= 158) && (direction < 203))
        {
            g.drawImage(villagerLeft, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }

        else if ((direction >= 338) || (direction < 23))
        {
            g.drawImage(villagerRight, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }

        else if ((direction < 338) && (direction >= 292))
        {
            g.drawImage(villagerRightTop, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }

        else if ((direction >= 203) && (direction < 247))
        {
            g.drawImage(villagerLeftTop, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }

        else if ((direction >= 123) && (direction < 158))
        {
            g.drawImage(villagerLeftBot, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }

        else if ((direction >= 23) && (direction < 78))
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

    @Override
    public void tick()
    {
        time++;
        if (time % 60 == 0)
        {
            time = 0;
            if (isMoving())
            {
                move();

            }
        }

    }

    private boolean isMoving()
    {

        if (!isFinish)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
