/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import GameElement.ObjectElement;
import Unit.Unit;
import View.GameBoard;
import View.SelectView;

/**
 *
 * @author Honza
 */
public class SelectMouse
{

    private static GameBoard gameBoard;
    private static SelectView selectView;
    private ObjectElement element;
    private Unit unit;

    public SelectMouse(GameBoard gameBoard, SelectView selectView, int indexX, int indexY)
    {
        this.gameBoard = gameBoard;
        this.selectView = selectView;
        element = gameBoard.getObjectFieldObject(indexX, indexY);
        unit = gameBoard.getUnitField(indexX, indexY);

        if (element == null)
        {
            selectView.setObjectElement(null);
        }
        if (unit == null)
        {
            selectView.setObjectUnit(null);
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

        selectView.repaint();
    }

    void setUnitSelect(boolean b)
    {
        if (unit != null)
        {
            unit.setSelected(b);
        }
    }

    void goTo(int clickedIndexX, int clickedIndexY)
    {
        unit.move(clickedIndexX, clickedIndexY);
    }

}
