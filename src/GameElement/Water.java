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

public class Water extends ObjectElement
{

    private static BufferedImage water;

    public Water(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            water = ImageIO.read(new File("src/Resources/water.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                gameBoard.setFieldIndex(x / 25 + i, y / 25 + j, 4);
            }
        }

    }

    public void drawObject(Graphics g)
    {
        g.drawImage(water, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }
}
