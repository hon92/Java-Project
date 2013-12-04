/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Building.Barracks;

import Buildings.Action;
import Buildings.Building;
import Data.GameData;
import Unit.Unit;
import Unit.Villager.Villager;
import View.GameBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author Adam
 */
public class TrainSwordman extends Action {

    public TrainSwordman(GameBoard gameBoard, Building building) {
        super();
        initAction();
        this.gameBoard = gameBoard;
        this.building = building;
    }

    @Override
    protected void initAction() {
        actionName = "Train Swordman";
        try {
            actionImage = ImageIO.read(new File("src/Building/Barracks/trainSwordman.png"));
        } catch (IOException ex) {
            Logger.getLogger(TrainSwordman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ;
    
    @Override
    public void doAction() {
        if (!isActive) {
            isActive = true;
            train(Villager.getCreateTime());
        }
    }

    public void train(int createTime) {
        final int creatiTimeFinal = createTime;
        Timer timer = new Timer(1000, new ActionListener() {
            int tickCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tickCount > creatiTimeFinal){
                   gameBoard.getUnits().add(new Villager(gameBoard, building.getLocationX() + (building.getSourceImg().getWidth() / 25), building.getLocationY() + (building.getSourceImg().getHeight() / 25), 0));
                   isActive = false;
                }
                tickCount++;
            }
        });
        timer.start();
    }
;
}
