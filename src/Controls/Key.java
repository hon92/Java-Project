/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import View.GameBoard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Honza
 */
public class Key implements KeyListener
{

    private GameBoard gameBoard;

    public Key(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("ppp");
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

}
