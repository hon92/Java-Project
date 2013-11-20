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

public class Tree
{

    private BufferedImage tree1;
    //private BufferedImage tree2;
    private GameBoard mainPanel;
    private int x;
    private int y;
    private int maxWood;
    private int currentWood;

    public Tree(GameBoard mainPanel, int x, int y)
    {
        try
        {
            tree1 = ImageIO.read(new File("src/Resources/tree1.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;
        this.currentWood=100;
        this.maxWood=100;
        
        
    }

    public void draw(Graphics g)
    {
        if ((x / GameData.BOXSIZE == 0) && (y / GameData.BOXSIZE == 0))
        {
            g.drawImage(tree1, mainPanel.convertX(x), mainPanel.convertY(y), null);
        }
        else
        {
            g.drawImage(tree1, mainPanel.convertX(x), mainPanel.convertY(y), null);
        }
    }
}
