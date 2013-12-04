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
import java.awt.image.BufferedImage;
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

    public ActionView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        background = ImgResources.getImg("actionView");
        this.gameBoard = gameBoard;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        
        if(building != null){
            int i = 0;
            for (Action a : building.getActions()) {
                g.drawImage(a.getActionImage(), 30, (30+(i*a.getActionImage().getHeight())) + i * 5, null);
                g.setColor(Color.red);
                int fontSize = 14;
                g.setFont(new Font("Verdana", 1, fontSize));
                g.drawString(a.getActionName(), 40 + a.getActionImage().getWidth(), ((30+(i*a.getActionImage().getHeight())) + i * 5) + a.getActionImage().getHeight()/2 + fontSize/2);
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
}
