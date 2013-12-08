/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Buildings.Action;
import Buildings.BuildingType;
import Controls.Mouse;
import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private int width, height;
    private String path;
    private Color buildOkColor = Color.GREEN;
    private Color buildErrorColor = Color.RED;
    private Color currentColor = buildErrorColor;
    private boolean canBePlaced = false;
    private boolean isAction = false;

    public VillagerAction(GameBoard gameBoard, BuildingType type)
    {
        this.gameBoard = gameBoard;

        switch (type)
        {
            case BARRACKS:
                width = 8;
                height = 6;
                path = "src/Unit/Villager/barracksicon.png";
                break;
            case CHURCH:
                width = 7;
                height = 8;
                path = "src/Unit/Villager/churchIcon.png";
                break;
            case FARM:
                width = 4;
                height = 4;
                path = "src/Unit/Villager/farmIcon.jpg";
                break;
            case HOUSE:
                width = 5;
                height = 4;
                path = "src/Unit/Villager/houseIcon.png";
                break;
//            case TOWN_CENTER:
//                width = 8;
//                height = 6;
//                path = "src/Unit/Villager/build.png";
//                break;

        }

        initAction();
    }

    @Override
    protected void initAction()
    {
        try
        {
            actionImage = ImageIO.read(new File(path));
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
        mouse.setBuildMode(true);
        new BuildMouse();
        gameBoard.setBuildAction(this);
        isAction = true;
    }

    public void drawBuildObject(Graphics g)
    {
        if (isAction)
        {
            g.setColor(currentColor);
            //g.fillRect(x - (width * 25) / 2, y - (height * 25) / 2, width * 25, height * 25);
            g.fillRect(x - 25, y - 25, width * 25, height * 25);
        }
    }

    private class BuildMouse implements MouseMotionListener, MouseListener
    {

        public BuildMouse()
        {

            MouseMotionListener t[] = gameBoard.getMouseMotionListeners();
            gameBoard.addMouseMotionListener(this);
            gameBoard.addMouseListener(this);
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

            int modulo = x % 25;
            int newx = 25 - modulo + x;
            int mapx = newx;
            x = mapx;
            int modulo2 = y % 25;
            int newy = 25 - modulo2 + y;
            int mapy = newy;
            y = mapy;

            int currx = mouse.convertX((x - 25) / 25);
            int curry = mouse.convertY((y - 25) / 25);
            System.err.println("" + currx + "  " + curry);

            for (int i = currx; i < currx + width; i++)
            {
                for (int j = curry; j < curry + height; j++)
                {
                    if (gameBoard.getFieldIndex(i, j) != 0)
                    {
                        //System.err.println("OK");
                        currentColor = buildErrorColor;
                    }
                    else
                    {
                        currentColor = buildOkColor;
                    }
                }
            }

            {

            }
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (canBePlaced && e.getButton() == MouseEvent.BUTTON1)
            {

            }
            else if (e.getButton() == MouseEvent.BUTTON3)
            {
                mouse.setBuildMode(false);
                gameBoard.removeMouseMotionListener(this);
                gameBoard.removeMouseListener(this);
                isAction = false;
                cancelAction();
            }
            else
            {
                System.out.println("Bad location for new Building");
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
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
