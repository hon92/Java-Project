/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Data.ImgResources;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class ActionView extends JPanel
{

    private BufferedImage background;
    private GameBoard gameBoard;

    public ActionView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        background = ImgResources.getImg("actionView");
        this.gameBoard = gameBoard;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.dispose();

    }

}
