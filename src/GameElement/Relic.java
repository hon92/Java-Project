
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Relic
{

    private BufferedImage relic;
    private GameBoard mainPanel;
    private int x;
    private int y;

    public Relic(GameBoard mainPanel, int x, int y)
    {
        try
        {
            relic = ImageIO.read(new File("src/Resources/relic.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Relic.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mainPanel = mainPanel;
        this.x = x;
        this.y = y;

    }

    public void draw(Graphics g)
    {
        g.drawImage(relic, mainPanel.convertX(x), mainPanel.convertY(y), null);
    }
}
