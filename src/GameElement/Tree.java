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

public class Tree extends ObjectElement
{

    private BufferedImage tree1;
    private int startWood;
    private int currentWood;

    public Tree(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            tree1 = ImageIO.read(new File("src/Resources/tree1.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.currentWood = 100;
        this.startWood = 100;

    }

    public void drawObject(Graphics g)
    {
        if ((x / GameData.BOXSIZE == 0) && (y / GameData.BOXSIZE == 0))
        {
            g.drawImage(tree1, gameBoard.convertX(x), gameBoard.convertY(y), null);
        }
        else
        {
            g.drawImage(tree1, gameBoard.convertX(x), gameBoard.convertY(y), null);
        }
    }
}
