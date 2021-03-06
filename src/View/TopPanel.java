/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Honza
 */
public class TopPanel extends JPanel
{

    private static BufferedImage topPanel;

    private GameBoard gameBoard;
    private int refreshDelay = 100;

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
        Timer topPanelRefresh = new Timer(refreshDelay, new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }
        });
        topPanelRefresh.start();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(topPanel, 0, 0, null);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(gameBoard.getBluePlayer().getWood()), 30, 35);

        g.drawString(String.valueOf(gameBoard.getBluePlayer().getFood()), 110, 35);
        g.drawString(String.valueOf(gameBoard.getBluePlayer().getGold()), 180, 35);
        g.drawString(String.valueOf(gameBoard.getBluePlayer().getStone()), 250, 35);
        g.drawString(String.valueOf(gameBoard.getBluePlayer().getActualPop() + "/" + gameBoard.getBluePlayer().getMaxPop()), 330, 35);
        g.dispose();

    }

}
