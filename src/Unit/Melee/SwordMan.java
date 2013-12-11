/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Melee;

import Building.Farm.Farm;
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
public class SwordMan extends Unit
{

    private int time = 0;
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

    private static BufferedImage attackImg;

    private int foodCost;
    private int goldCost;

    private boolean attacking;
    private int timeAttack = 10;
    private int timeToNewAttack = 0;
    private boolean enemyIsClose = false;
    private Unit enemy;

    public SwordMan(GameBoard gameBoard, int x, int y, int dir, String team)
    {
        super(gameBoard, x, y, dir, team);

        attacking = false;
        maxHp = 100;
        currentHp = 80;
        speed = 3;
        foodCost = 60;
        goldCost = 30;
        attack = 3;

        try
        {

            attackImg = ImageIO.read(new File("src/Resources/swordManImg/attack.png"));

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

        newPixelX = pixelX;
        newPixelY = pixelY;
        createTime = 5;

        if (team == "Blue")
        {
            gameBoard.getBluePlayer().addUnit(this);
            gameBoard.getBluePlayer().setFood(-1 * foodCost);
            gameBoard.getBluePlayer().setGold(-1 * goldCost);
        }

        if (team == "Red")
        {
            gameBoard.getRedPlayer().addUnit(this);
            gameBoard.getRedPlayer().setFood(-1 * foodCost);
            gameBoard.getRedPlayer().setGold(-1 * goldCost);
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
    public void drawUnit(Graphics g)
    {
        if (isSelected())
        {
            g.setColor(Color.red);
            g.fillRect(gameBoard.convertX(pixelX), gameBoard.convertY(pixelY - 10), 25, 5);
            g.setColor(Color.green);
            g.fillRect(gameBoard.convertX(pixelX), gameBoard.convertY(pixelY - 10), (int) (25 * getHpDown()), 5);
        }
        if (team == "Blue")
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

        if (attacking)
        {
            g.drawImage(attackImg, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2 - 40), null);
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
    public void setHp(int value)
    {
        if (currentHp < maxHp)
        {
            currentHp += value;
        }
        else
        {
            currentHp = maxHp;
        }
    }

    @Override
    public BufferedImage getIcon()
    {
        return swordManIcon;
    }

    @Override
    public void tick()
    {

        if (!isAlive())
        {
            deleteUnit();
        }

        if (checkCloseEnemy())
        {
            timeToNewAttack++;
            if (timeToNewAttack == timeAttack)
            {
                enemy.setHp(-1 * attack);
                gameBoard.getSelectView().repaint();
                timeToNewAttack = 0;
            }

        }

        if (isMoving())
        {
            movePixel();
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

    private boolean checkCloseEnemy()
    {

        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                int x = (getX() / 25) + j;
                int y = (getY() / 25 + 1) + i;

                if (gameBoard.getUnitField(x, y) != null)
                {
                    if (gameBoard.getFieldIndex(x, y) == 11 && (getPlayer() != gameBoard.getUnitField(x, y).getPlayer()))
                    {
                        attacking = true;
                        enemy = gameBoard.getUnitField(x, y);

                        //System.out.println("enemy: " + x + "   " + y);
                        return true;

                    }
                    else
                    {
                        attacking = false;
                        enemy = null;

                    }
                }
            }
        }
        return false;
    }
}
