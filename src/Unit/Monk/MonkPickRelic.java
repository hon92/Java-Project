/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Monk;

import Buildings.Action;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class MonkPickRelic extends Action
{

    public MonkPickRelic()
    {
        initAction();
    }

    @Override
    protected void initAction()
    {
        try
        {
            actionImage = ImageIO.read(new File("src/Unit/Monk/relicPickUp.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(MonkPickRelic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doAction()
    {
        System.err.println("akce");
    }

    @Override
    public void cancelAction()
    {
        System.out.println("storno");
    }

    @Override
    public void tick()
    {
    }

}
