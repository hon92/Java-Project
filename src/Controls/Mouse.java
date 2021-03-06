/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Unit.Unit;
import View.ActionView;
import View.BotPanel;
import View.GameBoard;
import View.SelectView;
import View.TopPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import static javax.swing.SwingUtilities.isLeftMouseButton;

/**
 *
 * @author Honza
 */
public class Mouse implements MouseListener, MouseMotionListener
{

    private GameBoard gameBoard;
    private SelectView selectView;
    private ActionView actionView;
    private Color dragColor = new Color(0, 255, 50, 128);
    private int clickedX = 0;
    private int clickedY = 0;
    private int currentX = 0;
    private int currentY = 0;
    private int clickedIndexX;
    private int clickedIndexY;
    private boolean active = false;
    private Rectangle selectRectangle = null;
    private int unitDistance = 0;
    private boolean buildMode = false;

    public Mouse(GameBoard gameBoard, BotPanel botPanel, TopPanel topPanel)
    {
        this.gameBoard = gameBoard;
        this.selectView = botPanel.getSelectPanel();
        this.actionView = botPanel.getActionPanel();

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        clickedX = e.getX();
        clickedY = e.getY();

        clickedIndexX = clickedX / 25;
        clickedIndexY = clickedY / 25;

        clickedIndexX = convertX(clickedIndexX);
        clickedIndexY = convertY(clickedIndexY);

        if (!buildMode)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                selectView.fillData(clickedIndexX, clickedIndexY);
                actionView.fillData(clickedIndexX, clickedIndexY);
            }
            if (e.getButton() == MouseEvent.BUTTON3)
            {

                for (Unit u : selectView.getUnitList())
                {
                    if (u.getPlayer() == "Blue")
                    {
                        if (gameBoard.getFieldIndex(clickedIndexX, clickedIndexY) == 11)
                        {
                            u.goAttack(gameBoard.getUnitField(clickedIndexX, clickedIndexY));
                        }
                        else if (gameBoard.getFieldIndex(clickedIndexX, clickedIndexY) == 15)
                        {
                            u.goAttackBuilding(gameBoard.getBuildingFieldObject(clickedIndexX, clickedIndexY), u);
                        }
                        else
                        {
                            u.move(clickedIndexX, clickedIndexY);
                        }

                    }
                }

            }
        }
        else
        {
            System.err.println("build Mode");
        }

        System.err.println("x: " + clickedIndexX + " y: " + clickedIndexY);

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        clickedX = e.getX();
        clickedY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        active = false;
        clickedX = 0;
        clickedY = 0;
        currentX = 0;
        currentY = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public void drawRect(Graphics g)
    {
        if (active)
        {
            g.setColor(dragColor);
            g.fillRect(clickedX, clickedY, currentX - clickedX, currentY - clickedY);

        }

    }

    public ArrayList<Unit> getListSelectedUnits(Rectangle rec)
    {
        ArrayList<Unit> selectedUnits = new ArrayList<>();
        for (Unit u : gameBoard.getBluePlayer().getUnits())
        {
            Rectangle r = new Rectangle(gameBoard.convertX(u.getX()), gameBoard.convertY(u.getY()), 25, 50);

            if (rec.intersects(r))
            {
                u.setSelected(true);
                selectedUnits.add(u);
            }
            else
            {
                u.setSelected(false);
                selectedUnits.remove(u);
            }

        }

        if (selectedUnits.size() == 1)
        {
            actionView.fillData(selectedUnits.get(0).getX() / 25, selectedUnits.get(0).getY() / 25);
        }
        return selectedUnits;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (isLeftMouseButton(e))
        {
            active = true;
            currentX = e.getX();
            currentY = e.getY();
            if (!buildMode)
            {
                selectRectangle = new Rectangle(clickedX, clickedY, currentX - clickedX, currentY - clickedY);
                selectView.setUnits(getListSelectedUnits(selectRectangle));
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    public int convertX(int x)
    {
        return x + (gameBoard.getCurrWinX() / 25);
    }

    public int convertY(int y)
    {
        return y + (gameBoard.getCurrWinY() / 25);
    }

    public void setBuildMode(boolean mode)
    {
        buildMode = mode;
    }

}
