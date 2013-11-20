package GameElement;

import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Grass
{

    private BufferedImage grass;
    private GameBoard mainPanel;
    private int x;
    private int y;

    public Grass(GameBoard mainPanel, int x, int y)
    {
        try
        {
            grass = ImageIO.read(new File("src/Resources/grass.png"));
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
        g.drawImage(grass, mainPanel.convertX(x), mainPanel.convertY(y), null);
    }
}
