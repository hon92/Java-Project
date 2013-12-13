/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Monk;

import GameElement.Grass;
import Unit.Unit;
import View.GameBoard;
import View.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class Monk extends Unit
{

    private static int attack;
    private static int armor;

    private static BufferedImage monkIcon;

    private static BufferedImage monkDownBlue;
    private static BufferedImage monkTopBlue;
    private static BufferedImage monkLeftBlue;
    private static BufferedImage monkRightBlue;
    private static BufferedImage monkRightBotBlue;
    private static BufferedImage monkRightTopBlue;
    private static BufferedImage monkLeftTopBlue;
    private static BufferedImage monkLeftBotBlue;

    private static BufferedImage monkDownRed;
    private static BufferedImage monkTopRed;
    private static BufferedImage monkLeftRed;
    private static BufferedImage monkRightRed;
    private static BufferedImage monkRightBotRed;
    private static BufferedImage monkRightTopRed;
    private static BufferedImage monkLeftTopRed;
    private static BufferedImage monkLeftBotRed;

    private static int count = 0;
    private int goldCost;
    private List<Unit> units;

    private boolean hasRelic;

    public Monk(GameBoard gameBoard, int x, int y, int dir, String team)
    {
        super(gameBoard, x, y, dir, team);

        maxHp = 100;
        currentHp = 80;
        speed = 1;
        hasRelic = false;
        goldCost = 100;

        actions.add(new MonkPickRelic());

        try
        {

            monkIcon = ImageIO.read(new File("src/Resources/monkImg/monkIcon.png"));

            monkDownBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueDown.png"));
            monkTopBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueTop.png"));
            monkRightBotBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueBotRight.png"));
            monkRightBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueRight.png"));
            monkLeftBotBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueBotLeft.png"));
            monkLeftTopBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueTopLeft.png"));
            monkRightTopBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueTopRight.png"));
            monkLeftBlue = ImageIO.read(new File("src/Resources/monkImg/monkBlueLeft.png"));

            monkDownRed = ImageIO.read(new File("src/Resources/monkImg/monkRedDown.png"));
            monkTopRed = ImageIO.read(new File("src/Resources/monkImg/monkRedTop.png"));
            monkRightBotRed = ImageIO.read(new File("src/Resources/monkImg/monkRedBotRight.png"));
            monkRightRed = ImageIO.read(new File("src/Resources/monkImg/monkRedRight.png"));
            monkLeftBotRed = ImageIO.read(new File("src/Resources/monkImg/monkRedBotLeft.png"));
            monkLeftTopRed = ImageIO.read(new File("src/Resources/monkImg/monkRedTopLeft.png"));
            monkRightTopRed = ImageIO.read(new File("src/Resources/monkImg/monkRedTopRight.png"));
            monkLeftRed = ImageIO.read(new File("src/Resources/monkImg/monkRedLeft.png"));
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
            gameBoard.getBluePlayer().setGold(-1 * goldCost);
        }

        if (team == "Red")
        {
            gameBoard.getRedPlayer().addUnit(this);
            gameBoard.getBluePlayer().setGold(-1 * goldCost);
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
                g.drawImage(monkTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
            else if ((direction >= 78) && (direction < 123))
            {
                g.drawImage(monkDownBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 158) && (direction < 203))
            {
                g.drawImage(monkLeftBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 338) || (direction < 23))
            {
                g.drawImage(monkRightBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction < 338) && (direction >= 292))
            {
                g.drawImage(monkRightTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 203) && (direction < 247))
            {
                g.drawImage(monkLeftTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 123) && (direction < 158))
            {
                g.drawImage(monkLeftBotBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 23) && (direction < 78))
            {
                g.drawImage(monkRightBotBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
        }
        else
        {
            if ((direction >= 247) && (direction < 292))
            {
                g.drawImage(monkTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
            else if ((direction >= 78) && (direction < 123))
            {
                g.drawImage(monkDownRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 158) && (direction < 203))
            {
                g.drawImage(monkLeftRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 338) || (direction < 23))
            {
                g.drawImage(monkRightRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction < 338) && (direction >= 292))
            {
                g.drawImage(monkRightTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 203) && (direction < 247))
            {
                g.drawImage(monkLeftTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 123) && (direction < 158))
            {
                g.drawImage(monkLeftBotRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 23) && (direction < 78))
            {
                g.drawImage(monkRightBotRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
        }
    }

    @Override
    public String getName()
    {
        return "Monk";
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
        if (currentHp <= maxHp)
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
        return monkIcon;
    }

    @Override
    public void tick()
    {

        count++;
        if (count % 20 == 0)
        {
            units = gameBoard.getUnits();
            for (Unit u : units)
            {
                if (this.team == u.getPlayer() && Math.abs((this.getX() / 25) - (u.getX() / 25)) < 10 && Math.abs((this.getY() / 25) - (u.getY() / 25)) < 10)
                {

                    u.setHp(1);
                    MainWindow.botPanel.getSelectPanel().repaint();
                }
            }
        }

        if (count > 999999990)
        {
            count = 0;
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

    @Override
    public void deleteUnit()
    {
        gameBoard.setUnitField(this.getX(), this.getY(), null);
        gameBoard.setUnitField(this.getX(), this.getY() + 1, null);
        gameBoard.setFieldIndex(this.getX(), this.getY() + 1, 0);

        gameBoard.getUnits().remove(this);
    }
}
