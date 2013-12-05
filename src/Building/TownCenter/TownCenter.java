//*

package Building.TownCenter;

import Buildings.Building;
import Buildings.BuildingType;
import Data.GameData;
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
 * @author Tom
 */
public class TownCenter extends Building {

    
    public TownCenter(GameBoard gameBoard, int x, int y,String team) {
        super(gameBoard, x, y,team);
        initTownCenter();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                gameBoard.setBuildingObjectField(x + i, y + j, this);
                gameBoard.setFieldIndex(x + i, y + j, 15);
            }
        }
        
        if(team == "Blue")
        {
            gameBoard.getBluePlayer().addBuilding(this);
        }
        
        if(team == "Red")
        {
            gameBoard.getRedPlayer().addBuilding(this);
        }
        

    }

    @Override
    public void drawBuilding(Graphics g) {
        if (isSelected()) {
            int imageWidth = sourceImg.getWidth();

            g.setColor(Color.red);
            g.fillRect(gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE - 10), imageWidth, 5);
            g.setColor(Color.green);
            g.fillRect(gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE - 10), (int) (imageWidth * getHpDown()), 5);
        }
        
       if (sourceImg != null) {
            g.drawImage(sourceImg, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
        }
            
        
    }

    private void initTownCenter() {
        buildingName = "Town Center";
        buildingType = BuildingType.TOWN_CENTER;

        actions.add(new TrainVillager(gameBoard, this));

        buildTime = 30;
        maxHp = 500;
        currentHp = maxHp;
        try {
            if(team == "Blue")
            {
            sourceImg = ImageIO.read(new File("src/Building/TownCenter/TownCenter.png"));
            }
            else
            {
            sourceImg = ImageIO.read(new File("src/Building/TownCenter/TownCenterRed.png"));
            }
            iconImg = ImageIO.read(new File("src/Building/TownCenter/TownCenterIcon.png"));
        } catch (IOException ex) {
            Logger.getLogger(TownCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getName() {
        return "Town Center";
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public int getCurrentHp() {
        return currentHp;
    }

    @Override
    public BufferedImage getIcon() {
        return iconImg;
    }

    @Override
    public void tick() {
        
    }

}
