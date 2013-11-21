/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElement;

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
    private static String name="Bush";

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
        gameBoard.setFieldIndex(x/25, y/25, 6);
        gameBoard.setFieldIndex(x/25+1, y/25, 6);
        gameBoard.setFieldIndex(x/25+2, y/25, 6);
        gameBoard.setFieldIndex(x/25, y/25+1, 6);
        gameBoard.setFieldIndex(x/25+1, y/25+1, 6);
        gameBoard.setFieldIndex(x/25+2, y/25+1, 6);
        
        gameBoard.setObjectFieldObject(x/25, y/25, this);
        gameBoard.setObjectFieldObject(x/25+1, y/25, this);
        gameBoard.setObjectFieldObject(x/25+2, y/25, this);
        gameBoard.setObjectFieldObject(x/25, y/25, this);
        gameBoard.setObjectFieldObject(x/25+1, y/25+1, this);
        gameBoard.setObjectFieldObject(x/25+2, y/25+1, this);
    }
 
    
    @Override
    public void drawObject(Graphics g)
    {
        g.drawImage(bush, gameBoard.convertX(x), gameBoard.convertY(y), null);
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
