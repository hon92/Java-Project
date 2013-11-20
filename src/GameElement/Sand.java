package GameElement;

import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Sand
{

    private BufferedImage sand;
    private GameBoard mainPanel;
    private int x;
    private int y;

    public Sand(GameBoard mainPanel, int x, int y)
    {
        try
        {
            sand = ImageIO.read(new File("src/Resources/sand.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;

    }

    public void draw(Graphics g)
    {
        g.drawImage(sand, mainPanel.convertX(x), mainPanel.convertY(y), null);
    }
}
