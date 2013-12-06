//*

package Building.Church;

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
public class Church extends Building {

    
    public Church(GameBoard gameBoard, int x, int y,String team) {
        super(gameBoard, x, y,team);
        initTownCenter();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
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
        buildingType = BuildingType.CHURCH;

        actions.add(new TrainMonk(gameBoard, this));

        buildTime = 30;
        maxHp = 500;
        currentHp = maxHp;
        try {
            if(team == "Blue")
            {
            sourceImg = ImageIO.read(new File("src/Building/Church/churchBlue.png"));
            }
            else
            {
            sourceImg = ImageIO.read(new File("src/Building/Church/churchRed.png"));
            }
            iconImg = ImageIO.read(new File("src/Building/Church/churchIcon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Church.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getName() {
        return "Church";
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
