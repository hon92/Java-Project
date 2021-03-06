/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Building.Farm.Farm;
import Buildings.Action;
import Buildings.BuildingType;
import Controls.ListItem;
import GameElement.Bush;
import GameElement.Gold;
import GameElement.Grass;
import GameElement.Stone;
import GameElement.Tree;
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

    private int time = 0;
    private static BufferedImage villagerIcon;

    private static BufferedImage villagerDownBlue;
    private static BufferedImage villagerTopBlue;
    private static BufferedImage villagerLeftBlue;
    private static BufferedImage villagerRightBlue;
    private static BufferedImage villagerRightBotBlue;
    private static BufferedImage villagerRightTopBlue;
    private static BufferedImage villagerLeftTopBlue;
    private static BufferedImage villagerLeftBotBlue;

    private static BufferedImage tezbaDreva;
    private static BufferedImage tezbaZlata;
    private static BufferedImage tezbaSutru;
    private static BufferedImage tezbaJidla;

    private static BufferedImage villagerDownRed;
    private static BufferedImage villagerTopRed;
    private static BufferedImage villagerLeftRed;
    private static BufferedImage villagerRightRed;
    private static BufferedImage villagerRightBotRed;
    private static BufferedImage villagerRightTopRed;
    private static BufferedImage villagerLeftTopRed;
    private static BufferedImage villagerLeftBotRed;

    private int foodCost;

    private boolean wood;
    private boolean farming;
    private boolean food;
    private boolean stone;
    private boolean gold;

    public Villager(GameBoard gameBoard, int x, int y, int dir, String team)
    {
        super(gameBoard, x, y, dir, team);

        maxHp = 50;
        currentHp = 50;
        speed = 2;
        foodCost = 50;
        actions.add(new VillagerAction(gameBoard, BuildingType.BARRACKS, this));
        actions.add(new VillagerAction(gameBoard, BuildingType.CHURCH, this));
        actions.add(new VillagerAction(gameBoard, BuildingType.FARM, this));
        actions.add(new VillagerAction(gameBoard, BuildingType.HOUSE, this));
        //actions.add(new VillagerAction(gameBoard, BuildingType.TOWN_CENTER));

        gold = farming = stone = wood = food = false;
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

            tezbaDreva = ImageIO.read(new File("src/Resources/Villager/wood.png"));
            tezbaZlata = ImageIO.read(new File("src/Resources/Villager/gold.png"));
            tezbaJidla = ImageIO.read(new File("src/Resources/Villager/food.png"));
            tezbaSutru = ImageIO.read(new File("src/Resources/Villager/stone.png"));
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

        if (stone)
        {
            g.drawImage(tezbaSutru, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2 - 30), null);
        }

        if (gold)
        {
            g.drawImage(tezbaZlata, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2 - 30), null);
        }

        if (food || farming)
        {
            g.drawImage(tezbaJidla, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2 - 30), null);
        }

        if (wood)
        {
            g.drawImage(tezbaDreva, gameBoard.convertX(pixelX + 2), gameBoard.convertY(pixelY + 2 - 30), null);
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
        //food=gold=stone=wood = false;

        if (!isAlive())
        {
            deleteUnit();
        }
        time++;
        if (time % 25 == 0)
        {

            if (gameBoard.getBuildingFieldObject(this.getX() / 25, this.getY() / 25 + 1) != null)
            {
                if (gameBoard.getBuildingFieldObject(this.getX() / 25, this.getY() / 25 + 1).getName() == "Farm")
                {
                    // System.out.println("tezim jidlo");
                    if (team == "Blue")
                    {
                        farming = true;
                        gameBoard.getBluePlayer().setFood(1);
                    }
                    else
                    {
                        farming = true;
                        gameBoard.getRedPlayer().setFood(1);
                    }
                    gameBoard.getSelectView().repaint();

                    Farm farm = null;
                    farm = (Farm) gameBoard.getBuildingFieldObject(this.getX() / 25, this.getY() / 25 + 1);
                    farm.setCurrentFood(1);

                    if (farm != null)
                    {
                        if (farm.getCurrentFood() <= 0)
                        {
                            farm.deleteFarm();
                            farming = false;
                        }
                    }
                }
            }
            else
            {
                farming = false;
            }

            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 6
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 6
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 6
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 6)
            {
                // System.out.println("tezim jidlo");
                if (team == "Blue")
                {
                    food = true;
                    gameBoard.getBluePlayer().setFood(1);
                }
                else
                {
                    food = true;
                    gameBoard.getRedPlayer().setFood(1);
                }

                Bush bush = null;

                if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 6)
                {
                    bush = (Bush) gameBoard.getObjectFieldObject(this.getX() / 25 + 1, this.getY() / 25);
                    if (bush!= null)
                    {
                    bush.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 6)
                {
                    bush = (Bush) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 + 2);
                    if (bush!= null)
                    {
                    bush.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 6)
                {
                    bush = (Bush) gameBoard.getObjectFieldObject(this.getX() / 25 - 1, this.getY() / 25);
                    if (bush!= null)
                    {
                    bush.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25) == 6)
                {
                    bush = (Bush) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 - 1);
                    if (bush != null)
                    {
                        bush.setRemainingResource(1);
                    }
                }

                if (bush != null)
                {
                    if (bush.getRemainingResource() == 0)
                    {
                        bush.deleteBush();
                    }
                }
            }
            else
            {
                food = false;
            }

            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 9
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 9
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 9
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 9)
            {

                //System.out.println("tezim drevo");
                if (team == "Blue")
                {
                    gold = true;
                    gameBoard.getBluePlayer().setGold(1);
                }
                else
                {
                    gold = true;
                    gameBoard.getRedPlayer().setGold(1);
                }

                Gold gold = null;
                if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 9)
                {
                    
                    gold = (Gold) gameBoard.getObjectFieldObject(this.getX() / 25 + 1, this.getY() / 25);
                    if(gold!= null)
                    {
                    gold.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 9)
                {
                    gold = (Gold) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 + 2);
                    if(gold!= null)
                    {
                    gold.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 9)
                {
                    gold = (Gold) gameBoard.getObjectFieldObject(this.getX() / 25 - 1, this.getY() / 25);
                    if(gold!= null)
                    {
                    gold.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25) == 9)
                {
                    gold = (Gold) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 - 1);
                    if(gold!= null)
                    {
                    gold.setRemainingResource(1);
                    }
                }

                if (gold != null)
                {
                    if (gold.getRemainingResource() == 0)
                    {
                        gold.deleteGold();
                    }
                }

            }
            else
            {
                gold = false;
            }

            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 10
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 10
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 10
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 10)
            {
                //System.out.println("tezim sutr");
                if (team == "Blue")
                {
                    stone = true;
                    gameBoard.getBluePlayer().setStone(1);
                }
                else
                {
                    stone = true;
                    gameBoard.getRedPlayer().setStone(1);
                }
                Stone stone = null;

                if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 10)
                {
                    stone = (Stone) gameBoard.getObjectFieldObject(this.getX() / 25 + 1, this.getY() / 25);
                    if (stone !=null)
                    {
                    stone.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 10)
                {
                    stone = (Stone) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 + 2);
                    if (stone !=null)
                    {
                    stone.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 10)
                {
                    stone = (Stone) gameBoard.getObjectFieldObject(this.getX() / 25 - 1, this.getY() / 25);
                    if (stone !=null)
                    {
                    stone.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25) == 10)
                {
                    stone = (Stone) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 - 1);
                    if (stone !=null)
                    {
                    stone.setRemainingResource(1);
                    }
                }

                if (stone != null)
                {
                    if (stone.getRemainingResource() == 0)
                    {
                        stone.deleteStone();
                    }
                }
            }
            else
            {
                stone = false;
            }
            if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 2
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 2
                    || gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 2
                    || gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 - 1) == 2)
            {
                //System.out.println("tezim drevo");
                if (team == "Blue")
                {
                    wood = true;
                    gameBoard.getBluePlayer().setWood(1);
                }
                else
                {
                    wood = true;
                    gameBoard.getRedPlayer().setWood(1);
                }
                Tree tree = null;

                if (gameBoard.getFieldIndex(this.getX() / 25 + 1, this.getY() / 25) == 2)
                {
                    tree = (Tree) gameBoard.getObjectFieldObject(this.getX() / 25 + 1, this.getY() / 25);
                    if (tree !=null)
                    {
                    tree.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25 + 2) == 2)
                {
                    tree = (Tree) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 + 2);

                    if (tree !=null)
                    {
                    tree.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25 - 1, this.getY() / 25) == 2)
                {
                    tree = (Tree) gameBoard.getObjectFieldObject(this.getX() / 25 - 1, this.getY() / 25);
                    if (tree !=null)
                    {
                    tree.setRemainingResource(1);
                    }
                }
                if (gameBoard.getFieldIndex(this.getX() / 25, this.getY() / 25) == 2)
                {
                    tree = (Tree) gameBoard.getObjectFieldObject(this.getX() / 25, this.getY() / 25 - 1);
                    if (tree !=null)
                    {
                    tree.setRemainingResource(1);
                    }
                }

                if (tree != null)
                {
                    if (tree.getRemainingResource() == 0)
                    {
                        tree.deleteTree();
                    }
                }

            }
            else
            {
                wood = false;
            }

        }

        if (time > 999999999)
        {
            time = 0;
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

//    @Override
//    public void deleteUnit()
//    {
//        gameBoard.setUnitField(this.getX(), this.getY(), null);
//        gameBoard.setUnitField(this.getX(), this.getY() + 1, null);
//        gameBoard.setFieldIndex(this.getX(), this.getY() + 1, 0);
//
//        gameBoard.getUnits().remove(this);
//    }
}
