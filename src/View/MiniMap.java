package View;

import Data.GameData;
import GameElement.Cactus;
import GameElement.Gold;
import GameElement.Grass;
import GameElement.ObjectElement;
import GameElement.Relic;
import GameElement.Sand;
import GameElement.Shoal;
import GameElement.Stone;
import GameElement.Tree;
import GameElement.Water;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MiniMap extends JPanel
{

    private int sizeWidth = 360;
    private int sizeHeight = 200;
    private GameBoard gameBoard;
    private int x = 0;
    private int y = 0;
    private int scaleX = GameData.MAP_WIDTH / sizeWidth;
    private int scaleY = GameData.MAP_HEIGHT / sizeHeight;
    private int viewRectWidth = (GameData.MAP_WIDTH / GameData.WINDOW_WIDTH) * 10;
    private int viewRectHeight = (GameData.MAP_HEIGHT / GameData.WINDOW_HEIGHT) * 10;
    final private Color brownColor = new Color(129, 39, 3);
    final private Color goldColor = new Color(211, 189, 13);
    final private Color silverColor = new Color(237, 237, 237);
    final private Color greenColor = new Color(8, 135, 40);
    final private Color grayColor = new Color(89, 97, 86);
    private List<ObjectElement> objects;
    private Timer mapRefresh;
    private int refreshDelay = 1000;

    public MiniMap(final GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        setPreferredSize(new Dimension(sizeWidth, sizeHeight));
        objects = new ArrayList<>();

        mapRefresh = new Timer(refreshDelay, new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                objects = gameBoard.getObjects();
                repaint();

            }
        }
        );
        mapRefresh.start();

    }

    private class MouseMiniMap implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
        }

    }

    public void paintComponent(Graphics g)
    {
        g.setColor(brownColor);
        g.fillRect(x, y, sizeWidth, sizeHeight);
        g.setColor(Color.red);

        g.drawRect(x, y, viewRectWidth, viewRectHeight);
        for (ObjectElement ob : objects)
        {
            if (!((ob instanceof Water) || (ob instanceof Sand) || (ob instanceof Cactus) || (ob instanceof Shoal) || (ob instanceof Grass)))
            {

                if (ob instanceof Gold)
                {
                    g.setColor(goldColor);
                }
                if (ob instanceof Relic)
                {
                    g.setColor(silverColor);
                }
                if (ob instanceof Tree)
                {
                    g.setColor(greenColor);
                }
                if (ob instanceof Stone)
                {
                    g.setColor(grayColor);
                }

                int objx = ob.getX();
                int objy = ob.getY();
                g.fillRect(x + convertX(objx), y + convertY(objy), 5, 5);
            }

        }
    }

    void setData(List<ObjectElement> objects
    )
    {
        this.objects = objects;
    }

    private int convertX(int x)
    {
        return x / scaleX;
    }

    private int convertY(int y)
    {
        return y / scaleY;
    }

    public void setAction(int x, int y)
    {

        int newx = GameData.WINDOW_WIDTH - x;
        int newy = GameData.WINDOW_HEIGHT - y;
        System.err.println("X: " + newx + "Y: " + newy);
        int xx = newx + viewRectWidth / 2;
        int yy = newy + viewRectHeight / 2;
        gameBoard.setWindowsX(xx);
        gameBoard.setWindowsY(yy);
        gameBoard.repaint();

    }

    public boolean intersect(int x, int y)
    {
        if (x >= this.x && y >= this.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
