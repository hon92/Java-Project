/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElement;

import Data.GameData;
import View.GameBoard;
import java.awt.Graphics;

/**
 *
 * @author Honza
 */
public abstract class ObjectElement
{

    protected int x;
    protected int y;
    protected GameBoard gameBoard;

    public ObjectElement(GameBoard gameBoard, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.gameBoard = gameBoard;
    }

    public abstract void drawObject(Graphics g);

    public int getX()
    {
        // return x / GameData.BOXSIZE;
        return x;
    }

    public int getY()
    {
        //return y / GameData.BOXSIZE;
        return y;
    }
}
