/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

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

    private static int time = 0;
    private static BufferedImage villagerIcon;

    private static BufferedImage villagerDownBlue;
    private static BufferedImage villagerTopBlue;
    private static BufferedImage villagerLeftBlue;
    private static BufferedImage villagerRightBlue;
    private static BufferedImage villagerRightBotBlue;
    private static BufferedImage villagerRightTopBlue;
    private static BufferedImage villagerLeftTopBlue;
    private static BufferedImage villagerLeftBotBlue;

    private static BufferedImage villagerDownRed;
    private static BufferedImage villagerTopRed;
    private static BufferedImage villagerLeftRed;
    private static BufferedImage villagerRightRed;
    private static BufferedImage villagerRightBotRed;
    private static BufferedImage villagerRightTopRed;
    private static BufferedImage villagerLeftTopRed;
    private static BufferedImage villagerLeftBotRed;

    private int foodCost;

    public Villager(GameBoard gameBoard, int x, int y, int dir, String team)
    {
        super(gameBoard, x, y, dir, team);

        maxHp = 50;
        currentHp = 10;
        speed = 2;
        foodCost = 50;
        actions.add(new VillagerAction());
        actions.add(new VillagerFarmAction(this, gameBoard));

        try
        {

            villagerIcon = ImageIO.read(new File("src/Resources/villagerImg/villagerIcon.png"));

            villagerDownBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerDown.png"));
            villagerTopBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerTop.png"));
            villagerRightBotBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerRightBot.png"));
            villagerRightBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerRight.png"));
            villagerLeftBotBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftBot.png"));
            villagerLeftTopBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftTop.png"));
            villagerRightTopBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerRightTop.png"));
            villagerLeftBlue = ImageIO.read(new File("src/Resources/villagerImg/villagerLeft.png"));

            villagerDownRed = ImageIO.read(new File("src/Resources/villagerImg/villagerDownRed.png"));
            villagerTopRed = ImageIO.read(new File("src/Resources/villagerImg/villagerTopRed.png"));
            villagerRightBotRed = ImageIO.read(new File("src/Resources/villagerImg/villagerRightBotRed.png"));
            villagerRightRed = ImageIO.read(new File("src/Resources/villagerImg/villagerRightRed.png"));
            villagerLeftBotRed = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftBotRed.png"));
            villagerLeftTopRed = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftTopRed.png"));
            villagerRightTopRed = ImageIO.read(new File("src/Resources/villagerImg/villagerRightTopRed.png"));
            villagerLeftRed = ImageIO.read(new File("src/Resources/villagerImg/villagerLeftRed.png"));
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
        }

        if (team == "Red")
        {
            gameBoard.getRedPlayer().addUnit(this);
            gameBoard.getRedPlayer().setFood(-1 * foodCost);
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
    public void setHp()
    {
        if (currentHp < maxHp)
        {
            currentHp += 1;
        }
        else
        {
            currentHp = maxHp;
        }
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
                g.drawImage(villagerTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
            else if ((direction >= 78) && (direction < 123))
            {
                g.drawImage(villagerDownBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 158) && (direction < 203))
            {
                g.drawImage(villagerLeftBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 338) || (direction < 23))
            {
                g.drawImage(villagerRightBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction < 338) && (direction >= 292))
            {
                g.drawImage(villagerRightTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 203) && (direction < 247))
            {
                g.drawImage(villagerLeftTopBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 123) && (direction < 158))
            {
                g.drawImage(villagerLeftBotBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 23) && (direction < 78))
            {
                g.drawImage(villagerRightBotBlue, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
        }
        else
        {
            if ((direction >= 247) && (direction < 292))
            {
                g.drawImage(villagerTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
            else if ((direction >= 78) && (direction < 123))
            {
                g.drawImage(villagerDownRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 158) && (direction < 203))
            {
                g.drawImage(villagerLeftRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 338) || (direction < 23))
            {
                g.drawImage(villagerRightRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction < 338) && (direction >= 292))
            {
                g.drawImage(villagerRightTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 203) && (direction < 247))
            {
                g.drawImage(villagerLeftTopRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 123) && (direction < 158))
            {
                g.drawImage(villagerLeftBotRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }

            else if ((direction >= 23) && (direction < 78))
            {
                g.drawImage(villagerRightBotRed, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2), null);
            }
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
        if (time % 25 == 0)
        {
            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 6
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 6
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 6
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 6)

            {
                // System.out.println("tezim jidlo");
                if (team == "Blue")
                {
                    gameBoard.getBluePlayer().setFood(1);
                }
                else
                {
                    gameBoard.getRedPlayer().setFood(1);
                }
            }

            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 9
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 9
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 9
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 9)

            {
                // System.out.println("tezim zlato");
                if (team == "Blue")
                {
                    gameBoard.getBluePlayer().setGold(1);
                }
                else
                {
                    gameBoard.getRedPlayer().setGold(1);
                }
            }

            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 10
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 10
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 10
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 10)

            {
                //System.out.println("tezim sutr");
                if (team == "Blue")
                {
                    gameBoard.getBluePlayer().setStone(1);
                }
                else
                {
                    gameBoard.getRedPlayer().setStone(1);
                }
            }

            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 2
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 2
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 2
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 2)

            {
                //System.out.println("tezim drevo");
                if (team == "Blue")
                {
                    gameBoard.getBluePlayer().setWood(1);
                }
                else
                {
                    gameBoard.getRedPlayer().setWood(1);
                }
            }
        }
        gameBoard.getTopPanel().repaint();

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
}
