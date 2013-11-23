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

public class Relic extends ObjectElement
{

    private static BufferedImage relic;
    private String name = "Relic";

    public Relic(GameBoard gameBoard, int x, int y)
    {
        super(gameBoard, x, y);
        try
        {
            relic = ImageIO.read(new File("src/Resources/relic.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Relic.class.getName()).log(Level.SEVERE, null, ex);
        }

        gameBoard.setObjectFieldObject(x, y, this);
        gameBoard.setObjectFieldObject(x, y + 1, this);
        
        gameBoard.setFieldIndex(x, y, 3);
        gameBoard.setFieldIndex(x, y+1, 3);
    }

    public void drawObject(Graphics g)
    {
        g.drawImage(relic, gameBoard.convertX(x * GameData.BOXSIZE), gameBoard.convertY(y * GameData.BOXSIZE), null);
    }

    public String getName()
    {
        return name;
    }

}
