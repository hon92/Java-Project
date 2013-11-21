/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
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
public class BotPanel extends JPanel
{

    private static BufferedImage botPanel;
    private GameBoard gameBoard;
    private MiniMap miniMap;

    BotPanel(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        setLayout(new BorderLayout());
        miniMap = new MiniMap(gameBoard);
        add(miniMap, BorderLayout.EAST);
        //add(new MiniMap(gameBoard), BorderLayout.WEST);
        //add(new MiniMap(gameBoard), BorderLayout.CENTER);

        try
        {

            botPanel = ImageIO.read(new File("src/Resources/botPanel.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPreferredSize(new Dimension(botPanel.getWidth(), botPanel.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(botPanel, 0, 0, null);

    }

}
