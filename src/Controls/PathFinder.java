/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Unit.Unit;

/**
 *
 * @author Honza
 */
public class PathFinder
{

    private Unit unit;
    private int nodes[][];
    private int goX;
    private int goY;

    public PathFinder(Unit unit, int goX, int goY)
    {
        this.unit = unit;
        this.goX = goX;
        this.goY = goY;
    }

    private void findWay()
    {

    }

}
