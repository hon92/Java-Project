/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static javax.swing.SwingUtilities.isLeftMouseButton;

/**
 *
 * @author Honza
 */
public class Mouse implements MouseListener, MouseMotionListener
{

    private GameBoard gameBoard;

    Thread testingThread;

    private Color dragColor = new Color(0, 255, 50, 128);

    private int clickedX = 0;
    private int clickedY = 0;

    private int currentX = 0;
    private int currentY = 0;

    private boolean active = false;
    private boolean firstDrag = false;

    public Mouse(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

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
        active = false;
        firstDrag = false;
        clickedX = 0;
        clickedY = 0;
        currentX = 0;
        currentY = 0;
        gameBoard.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public void drawRect(Graphics g)
    {
        if (active)
        {
            g.setColor(dragColor);
            g.fillRect(clickedX, clickedY, currentX - clickedX, currentY - clickedY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (isLeftMouseButton(e))
        {
            active = true;
            if (!firstDrag)
            {
                clickedX = e.getX();
                clickedY = e.getY();
                firstDrag = true;
            }
            currentX = e.getX();
            currentY = e.getY();
        }
        gameBoard.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }
}
