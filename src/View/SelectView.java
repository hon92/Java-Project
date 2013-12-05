/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Buildings.Building;
import Data.ImgResources;
import Data.Source;
import GameElement.ObjectElement;
import Unit.Unit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class SelectView extends JPanel
{

    private BufferedImage background;
    private GameBoard gameBoard;
    private ObjectElement element;
    private ArrayList<Unit> units;
    private Unit unit;
    private Building building;
    private SelectViewMouse selectViewMouse;

    public SelectView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        this.gameBoard = gameBoard;
        background = ImgResources.getImg("selectView");
        units = new ArrayList<Unit>();
        selectViewMouse = new SelectViewMouse();
        setFocusable(true);
        addMouseListener(selectViewMouse);
    }

    @Override
    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        if (element == null && unit == null && building == null && units.size() == 0)
        {
            g.drawString("Nic", 50, 50);
        }
        if (element instanceof Source)
        {
            gameBoard.getSelectView().repaint();
            Source s = (Source) element;
            g.drawImage(s.getIcon(), 20, 20, null);
            g.drawString(element.getName(), 50, 30);
            g.drawString("Remaining resources: " + s.getRemainingResource(), 50, 45);
        }
        else if (element != null)
        {
            g.drawString(element.getName(), 50, 60);
        }
        if (unit != null)
        {
            g.drawImage(unit.getIcon(), 20, 20, null);
            g.drawString(unit.getName(), 50, 30);
            g.drawString("Attack: " + unit.getAttack(), 50, 40);
            g.drawString("Armor: " + unit.getArmor(), 50, 50);
            g.drawString("Max Hp: " + unit.getMaxHp(), 50, 60);
            g.drawString("Current Hp: " + unit.getHp(), 50, 70);
        }
        if (building != null)
        {
            g.drawImage(building.getIcon(), 20, 20, null);
            g.drawString(building.getName(), 60, 30);
            g.drawString("Max Hp: " + building.getMaxHp(), 60, 60);
            g.drawString("Current Hp: " + building.getCurrentHp(), 60, 70);
        }
        if (units.size() != 0)
        {

            for (int i = 0; i < units.size(); i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    g.drawImage(units.get(i).getIcon(), 20 + (i * 30), 20 + (j), null);
                    g.setColor(Color.red);
                    g.fillRect(20 + (i * 30), 20 + j + units.get(i).getIcon().getHeight() + 2, units.get(i).getIcon().getWidth(), 5);
                    g.setColor(Color.green);
                    g.fillRect(20 + (i * 30), 20 + j + units.get(i).getIcon().getHeight() + 2, (int) (25 * units.get(i).getHpDown()), 5);
                }
                units.get(i).setSelected(true);

            }

        }
        g.dispose();

    }

    public void setObjectElement(ObjectElement element)
    {
        this.element = element;
    }

    public void setObjectUnit(Unit unit)
    {
        if (this.unit != null && this.unit.isSelected() && unit == null)
        {
            this.unit.setSelected(false);
        }
        this.unit = unit;
    }

    public void setBuildingObject(Building building)
    {
        if (this.building != null && this.building.isSelected() && building == null)
        {
            this.building.setSelected(false);
        }
        this.building = building;
    }

    public void setUnits(ArrayList<Unit> units)
    {
        System.out.println("mazu");

        if (units.size() == 0)
        {
            for (Unit u : gameBoard.getUnits())
            {
                u.setSelected(false);
            }
        }
        else if (units.size() == 1)
        {
            unit = units.get(0);
            unit.setSelected(true);
        }
        else
        {
            this.units = units;
        }
    }

    public Unit getUnit()
    {
        if (unit == null)
        {
            return null;
        }
        else
        {
            return unit;
        }

    }

    private class SelectViewMouse implements MouseListener
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
