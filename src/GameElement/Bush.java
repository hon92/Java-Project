/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElement;

import Data.GameData;
import Data.Source;
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
public class Bush extends ObjectElement implements Source
{

    private static int startFood;
    private int currentFood;
    private static BufferedImage bush;
    private static BufferedImage bushIcon;
    private static String name = "Bush";

    public Bush(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            bush = ImageIO.read(new File("src/Resources/bush.png"));
            bushIcon = ImageIO.read(new File("src/Resources/bushIcon.png"));
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

        gameBoard.setFieldIndex(x, y, 6);
        gameBoard.setFieldIndex(x + 1, y, 6);
        gameBoard.setFieldIndex(x + 2, y, 6);
        gameBoard.setFieldIndex(x, y + 1, 6);
        gameBoard.setFieldIndex(x + 1, y + 1, 6);
        gameBoard.setFieldIndex(x + 2, y + 1, 6);
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

    @Override
    public int getRemainingResource()
    {
        return currentFood;
    }

    @Override
    public void setRemainingResource(int count)
    {
        currentFood -= count;
    }

    @Override
    public BufferedImage getIcon() {
        return bushIcon;
    }

}
