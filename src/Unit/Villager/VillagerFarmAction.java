/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Buildings.Action;
import Unit.Unit;
import View.GameBoard;
import View.MainWindow;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class VillagerFarmAction extends Action
{

    private Unit unit;

    public VillagerFarmAction(Unit unit, GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        this.unit = unit;
        initAction();
    }

    @Override
    protected void initAction()
    {

        try
        {
            actionImage = ImageIO.read(new File("src/Unit/Villager/harvest.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(VillagerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doAction()
    {
        System.out.println("Click on map resource");
        Villager villager = (Villager) unit;
        gameBoard.getRootWindow().setCursor("harvest");

    }

    @Override
    public void cancelAction()
    {
    }

    @Override
    public void tick()
    {
    }

}
