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
public class Bush
{
    private int maxFood;
    private int currentFood;
    private BufferedImage bush;
    private GameBoard mainPanel;
    
    private int x;
    private int y;
    
    
    public Bush(GameBoard mainPanel, int x, int y)
    {
        try
        {
            bush = ImageIO.read(new File("src/Resources/bush.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;
        this.maxFood=300;
        this.currentFood=300;
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(bush, mainPanel.convertX(x), mainPanel.convertY(y), null);
    }   
}
