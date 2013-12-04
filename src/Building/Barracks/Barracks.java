/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Building.Barracks;

import Buildings.Building;
import Buildings.BuildingType;
import View.GameBoard;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adam
 */
public class Barracks extends Building {

    public Barracks(GameBoard gameBoard, int x, int y) {
        super(gameBoard, x, y);
        initBarracks();

    }

    @Override
    public void drawBuilding(Graphics g) {
        if (sourceImg != null) {
            g.drawImage(sourceImg, locationX, locationY, null);
        }
    }

    private void initBarracks() {
        buildingName = "Barracks";
        buildingType = BuildingType.BARRACKS;

        buildTime = 30;
        maxHp = 500;
        currentHp = maxHp;
        try {
            sourceImg = ImageIO.read(new File("src/Building/Barracks/barracks.png"));
            iconImg = ImageIO.read(new File("src/Building/Barracks/barracksicon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Barracks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
