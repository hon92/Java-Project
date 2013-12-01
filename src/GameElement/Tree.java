package GameElement;

import Data.GameData;
import Data.ImgResources;
import Data.Source;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tree extends ObjectElement implements Source
{

    private BufferedImage tree;
    private static BufferedImage treeIcon;

    private static int startWood;
    private int currentWood;
    private static String name = "Tree";

    public Tree(GameBoard gameBoard, int x, int y, String treename)
    {
        super(gameBoard, x, y);

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
            g.drawImage(tree, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE) + 75, null);
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
        currentWood = count;
    }

    @Override
    public BufferedImage getIcon()
    {
        return treeIcon;
    }
}
