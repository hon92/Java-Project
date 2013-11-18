/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import View.GameBoard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Honza
 */
public class Mouse implements MouseListener
{

    private GameBoard gameBoard;

    public Mouse(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("ccc");
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
