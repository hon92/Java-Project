/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Data.GameData;
import GameElement.Grass;
import View.GameBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class Villager
{

    private static int maxHealth;
    private int currentHealth;
    private int createTime;
    private int carriedResources;

    private static int attack;
    private static int armor;

    private static int speed;

    private static BufferedImage villagerDown;
    private static BufferedImage villagerIcon;
    private static BufferedImage villagerTop;
    private static BufferedImage villagerLeft;
    private static BufferedImage villagerRight;
    private static BufferedImage villagerRightBot;
    private static BufferedImage villagerRightTop;
    private static BufferedImage villagerLeftTop;
    private static BufferedImage villagerLeftBot;
    
    public Villager(GameBoard gameBoard, int x, int y)
    {
        try
        {
            villagerDown = ImageIO.read(new File("src/Resources/villagerImg/villagerDown.png"));
            villagerIcon=ImageIO.read(new File("src/Resources/villagerImg/villagerIcon.png"));
            villagerTop=ImageIO.read(new File("src/Resources/villagerImg/villagerTop.png"));
            villagerRightBot=  ImageIO.read(new File("src/Resources/villagerImg/villagerRightBot.png"));      
            villagerRight=ImageIO.read(new File("src/Resources/villagerImg/villagerRight.png"));
            villagerLeftBot=  ImageIO.read(new File("src/Resources/villagerImg/villagerLeftBot.png"));
            villagerLeftTop=ImageIO.read(new File("src/Resources/villagerImg/villagerLeftTop.png"));
            villagerRightTop=  ImageIO.read(new File("src/Resources/villagerImg/villagerRightTop.png")); 
            villagerLeft=  ImageIO.read(new File("src/Resources/villagerImg/villagerLeft.png"));      
        }
        catch (IOException ex)
        {
            Logger.getLogger(Grass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gameBoard.setFieldIndex(x, y+1, 11);        
    }
    
    
    
    public void move()
    {

    }

    public void build()
    {

    }
}
