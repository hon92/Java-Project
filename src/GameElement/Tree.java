package GameElement;

import Data.GameData;
import Data.ImgResources;
import Data.Source;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Tree extends ObjectElement implements Source
{

    private BufferedImage tree;
    private static BufferedImage treeIcon;

    private static int startWood;
    private int currentWood;
    private static String name = "Tree";
    private static BufferedImage deathTree;
    
    public Tree(GameBoard gameBoard, int x, int y, String treename)
    {
        super(gameBoard, x, y);
        try {
            deathTree = ImageIO.read(new File("src/Resources/treeDown.png"));
        } catch (IOException ex) {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }
        tree = ImgResources.getImg(treename);
        treeIcon = ImgResources.getImg("treeIcon");
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

        gameBoard.setFieldIndex(x, y + 2, 2);
        gameBoard.setFieldIndex(x + 1, y + 2, 2);

        gameBoard.setFieldIndex(x, y + 3, 2);
        gameBoard.setFieldIndex(x + 1, y + 3, 2);

    }

    public void drawObject(Graphics g)
    {
        if (currentWood < 100)
        {
            g.drawImage(deathTree, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE) + 75, null);
        }
        else
        {
            if ((((x + y) / 25) % 4 == 3))
            {
                g.drawImage(tree, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
            }
            else if (((x + y) / 25) % 4 == 2)
            {
                g.drawImage(tree, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
            }
            else
            {
                g.drawImage(tree, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
            }
        }
    }

    public void deleteTree()
    {
        gameBoard.setObjectFieldObject(x, y, null);
        gameBoard.setObjectFieldObject(x, y + 1, null);
        gameBoard.setObjectFieldObject(x, y + 2, null);
        gameBoard.setObjectFieldObject(x, y + 3, null);
        gameBoard.setObjectFieldObject(x + 1, y, null);
        gameBoard.setObjectFieldObject(x + 1, y + 1, null);
        gameBoard.setObjectFieldObject(x + 1, y + 2, null);
        gameBoard.setObjectFieldObject(x + 1, y + 3, null);

        gameBoard.setFieldIndex(x, y + 2, 0);
        gameBoard.setFieldIndex(x + 1, y + 2, 0);

        gameBoard.setFieldIndex(x, y + 3, 0);
        gameBoard.setFieldIndex(x + 1, y + 3, 0); 
        
        gameBoard.getObjects().remove(this);
    }
    
    public String getName()
    {
        return name;
    }

    @Override
    public int getRemainingResource()
    {
        return currentWood;
    }

    @Override
    public void setRemainingResource(int count)
    {
        currentWood-= count;
    }

    @Override
    public BufferedImage getIcon()
    {
        return treeIcon;
    }
}
