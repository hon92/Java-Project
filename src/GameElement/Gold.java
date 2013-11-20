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
public class Gold
{
    private int maxGold;
    private int currentGold;
    private BufferedImage gold;
    private GameBoard mainPanel;
    
    private int x;
    private int y;
    
    
    public Gold(GameBoard mainPanel, int x, int y)
    {
        try
        {
            gold = ImageIO.read(new File("src/Resources/gold.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;
        this.maxGold=500;
        this.currentGold=500;
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(gold, mainPanel.convertX(x), mainPanel.convertY(y), null);
    }   
}
