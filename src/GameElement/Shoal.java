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

public class Shoal extends ObjectElement
{

    private static BufferedImage shoal;

    public Shoal(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            shoal = ImageIO.read(new File("src/Resources/shoal.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 8; i++)
        {

           // gameBoard.setObjectFieldObject(x + i, y, this);
            //gameBoard.setObjectFieldObject(x + i, y + 1, this);
            if (gameBoard.getFieldIndex(x + i, y) == 0)
            {
                gameBoard.setFieldIndex(x + i, y, 7);
            }
            if (gameBoard.getFieldIndex(x + i, y + 1) == 0)
            {
                gameBoard.setFieldIndex(x + i, y + 1, 7);
            }
        }

    }

    @Override
    public String getName()
    {
        return "Shoal";
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(shoal, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }
}
