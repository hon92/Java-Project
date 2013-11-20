package View;

import Data.GameData;
import java.awt.Color;
import java.awt.Graphics;

public class MiniMap
{

    private int sizeWidth = 250;
    private int sizeHeight = 160;
    private int x = GameData.WINDOW_WIDTH - sizeWidth - 20;
    private int y = GameData.WINDOW_HEIGHT - sizeHeight - 40;

    public MiniMap()
    {
    }

    public void drawMiniMap(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(x, y, sizeWidth, sizeHeight);

    }
}
