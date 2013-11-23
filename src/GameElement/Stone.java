/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElement;

import Data.GameData;
import View.GameBoard;
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
public class Stone extends ObjectElement
{

    private static int startStone;
    private int currentStone;
    private static BufferedImage stone;
    private static String name = "Stone";

    public Stone(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            stone = ImageIO.read(new File("src/Resources/stone.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.startStone = 500;
        this.currentStone = 500;

        gameBoard.setObjectFieldObject(x, y, this);
        gameBoard.setObjectFieldObject(x + 1, y, this);
        gameBoard.setObjectFieldObject(x + 2, y, this);
        gameBoard.setObjectFieldObject(x, y + 1, this);
        gameBoard.setObjectFieldObject(x + 1, y + 1, this);
        gameBoard.setObjectFieldObject(x + 2, y + 1, this);
        
        gameBoard.setFieldIndex(x, y, 10);
        gameBoard.setFieldIndex(x+1, y, 10);
        gameBoard.setFieldIndex(x+2, y, 10);
        gameBoard.setFieldIndex(x, y+1, 10);
        gameBoard.setFieldIndex(x+1, y+1, 10);
        gameBoard.setFieldIndex(x+2, y+1, 10);
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(stone, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }

    public String getName()
    {
        return name;
    }

    public int getRemainStone()
    {
        return currentStone;
    }
}
