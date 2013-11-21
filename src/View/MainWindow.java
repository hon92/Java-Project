package View;

import Data.GameData;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainWindow extends JFrame
{

    private TopPanel topPanel;
    private BotPanel botPanel;
    private GameBoard gameBoard;

    public MainWindow() throws HeadlessException
    {
        super("Age of Empires The ZIHOMO Expansion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT));
        setResizable(false);
        initWindow();
        add(topPanel, BorderLayout.NORTH);
        add(gameBoard, BorderLayout.CENTER);
        add(botPanel, BorderLayout.SOUTH);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        try
        {
            Cursor cursor;
            cursor = toolkit.createCustomCursor(ImageIO.read(new File("src/Resources/cursor.png")), new Point(16, 16), "img");
            getRootPane().setCursor(cursor);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initWindow()
    {
        gameBoard = new GameBoard();
        topPanel = new TopPanel(gameBoard);
        botPanel = new BotPanel(gameBoard);
    }

    public static void main(String args[])
    {

        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainWindow().setVisible(true);
            }
        });
    }
}
