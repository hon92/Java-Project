/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Buildings.Action;
import Buildings.Building;
import Data.ImgResources;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Honza
 */
public class ActionView extends JPanel
{

    private BufferedImage background;
    private GameBoard gameBoard;
    private Building building;
    private ActionMouseClick actionMouseClick;
    private List<Rectangle> rectangles;

    //private int viewWidth = 290;
    //private int viewHeigth = 165;
    public ActionView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        background = ImgResources.getImg("actionView");
        this.gameBoard = gameBoard;
        actionMouseClick = new ActionMouseClick();
        setFocusable(true);
        addMouseListener(actionMouseClick);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        if (building != null)
        {
            generateRectangles(building.getActions());
            int i = 0;
            int row = 0;
            for (Action a : building.getActions())
            {
                if (i % 7 == 0 && i != 0)
                {
                    row++;
                    i = 0;
                }
                g.drawImage(
                        a.getActionImage(),
                        (30 + (i * a.getActionImage().getWidth())) + i * 5,
                        (30 + (row * a.getActionImage().getHeight())) + row * 5,
                        null);
                if (a.isIsActive())
                {
                    g.setColor(Color.red);
                    g.setFont(new Font("Verdana", 1, 20));
                    g.drawString("" + (((a.getRemaining() - 1) / 60) + 1), ((30 + (i * a.getActionImage().getWidth())) + i * 5) + a.getActionImage().getWidth() / 2 - 8, ((30 + (row * a.getActionImage().getHeight())) + row * 5) + a.getActionImage().getHeight() / 2 + 8);
                }
                i++;
            }
//            for (Rectangle r : rectangles) {
//                g.setColor(Color.red);
//                g.drawRect(r.x, r.y, r.width, r.height);
//            }
        }

        g.dispose();
    }

    private void generateRectangles(List<Action> actions)
    {
        rectangles = new ArrayList<Rectangle>();
        int i = 0;
        int row = 0;
        for (Action a : actions)
        {
            if (i % 7 == 0 && i != 0)
            {
                row++;
                i = 0;
            }
            rectangles.add(new Rectangle(
                    (30 + (i * a.getActionImage().getWidth())) + i * 5,
                    (30 + (row * a.getActionImage().getHeight())) + row * 5,
                    a.getActionImage().getWidth(),
                    a.getActionImage().getHeight()));
            i++;
        }
    }

    public void fillData(int indexX, int indexY)
    {
        Building building = gameBoard.getBuildingFieldObject(indexX, indexY);
        if (building != null)
        {
            this.building = building;

        }
        else if (building == null && this.building != null)
        {

            this.building = null;
        }

    }

    private class ActionMouseClick implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {

            if (rectangles != null)
            {
                Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
                int position = 0;
                for (Rectangle r : rectangles)
                {
                    if (r.intersects(rect))
                    {
                        if (SwingUtilities.isLeftMouseButton(e))
                        {
                            building.getActions().get(position).doAction();
                        }
                        if (SwingUtilities.isRightMouseButton(e))
                        {
                            building.getActions().get(position).cancelAction();
                        }
                    }
                    position++;
                }
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
}
