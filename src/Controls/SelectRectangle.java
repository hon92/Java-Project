/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import View.GameBoard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Honza
 */
@Deprecated
public class SelectRectangle
{

    private GameBoard gameBoard;
    private Thread t;
    int currx = 0;
    int curry = 0;
    int currwidth = 0;
    int currheight = 0;

    public SelectRectangle(final GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        t = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                gameBoard.addMouseListener(new Mouse());
                System.err.println("wdqd");

            }
        }, "mouse");
        t.start();
    }

    private class Mouse implements MouseMotionListener, MouseListener
    {

        @Override
        public void mouseDragged(MouseEvent e)
        {
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
//            currx = e.getX();
//            curry = e.getY();
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

}
