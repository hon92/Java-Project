package GameElement;

import Data.ImgResources;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Grass extends ObjectElement
{

    private static BufferedImage grass;

    public Grass(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);

        grass = ImgResources.getImg("grass");

//        for (int i = 0; i < 16; i++)
//        {
//            for (int j = 0; j < 16; j++)
//            {
//                gameBoard.setFieldIndex(x/25 + i, y/25  + j, 1);
//            }
//        }
    }

    @Override
    public String getName()
    {
        return "Grass";
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(grass, gameBoard.convertX(x), gameBoard.convertY(y), null);
    }
}
