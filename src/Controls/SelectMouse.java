/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Data.Source;
import GameElement.ObjectElement;
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

    public SelectMouse(GameBoard gameBoard, SelectView selectView, int indexX, int indexY)
    {
        this.gameBoard = gameBoard;
        this.selectView = selectView;
        element = gameBoard.getObjectFieldObject(indexX, indexY);
        selectView.setObjectElement(element);
        selectView.repaint();
    }

}
