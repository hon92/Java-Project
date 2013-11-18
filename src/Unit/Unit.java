/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Honza
 */
public abstract class Unit
{

    protected int currentHp;
    protected int maxHp;
    protected int locationX;
    protected int locationY;
    protected UnitType typeUnit;
    protected BufferedImage sourceImg;

    public abstract void move(int x, int y);

    public abstract void drawUnit(Graphics g);

}
