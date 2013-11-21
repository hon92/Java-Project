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
    private BufferedImage tree2;
    private BufferedImage gayTree;
    private BufferedImage deadTree;
    private BufferedImage treeDown;
    private int startWood;
    private int currentWood;

    public Tree(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            tree1 = ImageIO.read(new File("src/Resources/tree1.png"));
            tree2 = ImageIO.read(new File("src/Resources/tree2_1.png"));
            gayTree = ImageIO.read(new File("src/Resources/gayTree.png"));
            deadTree = ImageIO.read(new File("src/Resources/deadTree.png"));
            treeDown = ImageIO.read(new File("src/Resources/treeDown.png"));
            
        }
        catch (IOException ex)
        {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.currentWood = 100;
        this.startWood = 100;
        
        gameBoard.setFieldIndex(x/25, y/25+2, 2);
        gameBoard.setFieldIndex(x/25+1, y/25+2, 2);
        
        gameBoard.setFieldIndex(x/25, y/25+3, 2);
        gameBoard.setFieldIndex(x/25+1, y/25+3, 2);
        

    }

    public void drawObject(Graphics g)
    {
        if(currentWood<100)
        {
            g.drawImage(tree1, gameBoard.convertX(x), gameBoard.convertY(y)+75, null);
        }
        else
        {
            if ((((x+y)/25) % 4 == 3))
               {
                g.drawImage(tree1, gameBoard.convertX(x), gameBoard.convertY(y), null);
               }
            else if(((x+y)/25) % 4==2)
               {
               g.drawImage(tree2, gameBoard.convertX(x), gameBoard.convertY(y), null);
               }
            else if (((x+y)/25) % 4 == 1)
            {
                g.drawImage(deadTree, gameBoard.convertX(x), gameBoard.convertY(y), null);
            }
            else
            {
                g.drawImage(gayTree, gameBoard.convertX(x), gameBoard.convertY(y), null);
            }
        }   
    }
}
