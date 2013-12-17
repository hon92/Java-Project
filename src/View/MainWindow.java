package View;

import Data.GameData;
import Data.ImgResources;
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

    public static ImgResources imgResources = new ImgResources();
    public static TopPanel topPanel;
    public static BotPanel botPanel;
    public static GameBoard gameBoard;
    private Cursor cursor;
    private Toolkit toolkit;

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
        setLocationRelativeTo(null);

        toolkit = Toolkit.getDefaultToolkit();
        try
        {

            cursor = toolkit.createCustomCursor(ImageIO.read(new File("src/Resources/cursor.png")), new Point(3, 3), "img");
            getRootPane().setCursor(cursor);

        }
        catch (IOException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Cursor getCursor()
    {
        return cursor;
    }

    public void setCursor(String cursorName)
    {
        try
        {
            cursor = toolkit.createCustomCursor(ImageIO.read(new File("src/Resources/" + cursorName + ".png")), new Point(3, 3), "t");

        }
        catch (IOException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initWindow()
    {
        gameBoard = new GameBoard(this);
        topPanel = new TopPanel(gameBoard);
        botPanel = new BotPanel(gameBoard);
        gameBoard.initComponents(botPanel, topPanel);
    }

    public static void main(String args[])
    {

        System.setProperty("sun.java2d.opengl", "true");
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
