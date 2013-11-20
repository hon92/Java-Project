package GameElement;

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

    private BufferedImage shoal;

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

    }

    public void drawObject(Graphics g)
    {
        g.drawImage(shoal, gameBoard.convertX(x), gameBoard.convertY(y), null);
    }
}
