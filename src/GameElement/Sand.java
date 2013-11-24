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

public class Sand extends ObjectElement
{

    private static BufferedImage sand;

    public Sand(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            sand = ImageIO.read(new File("src/Resources/sand.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 8; i++)
        {
            if (gameBoard.getFieldIndex(x+i, y)==0)
            {
                gameBoard.setFieldIndex(x+i, y, 8);
            }
            gameBoard.setObjectFieldObject(x + i, y, this);
        }

    }

    @Override
    public String getName()
    {
        return "Sand";
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(sand, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }
}
