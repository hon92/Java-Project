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
        //TODO
    }

    void setData(List<ObjectElement> objects)
    {
        this.objects = objects;
    }

    private int convertX(int x)
    {
        return 1;
    }

    private int convertY(int y)
    {
        return 1;
    }
}
