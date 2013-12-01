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
public class Gold extends ObjectElement implements Source
{

    private static int startGold;
    private int currentGold;
    private static BufferedImage gold;
    private static BufferedImage goldIcon;
    private static String name = "Gold";

    public Gold(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            gold = ImageIO.read(new File("src/Resources/gold.png"));
            goldIcon = ImageIO.read(new File("src/Resources/goldIcon.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.startGold = 500;
        this.currentGold = 500;

        gameBoard.setObjectFieldObject(x, y, this);
        gameBoard.setObjectFieldObject(x + 1, y, this);
        gameBoard.setObjectFieldObject(x + 2, y, this);
        gameBoard.setObjectFieldObject(x, y + 1, this);
        gameBoard.setObjectFieldObject(x + 2, y + 1, this);
        gameBoard.setObjectFieldObject(x + 1, y + 1, this);

        gameBoard.setFieldIndex(x, y, 9);
        gameBoard.setFieldIndex(x + 1, y, 9);
        gameBoard.setFieldIndex(x + 2, y, 9);
        gameBoard.setFieldIndex(x, y + 1, 9);
        gameBoard.setFieldIndex(x + 2, y + 1, 9);
        gameBoard.setFieldIndex(x + 1, y + 1, 9);

    }

    public void drawObject(Graphics g)
    {
        g.drawImage(gold, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public int getRemainingResource()
    {
        return currentGold;
    }

    @Override
    public void setRemainingResource(int count)
    {
        currentGold = count;
    }

    @Override
    public BufferedImage getIcon() {
        return goldIcon;
    }

}
