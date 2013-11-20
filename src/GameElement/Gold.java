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
public class Gold extends ObjectElement
{

    private int startGold;
    private int currentGold;
    private BufferedImage gold;

    public Gold(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            gold = ImageIO.read(new File("src/Resources/gold.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.startGold = 500;
        this.currentGold = 500;
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(gold, gameBoard.convertX(x), gameBoard.convertY(y), null);
    }
}
