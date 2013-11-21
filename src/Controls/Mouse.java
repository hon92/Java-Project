/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import View.GameBoard;
import View.MiniMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Honza
 */
public class Mouse implements MouseListener
{

    private GameBoard gameBoard;
    private MiniMap miniMap;

    public Mouse(GameBoard gameBoard, MiniMap miniMap)
    {
        this.gameBoard = gameBoard;
        this.miniMap = miniMap;

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        if (miniMap.intersect(x, y))
        {
            miniMap.setAction(x, y);
        }

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
