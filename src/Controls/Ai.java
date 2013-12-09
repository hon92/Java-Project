/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Unit.Player;
import Unit.Unit;
import View.GameBoard;

/**
 *
 * @author Honza
 */
public class Ai
{

    private GameBoard gameBoard;
    private Player player;
    private int timer = 0;
    private int timerToSpawnNewUnit = 50;

    public Ai(GameBoard gameBoard, Player player)
    {
        this.gameBoard = gameBoard;
        this.player = player;
    }

    public void tick()
    {
        if (timer % 60 == 0)
        {
            for (Unit u : player.getUnits())
            {
                //u.move(u.getX() / 25 - 1, u.getY() / 25 - 1);
            }
            timer = 0;
        }

        timerToSpawnNewUnit++;

        timer++;

    }

}
