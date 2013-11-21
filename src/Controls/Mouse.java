/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import GameElement.Bush;
import GameElement.Gold;
import GameElement.ObjectElement;
import GameElement.Relic;
import GameElement.Stone;
import GameElement.Tree;
import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static javax.swing.SwingUtilities.isLeftMouseButton;

/**
 *
 * @author Honza
 */
public class Mouse implements MouseListener, MouseMotionListener {

    private GameBoard gameBoard;

    Thread testingThread;

    private Color dragColor = new Color(0, 255, 50, 128);

    private int clickedX = 0;
    private int clickedY = 0;

    private int clickedObjectX=0;
    private int clickedObjectY=0;
    
    private int currentX = 0;
    private int currentY = 0;

    private int clickedIndexX;
    private int clickedIndexY;
    
    private boolean active = false;
    private boolean firstDrag = false;

    public Mouse(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickedX=e.getX();
        clickedY=e.getY();
        
        clickedIndexX = clickedX/25;
        clickedIndexY=clickedY/25;
        
        //clickedObjectX=clickedIndexX*25;
        //clickedObjectY=clickedIndexY*25;
        
        System.out.println("clicked on: ");
        
        System.out.println(clickedIndexX);
        System.out.println(clickedIndexY);
        if(gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexX) instanceof Tree)
        {
            Tree tr = (Tree) gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexY);
            System.out.println(tr.getName());
            System.out.println("Wood: "+tr.getRemainingWood());
        }
        else if (gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexX) instanceof Bush)
        {
            Bush b = (Bush) gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexY);
            System.out.println(b.getName());
            System.out.println("Food: "+b.getRemainingFood());
        }
        else if (gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexX) instanceof Stone)
        {
           Stone st = (Stone) gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexY); 
           System.out.println(st.getName());
           System.out.println("Stone: "+st.getRemainStone());
        }
        else if (gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexX) instanceof Gold)
        {
           Gold g = (Gold) gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexY); 
           System.out.println(g.getName());
           System.out.println("Gold: "+g.getRemainingGold());
        }
        else if (gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexX) instanceof Relic)
        {
           Relic r = (Relic) gameBoard.getObjectFieldObject(clickedIndexX, clickedIndexY); 
           System.out.println(r.getName());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        active = false;
        firstDrag = false;
        clickedX = 0;
        clickedY = 0;
        currentX = 0;
        currentY = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void drawRect(Graphics g) {
        if (active) {
            g.setColor(dragColor);
            g.fillRect(clickedX, clickedY, currentX - clickedX, currentY - clickedY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isLeftMouseButton(e)) {
            active = true;
            if (!firstDrag) {
                clickedX = e.getX();
                clickedY = e.getY();
                firstDrag = true;
            }
            currentX = e.getX();
            currentY = e.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
