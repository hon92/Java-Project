/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class ActionView extends JPanel
{

    private GameBoard gameBoard;

    public ActionView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        setBackground(new Color(0, 0, 0, 32));
        this.gameBoard = gameBoard;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.dispose();

    }

}
