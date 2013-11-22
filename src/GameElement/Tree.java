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

    private static BufferedImage tree1;
    private static BufferedImage tree2;
    private static BufferedImage gayTree;
    private static BufferedImage deadTree;
    private static BufferedImage treeDown;
    private int startWood;
    private int currentWood;
    private static String name = "Tree";

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

        gameBoard.setObjectFieldObject(x, y, this);
        gameBoard.setObjectFieldObject(x, y + 1, this);
        gameBoard.setObjectFieldObject(x, y + 2, this);
        gameBoard.setObjectFieldObject(x, y + 3, this);
        gameBoard.setObjectFieldObject(x + 1, y, this);
        gameBoard.setObjectFieldObject(x + 1, y + 1, this);
        gameBoard.setObjectFieldObject(x + 1, y + 2, this);
        gameBoard.setObjectFieldObject(x + 1, y + 3, this);

    }

    public void drawObject(Graphics g)
    {
        if (currentWood < 100)
        {
            g.drawImage(tree1, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE) + 75, null);
        }
        else
        {
            if ((((x + y) / 25) % 4 == 3))
            {
                g.drawImage(tree1, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
            }
            else if (((x + y) / 25) % 4 == 2)
            {
                g.drawImage(tree2, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
            }
            else
            {
                g.drawImage(gayTree, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
            }
        }
    }

    public String getName()
    {
        return name;
    }

    public int getRemainingWood()
    {
        return currentWood;
    }
}
