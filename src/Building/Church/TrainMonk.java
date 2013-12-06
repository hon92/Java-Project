/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Building.Church;

import Buildings.Action;
import Buildings.Building;
import Unit.Monk.Monk;
import Unit.Unit;
import Unit.Villager.Villager;
import View.GameBoard;
import View.MainWindow;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adam
 */
public class TrainMonk extends Action
{
    File res_pop = new File("src/Sounds/resources_pop.wav");
    private int spawnX;
    private int spawnY;
    private int spawnLines;

    public TrainMonk(GameBoard gameBoard, Building building)
    {
        super();
        initAction();
        this.gameBoard = gameBoard;
        this.building = building;
    }

    @Override
    protected void initAction()
    {
        actionName = "Train Monk";
        tickCount = -1;
        remaining = -1;
        try
        {
            actionImage = ImageIO.read(new File("src/Building/Church/monkIcon2.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(TrainMonk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doAction()
    {
        if(building.getPlayer()=="Blue")
        {
            if(
                (gameBoard.getBluePlayer().getActualPop()<gameBoard.getBluePlayer().getMaxPop())&&
                    (gameBoard.getBluePlayer().getGold()>=100))
        {
        loadSpawn();
        if (!isActive)
        {
            isActive = true;
        }
        }
            else
        {
                gameBoard.playSound(res_pop);  
        }
        }
        gameBoard.getTopPanel().repaint();
        
    }

    @Override
    public void tick()
    {
        if (isActive)
        {
            remaining = Monk.getCreateTime() * 60 - tickCount;
            if (tickCount > Monk.getCreateTime() * 60)
            {
                boolean isFree = true;
                for (Unit unit : gameBoard.getUnits())
                {
                    if ((unit.getX() == spawnX * 25) && (unit.getY() == spawnY * 25))
                    {
                        isFree = false;
                        break;
                    }
                }

                if (isFree) {
                    gameBoard.getUnits().add(new Monk(gameBoard, spawnX, spawnY, 0,"Blue"));
                    isActive = false;
                    tickCount = 0;
                    spawnLines++;
                }
                else
                {
                    spawnX += 1;
                }
            }
            tickCount++;
            MainWindow.botPanel.getActionPanel().repaint();
        }
        else if (MainWindow.botPanel != null)
        {
            MainWindow.botPanel.getActionPanel().repaint();
        }
    }

    @Override
    public void cancelAction()
    {
        isActive = false;
        tickCount = 0;
        remaining = -1;
    }

    private void loadSpawn()
    {
        spawnX = building.getLocationX() + (building.getSourceImg().getWidth() / 25);
        spawnY = building.getLocationY() + (building.getSourceImg().getHeight() / 25);
    }
}
