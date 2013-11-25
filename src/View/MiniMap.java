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
import java.awt.event.MouseMotionListener;
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
    final private Color blueColor = new Color(136, 206, 250);
    private List<ObjectElement> objects;
    private Timer mapRefresh;
    private int refreshDelay = 100;

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

        MouseMiniMap mouse = new MouseMiniMap();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

    }

    private class MouseMiniMap implements MouseListener, MouseMotionListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            int mapx = e.getX();
            int mapy = e.getY();

            x = mapx - viewRectWidth / 2;
            y = mapy - viewRectHeight / 2;

            int setx = mapx * scaleX - GameData.WINDOW_WIDTH / 2;
            int sety = mapy * scaleY - GameData.WINDOW_HEIGHT / 2;

            gameBoard.setWindowsX(setx);
            gameBoard.setWindowsY(sety);
            gameBoard.repaint();
            repaint();

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

        @Override
        public void mouseDragged(MouseEvent e)
        {
            int mapx = e.getX();
            int mapy = e.getY();

            x = mapx - viewRectWidth / 2;
            y = mapy - viewRectHeight / 2;
            int setx = mapx * scaleX - GameData.WINDOW_WIDTH / 2;
            int sety = mapy * scaleY - GameData.WINDOW_HEIGHT / 2;
            gameBoard.setWindowsX(setx);
            gameBoard.setWindowsY(sety);
            gameBoard.repaint();
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
        }

    }

    public void paintComponent(Graphics g)
    {
        g.setColor(brownColor);
        g.fillRect(0, 0, sizeWidth, sizeHeight);

        for (ObjectElement ob : objects)
        {
            if (!((ob instanceof Sand) || (ob instanceof Cactus) || (ob instanceof Shoal) || (ob instanceof Grass)))
            {

                if (ob instanceof Water)
                {
                    g.setColor(blueColor);
                }
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
                g.fillRect(0 + convertX(objx * 25), 0 + convertY(objy * 25), 5, 5);
                if (ob instanceof Water)
                {
                    g.fillRect(0 + convertX((objx) * 25), 0 + convertY(objy * 25), 11, 11);
                }

            }
        }
        int currx = gameBoard.getCurrWinX() / scaleX;
        int curry = gameBoard.getCurrWinY() / scaleY;
        x = currx;
        y = curry;
        g.setColor(Color.black);
        g.drawRect(x, y, viewRectWidth, viewRectHeight);

        g.dispose();
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
