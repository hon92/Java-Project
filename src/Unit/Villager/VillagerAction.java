/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Villager;

import Building.Barracks.Barracks;
import Building.Church.Church;
import Building.Farm.Farm;
import Building.House.House;
import Buildings.Action;
import Buildings.BuildingType;
import Controls.Mouse;
import Unit.Unit;
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

    private Unit villager;
    private Mouse mouse;
    private int x, y;
    private int width, height;
    private String path;
    private Color buildOkColor = new Color(0, 255, 0, 160);
    private Color buildErrorColor = new Color(255, 0, 0, 160);
    private Color currentColor = buildErrorColor;
    private boolean canBePlaced = false;
    private boolean isAction = false;
    private BuildingType type;
    private int currX, currY;
    private int wood, food, stone, gold;
    private BuildMouse buildMouse;

    public VillagerAction(GameBoard gameBoard, BuildingType type, Unit villager)
    {
        this.gameBoard = gameBoard;
        this.type = type;
        this.villager = villager;

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
                width = 6;
                height = 6;
                path = "src/Unit/Villager/farmIcon.jpg";
                break;
            case HOUSE:
                width = 5;
                height = 4;
                wood = 100;
                gold = 100;
                stone = 100;
                food = 100;
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

        if (villager.getPlayer() == "Blue")
        {
            if (gameBoard.getBluePlayer().getFood() >= food
                    && gameBoard.getBluePlayer().getGold() >= gold
                    && gameBoard.getBluePlayer().getStone() >= stone
                    && gameBoard.getBluePlayer().getWood() >= wood)
            {
                System.err.println("akce");
                mouse = gameBoard.getMouse();
                mouse.setBuildMode(true);
                buildMouse = new BuildMouse();
                gameBoard.setBuildAction(this);
                isAction = true;
            }
            else
            {
                System.err.println("Malo surovin");
            }
        }
    }

    public void drawBuildObject(Graphics g)
    {
        if (isAction)
        {
            g.setColor(currentColor);
            g.fillRect(x - 25, y - 25, width * 25, height * 25);
        }
    }

    private class BuildMouse implements MouseMotionListener, MouseListener
    {

        private MouseListener ml[];
        private MouseMotionListener mml[];
        public MouseListener temp;

        public BuildMouse()
        {

            ml = gameBoard.getBotPanel().getActionPanel().getMouseListeners();
            temp = ml[0];
            gameBoard.getBotPanel().getActionPanel().removeMouseListener(ml[0]);

            gameBoard.addMouseMotionListener(this);
            gameBoard.addMouseListener(this);
            gameBoard.setFocusable(true);
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

            currX = mouse.convertX((x - 25) / 25);
            currY = mouse.convertY((y - 25) / 25);
            System.err.println("" + currX + "  " + currY);

            for (int i = currY; i < currY + 4; i++)
            {
                for (int j = currX; j < currX + 5; j++)
                {
                    if (gameBoard.getFieldIndex(j, i) != 0)
                    {

                        currentColor = buildErrorColor;
                        canBePlaced = false;
                        return;
                    }
                    else
                    {
                        //System.err.println("OK");
                        canBePlaced = true;
                        currentColor = buildOkColor;

                    }
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (canBePlaced && e.getButton() == MouseEvent.BUTTON1)
            {
                switch (type)
                {
                    case BARRACKS:

                        gameBoard.getBluePlayer().addBuilding(new Barracks(gameBoard, currX, currY, "Blue"));
                        gameBoard.getBluePlayer().setFood(-food);
                        gameBoard.getBluePlayer().setGold(-gold);
                        gameBoard.getBluePlayer().setStone(-stone);
                        gameBoard.getBluePlayer().setWood(-wood);
                        break;
                    case CHURCH:
                        gameBoard.getBluePlayer().addBuilding(new Church(gameBoard, currX, currY, "Blue"));
                        gameBoard.getBluePlayer().setFood(-food);
                        gameBoard.getBluePlayer().setGold(-gold);
                        gameBoard.getBluePlayer().setStone(-stone);
                        gameBoard.getBluePlayer().setWood(-wood);
                        break;
                    case FARM:
                        gameBoard.getBluePlayer().addBuilding(new Farm(gameBoard, currX, currY, "Blue"));
                        gameBoard.getBluePlayer().setFood(-food);
                        gameBoard.getBluePlayer().setGold(-gold);
                        gameBoard.getBluePlayer().setStone(-stone);
                        gameBoard.getBluePlayer().setWood(-wood);
                        break;
                    case HOUSE:
                        gameBoard.getBluePlayer().addBuilding(new House(gameBoard, currX, currY, "Blue"));
                        gameBoard.getBluePlayer().setFood(-food);
                        gameBoard.getBluePlayer().setGold(-gold);
                        gameBoard.getBluePlayer().setStone(-stone);
                        gameBoard.getBluePlayer().setWood(-wood);
                        gameBoard.getBluePlayer().setMaxPop(5);
                        break;
                }
                cancelAction();
            }
            else if (e.getButton() == MouseEvent.BUTTON3)
            {

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
        mouse.setBuildMode(false);
        gameBoard.removeMouseMotionListener(buildMouse);
        gameBoard.removeMouseListener(buildMouse);
        isAction = false;
        gameBoard.getBotPanel().getActionPanel().addMouseListener(buildMouse.temp);
        buildMouse = null;
        System.out.println("storno");
    }

    @Override
    public void tick()
    {
    }

}
