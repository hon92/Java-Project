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
public class Stone
{
    private int startStone;
    private int currentStone;
    private BufferedImage stone;
    private GameBoard mainPanel;
    
    private int x;
    private int y;
    
    
    public Stone(GameBoard mainPanel, int x, int y)
    {
        try
        {
            stone = ImageIO.read(new File("src/Resources/stone.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;
        this.startStone=500;
        this.currentStone=500;
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(stone, mainPanel.convertX(x), mainPanel.convertY(y), null);
    }   
}
