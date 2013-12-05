/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Melee;

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
public class SwordMan extends Unit
{
    private static int attack;
    private static int armor;

    private static BufferedImage swordManIcon;
    
    private static BufferedImage swordManDownBlue; 
    private static BufferedImage swordManTopBlue;
    private static BufferedImage swordManLeftBlue;
    private static BufferedImage swordManRightBlue;
    private static BufferedImage swordManRightBotBlue;
    private static BufferedImage swordManRightTopBlue;
    private static BufferedImage swordManLeftTopBlue;
    private static BufferedImage swordManLeftBotBlue;
    
    
    private static BufferedImage swordManDownRed;
    private static BufferedImage swordManTopRed;
    private static BufferedImage swordManLeftRed;
    private static BufferedImage swordManRightRed;
    private static BufferedImage swordManRightBotRed;
    private static BufferedImage swordManRightTopRed;
    private static BufferedImage swordManLeftTopRed;
    private static BufferedImage swordManLeftBotRed;

    private int newLocationX;
    private int newLocationY;
    int time = 0;
    private ArrayList<ListItem> moves;
    private int currentPoint = 1;
    private boolean isFinish = true;
    private Dijkstra dd = null;
    private int newPixelX;
    private int newPixelY;
    private int t;
    
    private int foodCost;
    private int goldCost;

    public SwordMan(GameBoard gameBoard, int x, int y, int dir, String team)
    {
        super(gameBoard, x, y, dir, team);

        maxHp = 100;
        currentHp = 100;
        speed = 3;
        foodCost = 60;
        goldCost = 30;
        moves = new ArrayList<>();
        try
        {
            
            swordManIcon = ImageIO.read(new File("src/Resources/swordManImg/swordManIcon.png"));
            
            swordManDownBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManBot.png"));
            swordManTopBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManTop.png"));
            swordManRightBotBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManRightBot.png"));
            swordManRightBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManRight.png"));
            swordManLeftBotBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManLeftBot.png"));
            swordManLeftTopBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManLeftTop.png"));
            swordManRightTopBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManRightTop.png"));
            swordManLeftBlue = ImageIO.read(new File("src/Resources/swordManImg/swordManLeft.png"));
            
            swordManDownRed = ImageIO.read(new File("src/Resources/swordManImg/swordManBotRed.png"));
            swordManTopRed = ImageIO.read(new File("src/Resources/swordManImg/swordManTopRed.png"));
            swordManRightBotRed = ImageIO.read(new File("src/Resources/swordManImg/swordManRightBotRed.png"));
            swordManRightRed = ImageIO.read(new File("src/Resources/swordManImg/swordManRightRed.png"));
            swordManLeftBotRed = ImageIO.read(new File("src/Resources/swordManImg/swordManLeftBotRed.png"));
            swordManLeftTopRed = ImageIO.read(new File("src/Resources/swordManImg/swordManLeftTopRed.png"));
            swordManRightTopRed = ImageIO.read(new File("src/Resources/swordManImg/swordManRightTopRed.png"));
            swordManLeftRed = ImageIO.read(new File("src/Resources/swordManImg/swordManLeftRed.png"));
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
        createTime = 5;
        
        if(team == "Blue")
        {
            gameBoard.getBluePlayer().addUnit(this);
            gameBoard.getBluePlayer().setFood(-1*foodCost);
            gameBoard.getBluePlayer().setGold(-1*goldCost);
        }
        
        if(team == "Red")
        {
            gameBoard.getRedPlayer().addUnit(this);
            gameBoard.getRedPlayer().setFood(-1*foodCost);
            gameBoard.getBluePlayer().setGold(-1*goldCost);
        }    
        
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

            gameBoard.setFieldIndex(pixelX / 25, pixelY / 25 + 1, 0);
            gameBoard.setUnitField(pixelX / 25, pixelY / 25, null);
            gameBoard.setUnitField(pixelX / 25, pixelY / 25 + 1, null);
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
        if(team=="Blue")
        {
        if ((direction >= 247) && (direction < 292))
        {
            g.drawImage(swordManTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }
        else if ((direction >= 78) && (direction < 123))
        {
            g.drawImage(swordManDownBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 158) && (direction < 203))
        {
            g.drawImage(swordManLeftBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 338) || (direction < 23))
        {
            g.drawImage(swordManRightBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            //g.drawRect(gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), swordManRight.getWidth(), swordManRight.getHeight());
        }

        else if ((direction < 338) && (direction >= 292))
        {
            g.drawImage(swordManRightTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 203) && (direction < 247))
        {
            g.drawImage(swordManLeftTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 123) && (direction < 158))
        {
            g.drawImage(swordManLeftBotBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 23) && (direction < 78))
        {
            g.drawImage(swordManRightBotBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }
        }
        else
        {
         if ((direction >= 247) && (direction < 292))
        {
            g.drawImage(swordManTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }
        else if ((direction >= 78) && (direction < 123))
        {
            g.drawImage(swordManDownRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 158) && (direction < 203))
        {
            g.drawImage(swordManLeftRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 338) || (direction < 23))
        {
            g.drawImage(swordManRightRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            //g.drawRect(gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), swordManRight.getWidth(), swordManRight.getHeight());
        }

        else if ((direction < 338) && (direction >= 292))
        {
            g.drawImage(swordManRightTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 203) && (direction < 247))
        {
            g.drawImage(swordManLeftTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 123) && (direction < 158))
        {
            g.drawImage(swordManLeftBotRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }

        else if ((direction >= 23) && (direction < 78))
        {
            g.drawImage(swordManRightBotRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
        }   
        }
    }

    @Override
    public String getName()
    {
        return "SwordMan";
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
        return swordManIcon;
    }

    @Override
    public void tick()
    {
        time++;

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
