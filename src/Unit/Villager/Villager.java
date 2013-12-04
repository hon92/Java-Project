/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Controls.Dijkstra;
import Controls.ListItem;
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
    private int pixelX, pixelY;
    private int newPixelX;
    private int newPixelY;
    private int t;

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

        pixelX = locationX * 25;
        pixelY = locationY * 25;
        newPixelX = pixelX;
        newPixelY = pixelY;
    }

    public void move()
    {

        if ((locationX == newLocationX) && (locationY == newLocationY))
        {
            isFinish = true;
        }
        else
        {
            if (currentPoint < moves.size())
            {
                gameBoard.setFieldIndex(locationX, locationY + 1, 0);
                gameBoard.setUnitField(locationX, locationY, null);
                gameBoard.setUnitField(locationX, locationY + 1, null);

                int newx = moves.get(currentPoint).getX();
                int newy = moves.get(currentPoint).getY();
                locationX = newx;
                locationY = newy;
                this.direction = moves.get(currentPoint).getDirection();
                gameBoard.setFieldIndex(locationX, locationY + 1, 11);
                gameBoard.setUnitField(locationX, locationY, this);
                gameBoard.setUnitField(locationX, locationY + 1, this);

                if (currentPoint == moves.size() - 1)
                {
                    direction = moves.get(moves.size() - 2).getDirection();
                }

            }
            currentPoint++;

        }
    }

    public void movePixel()
    {

        if ((pixelX == newPixelX) && (pixelY == newPixelY - 25))
        {
            gameBoard.setFieldIndex(pixelX / 25, pixelY / 25 + 1, 11);
            gameBoard.setUnitField(pixelX / 25, pixelY / 25, this);
            gameBoard.setUnitField(pixelX / 25, pixelY / 25 + 1, this);
            isFinish = true;
            return;
        }

        if (t == 24)
        {

            System.out.println("t: " + t + " " + (pixelX / 25) + "  " + (pixelY / 25 + 1));
            gameBoard.setFieldIndex(pixelX / 25, pixelY / 25 + 1, 0);
            gameBoard.setUnitField(pixelX / 25, pixelY / 25, null);
            gameBoard.setUnitField(pixelX / 25, pixelY / 25 + 1, null);
            if (currentPoint + 1 < moves.size())
            {
                currentPoint++;
            }
            t = 0;
        }

        int newx = moves.get(currentPoint).getX() * 25;
        int newy = moves.get(currentPoint).getY() * 25;
        this.direction = moves.get(currentPoint).getDirection();
        //System.err.println(newx + "  " + newy);
        if (newx > pixelX)
        {
            pixelX += 1;
        }
        if (newy > pixelY)
        {
            pixelY += 1;
        }
        if (newx < pixelX)
        {
            pixelX += -1;
        }
        if (newy < pixelY)
        {
            pixelY += -1;
        }

        t++;
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

        dd = new Dijkstra(new ListItem(pixelX / 25, pixelY / 25), new ListItem(x, y), gameBoard);

        moves = dd.getPath();
        isFinish = false;
        newLocationX = x;
        newLocationY = y + 1;

        gameBoard.setFieldIndex(pixelX / 25, pixelY / 25 + 1, 0);
        gameBoard.setUnitField(pixelX / 25, pixelY / 25, null);
        gameBoard.setUnitField(pixelX / 25, pixelY / 25 + 1, null);
        newPixelX = x * 25;
        newPixelY = (y * 25);

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
            g.fillRect(gameBoard.convertX(pixelX), gameBoard.convertY(pixelY - 10), 25, 5);
            g.setColor(Color.green);
            g.fillRect(gameBoard.convertX(pixelX), gameBoard.convertY(pixelY - 10), (int) (25 * getHpDown()), 5);
        }
        if ((direction >= 247) && (direction < 292))
        {
            g.drawImage(villagerTop, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }
        else if ((direction >= 78) && (direction < 123))
        {
            g.drawImage(villagerDown, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 158) && (direction < 203))
        {
            g.drawImage(villagerLeft, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 338) || (direction < 23))
        {
            g.drawImage(villagerRight, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            //g.drawRect(gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), villagerRight.getWidth(), villagerRight.getHeight());
        }

        else if ((direction < 338) && (direction >= 292))
        {
            g.drawImage(villagerRightTop, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 203) && (direction < 247))
        {
            g.drawImage(villagerLeftTop, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 123) && (direction < 158))
        {
            g.drawImage(villagerLeftBot, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 23) && (direction < 78))
        {
            g.drawImage(villagerRightBot, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
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

        //if (time % 2 == 0)
        {
            time = 0;

            if (isMoving())
            {
                movePixel();
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
