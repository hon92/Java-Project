/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Buildings.Action;
import Controls.Mouse;
import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class VillagerAction extends Action
{

    private Mouse mouse;
    private int x, y;

    public VillagerAction(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        initAction();
    }

    @Override
    protected void initAction()
    {
        try
        {
            actionImage = ImageIO.read(new File("src/Unit/Villager/build.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(VillagerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doAction()
    {
        System.err.println("akce");
        mouse = gameBoard.getMouse();
        boolean running = true;
        new BuildMouse();
        gameBoard.setBuildAction(this);
        //gameBoard.getBuildings().add(new Barracks(gameBoard, 45, 80, "Blue"));
        //g.drawImage(sourceImg, gameBoard.convertX(locationX * GameData.BOXSIZE), gameBoard.convertY(locationY * GameData.BOXSIZE), null);
    }

    public void drawBuildObject(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(x, y, 100, 100);
    }

    private class BuildMouse implements MouseMotionListener
    {

        public BuildMouse()
        {

            MouseMotionListener t[] = gameBoard.getMouseMotionListeners();
            gameBoard.addMouseMotionListener(this);
            gameBoard.requestFocus();
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            x = e.getX();
            y = e.getY();

            System.err.println("" + e.getX() + "  " + e.getY());
        }

    }

    @Override
    public void cancelAction()
    {
        System.out.println("storno");
    }

    @Override
    public void tick()
    {
    }

}
