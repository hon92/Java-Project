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
    private Building building;
    private SelectViewMouse selectViewMouse;

    public SelectView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        this.gameBoard = gameBoard;
        background = ImgResources.getImg("selectView");
        units = new ArrayList<>();
        selectViewMouse = new SelectViewMouse();
        setFocusable(true);
        addMouseListener(selectViewMouse);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        if (element == null && building == null && units.size() == 0)
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
        if (units.size() == 1)
        {
            g.drawImage(units.get(0).getIcon(), 20, 20, null);
            g.drawString(units.get(0).getName(), 50, 30);
            g.drawString("Attack: " + units.get(0).getAttack(), 50, 40);
            g.drawString("Armor: " + units.get(0).getArmor(), 50, 50);
            g.drawString("Max Hp: " + units.get(0).getMaxHp(), 50, 60);
            g.drawString("Current Hp: " + units.get(0).getHp(), 50, 70);
        }
        else if (units.size() > 1)
        {
            for (int i = 0; i < units.size(); i++)
            {
                units.get(i).setSelected(true);
                for (int j = 0; j < 5; j++)
                {
                    g.drawImage(units.get(i).getIcon(), 20 + (i * 30), 20 + (j), null);
                    g.setColor(Color.red);
                    g.fillRect(20 + (i * 30), 20 + j + units.get(i).getIcon().getHeight() + 2, units.get(i).getIcon().getWidth(), 5);
                    g.setColor(Color.green);
                    g.fillRect(20 + (i * 30), 20 + j + units.get(i).getIcon().getHeight() + 2, (int) (25 * units.get(i).getHpDown()), 5);
                }
            }
        }

        if (building != null)
        {
            g.drawImage(building.getIcon(), 20, 20, null);
            g.drawString(building.getName(), 60, 30);
            g.drawString("Max Hp: " + building.getMaxHp(), 60, 60);
            g.drawString("Current Hp: " + building.getCurrentHp(), 60, 70);
        }
        g.dispose();

    }

    public void setObjectElement(ObjectElement element)
    {
        this.element = element;
    }

    public void fillData(int indexX, int indexY)
    {
        element = gameBoard.getObjectFieldObject(indexX, indexY);
        building = gameBoard.getBuildingFieldObject(indexX, indexY);
        Unit unit = gameBoard.getUnitField(indexX, indexY);
        if (unit != null)
        {
            unit.setSelected(true);
            units.add(unit);
        }
        else
        {
            for (Unit u : units)
            {
                u.setSelected(false);

            }
            units.clear();
        }
        repaint();
    }

//    public void setObjectUnit(Unit unit)
//    {
//        if (this.unit != null && this.unit.isSelected() && unit == null)
//        {
//            this.unit.setSelected(false);
//        }
//        this.unit = unit;
//    }
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
        System.out.println("new list unit " + "size: " + units.size());

//        if (units.size() == 0)
//        {
//            for (Unit u : gameBoard.getUnits())
//            {
//                u.setSelected(false);
//            }
//        }
        this.units = units;

    }

//    public Unit getUnit()
//    {
//        if (unit == null)
//        {
//            return null;
//        }
//        else
//        {
//            return unit;
//        }
//
//    }
    public ArrayList<Unit> getUnitList()
    {
        return units;
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
