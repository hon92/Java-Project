/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controls.SelectMouse;
import Data.Source;
import GameElement.ObjectElement;
import Unit.Unit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class SelectView extends JPanel
{

    private GameBoard gameBoard;
    private SelectMouse mouseSelect;
    private ObjectElement element;
    private Unit unit;

    public SelectView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        //setBackground(Color.green);
        this.gameBoard = gameBoard;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (element == null && unit == null)
        {
            g.drawString("Nic", 50, 50);
        }
        if (element instanceof Source)
        {
            Source s = (Source) element;
            g.drawImage(s.getIcon(), 20, 20, null);
            g.drawString(element.getName(), 50, 30);
            g.drawString("Remaining resources: " + s.getRemainingResource(), 50, 45);
        }
        else if (element != null)
        {
            g.drawString(element.getName(), 50, 60);
        }
        if (unit instanceof Unit)
        {
            g.drawImage(unit.getIcon(), 20, 20, null);
            g.drawString(unit.getName(), 50, 30);
            g.drawString("Attack: " + unit.getAttack(), 50, 40);
            g.drawString("Armor: " + unit.getArmor(), 50, 50);
            g.drawString("Max Hp: " + unit.getMaxHp(), 50, 60);
            g.drawString("Current Hp: " + unit.getHp(), 50, 70);
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

}
