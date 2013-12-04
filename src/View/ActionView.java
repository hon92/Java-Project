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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;

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

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public ActionView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        background = ImgResources.getImg("actionView");
        this.gameBoard = gameBoard;
        actionMouseClick = new ActionMouseClick();
        //setFocusable(true);
        addMouseListener(actionMouseClick);

        button1 = new JButton("LOL");
        button1.setBounds(30, 30, 100, 100);
        button1.setEnabled(true);
        button1.setVisible(true);
        add(button1);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        if (building != null)
        {
            int i = 0;
            for (Action a : building.getActions())
            {
                g.drawImage(a.getActionImage(), 30, (30 + (i * a.getActionImage().getHeight())) + i * 5, null);
                g.setColor(Color.red);
                int fontSize = 14;
                g.setFont(new Font("Verdana", 1, fontSize));
                g.drawString(a.getActionName(), 40 + a.getActionImage().getWidth(), ((30 + (i * a.getActionImage().getHeight())) + i * 5) + a.getActionImage().getHeight() / 2 + fontSize / 2);
                i++;
            }
        }

        //drawing action icon
        g.dispose();
    }

    public void setBuildingObject(Building building)
    {
        if (this.building != null && this.building.isSelected() && building == null)
        {
            this.building.setSelected(false);
        }
        this.building = building;
    }

    private class ActionMouseClick implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {

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
