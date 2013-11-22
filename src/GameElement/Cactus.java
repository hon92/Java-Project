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
public class Cactus extends ObjectElement
{

    private static BufferedImage cactus;
    private static String name = "Cactus";

    public Cactus(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            cactus = ImageIO.read(new File("src/Resources/cactus.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        gameBoard.setFieldIndex(x / 25, y / 25, 5);
        gameBoard.setObjectFieldObject(x, y, this);
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(cactus, gameBoard.convertX(x), gameBoard.convertY(y), null);
    }

    public String getName()
    {
        return name;
    }
}
