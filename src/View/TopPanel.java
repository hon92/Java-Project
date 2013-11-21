/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class TopPanel extends JPanel
{

    private static BufferedImage topPanel;

    private GameBoard gameBoard;

    TopPanel(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        try
        {
            topPanel = ImageIO.read(new File("src/Resources/topPanel.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPreferredSize(new Dimension(topPanel.getWidth(), topPanel.getHeight()));

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(topPanel, 0, 0, null);

    }

}
