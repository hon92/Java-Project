/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Buildings.Building;
import GameElement.ObjectElement;
import Unit.Unit;
import View.ActionView;
import View.BotPanel;
import View.GameBoard;
import View.SelectView;
import View.TopPanel;
import java.util.ArrayList;

/**
 *
 * @author Honza
 */
public class SelectMouse
{

    private static GameBoard gameBoard;
    private static SelectView selectView;
    private static ActionView actionView;
    private ObjectElement element;
    private Unit unit;
    private Building building;

    public SelectMouse(GameBoard gameBoard, BotPanel botPanel, TopPanel topPanel)
    {
        this.gameBoard = gameBoard;
        this.selectView = botPanel.getSelectPanel();
        this.actionView = botPanel.getActionPanel();

    }

    public void setUnitSelectedList(ArrayList<Unit> units)
    {
        System.err.println("size:" + units.size());
        selectView.setUnits(units);
        selectView.repaint();
    }

    public void setData(int indexX, int indexY)
    {
        element = gameBoard.getObjectFieldObject(indexX, indexY);
        unit = gameBoard.getUnitField(indexX, indexY);
        building = gameBoard.getBuildingFieldObject(indexX, indexY);

        if (element == null)
        {
            selectView.setObjectElement(null);
        }
        if (unit == null)
        {
            selectView.setObjectUnit(null);
        }
        if (building == null)
        {
            actionView.setBuildingObject(null);
            selectView.setBuildingObject(null);
        }

        if (unit != null)
        {
            unit.setSelected(true);
            selectView.setObjectUnit(unit);
        }
        if (element != null)
        {
            selectView.setObjectElement(element);
        }
        if (building != null)
        {
            building.setSelected(true);
            actionView.setBuildingObject(building);
            selectView.setBuildingObject(building);
        }
        selectView.repaint();
        actionView.repaint();
    }

    public void setUnitSelect(boolean b)
    {
        if (unit != null)
        {
            unit.setSelected(b);
        }
    }

    public boolean isUnitSelected()
    {
        if (unit == null)
        {
            return false;
        }
        else
        {
            return unit.isSelected();
        }
    }

    public Unit getUnit()
    {
        return unit;
    }

}
