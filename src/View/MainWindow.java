package View;

import Data.GameData;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainWindow extends JFrame
{

    private GameBoard gameBoard;
    private MiniMap miniMap;

    public MainWindow() throws HeadlessException
    {
        super("Age of Empires The ZIHOMO Expansion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT);
        setResizable(false);

        initWindow();
        add(gameBoard);
        add(miniMap);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        try
        {
            Cursor c;
            c = toolkit.createCustomCursor(ImageIO.read(new File("src/Resources/cursor.png")), new Point(16, 16), "img");
            getRootPane().setCursor(c);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initWindow()
    {
        gameBoard = new GameBoard();
        miniMap = new MiniMap(gameBoard);
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
