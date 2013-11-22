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
public class Bush extends ObjectElement
{

    private int startFood;
    private int currentFood;
    private static BufferedImage bush;
    private static String name = "Bush";

    public Bush(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            bush = ImageIO.read(new File("src/Resources/bush.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.startFood = 300;
        this.currentFood = 300;

        gameBoard.setObjectFieldObject(x, y, this);
        gameBoard.setObjectFieldObject(x + 1, y, this);
        gameBoard.setObjectFieldObject(x + 2, y, this);
        gameBoard.setObjectFieldObject(x, y + 1, this);
        gameBoard.setObjectFieldObject(x + 1, y + 1, this);
        gameBoard.setObjectFieldObject(x + 2, y + 1, this);
    }

    @Override
    public void drawObject(Graphics g)
    {
        g.drawImage(bush, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }

    public String getName()
    {
        return name;
    }

    public int getRemainingFood()
    {
        return currentFood;
    }

}
