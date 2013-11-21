package View;

import Data.GameData;
import GameElement.ObjectElement;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MiniMap
{

    private int sizeWidth = 350;
    private int sizeHeight = 180;
    private int x = GameData.WINDOW_WIDTH - sizeWidth - 20;
    private int y = GameData.WINDOW_HEIGHT - sizeHeight - 40;
    private List<ObjectElement> objects;

    public MiniMap()
    {
        objects = new ArrayList<>();
    }

    public void drawMiniMap(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(x, y, sizeWidth, sizeHeight);
        g.setColor(Color.red);

        for (ObjectElement ob : objects)
        {

            int objx = ob.getX();
            int objy = ob.getY();
            g.fillRect(x + convertX(objx), y + convertY(objy), 5, 5);

        }

    }

    void setData(List<ObjectElement> objects)
    {
        this.objects = objects;
    }

    private int convertX(int x)
    {
        return x / 10;
    }

    private int convertY(int y)
    {
        return y / 10;
    }
}
