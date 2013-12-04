/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Building.Barracks;

import Buildings.Action;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adam
 */
public class TrainSwordman extends Action{

    public TrainSwordman() {
        super();
        initAction();
    }

    @Override
    protected void initAction(){
        actionName = "Train Swordman";
        try {
            actionImage = ImageIO.read(new File("src/Building/Barracks/trainSwordman.png"));
        } catch (IOException ex) {
            Logger.getLogger(TrainSwordman.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    @Override
    protected void doAction() {
        
    }
    
}
