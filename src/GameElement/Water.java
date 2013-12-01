package GameElement;

import Data.GameData;
import Data.ImgResources;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Water extends ObjectElement
{

    private static BufferedImage water;

    public Water(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        water = ImgResources.getImg("water");

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                gameBoard.setFieldIndex(x + i, y + j, 4);
            }
        }

    }

    @Override
    public String getName()
    {
        return "Water";
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(water, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }
}
