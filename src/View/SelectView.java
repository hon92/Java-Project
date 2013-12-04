/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Buildings.Building;
import Controls.SelectMouse;
import Data.ImgResources;
import Data.Source;
import GameElement.ObjectElement;
import Unit.Unit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class SelectView extends JPanel
{

    private BufferedImage background;
    private GameBoard gameBoard;
    private SelectMouse mouseSelect;
    private ObjectElement element;
    private Unit unit;
    private Building building;

    public SelectView(GameBoard gameBoard)
    {
        setPreferredSize(new Dimension(360, 200));
        this.gameBoard = gameBoard;
        background = ImgResources.getImg("selectView");

    }

    @Override
    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        if (element == null && unit == null && building == null)
        {
            g.drawString("Nic", 50, 50);
        }
        if (element instanceof Source)
        {
            MainWindow.botPanel.repaint();
            Source s = (Source) element;
            g.drawImage(s.getIcon(), 20, 20, null);
            g.drawString(element.getName(), 50, 30);
            g.drawString("Remaining resources: " + s.getRemainingResource(), 50, 45);
        }
        else if (element != null)
        {
            g.drawString(element.getName(), 50, 60);
        }
        if (unit != null)
        {
            g.drawImage(unit.getIcon(), 20, 20, null);
            g.drawString(unit.getName(), 50, 30);
            g.drawString("Attack: " + unit.getAttack(), 50, 40);
            g.drawString("Armor: " + unit.getArmor(), 50, 50);
            g.drawString("Max Hp: " + unit.getMaxHp(), 50, 60);
            g.drawString("Current Hp: " + unit.getHp(), 50, 70);
        }
        if (building != null)
        {
            //g.drawImage(unit.getIcon(), 20, 20, null);
            g.drawString(building.getName(), 50, 30);

            g.drawString("Max Hp: " + building.getMaxHp(), 50, 60);
            g.drawString("Current Hp: " + building.getCurrentHp(), 50, 70);
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

    public void setBuildingObject(Building building)
    {
        this.building = building;
    }

}
