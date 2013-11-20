package View;

import Data.GameData;
import java.awt.Color;
import java.awt.Graphics;

public class MiniMap
{

    public MiniMap()
    {
    }

    private int x = GameData.WINDOW_HEIGHT - 240;
    private int y = GameData.WINDOW_WIDTH + 1000;

    public void drawMiniMap(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(x, y, 200, 200);

    }
}
