/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Buildings;

import View.GameBoard;
import java.awt.image.BufferedImage;

/**
 *
 * @author Adam
 */
public abstract class Action {
    protected String actionName;
    protected BufferedImage actionImage;
    protected Boolean isActive = false;
    protected GameBoard gameBoard;
    protected Building building;
    protected int tickCount;
    protected int remaining;
    
    public Action() {
        this.actionName = null;
        this.actionImage = null;
        this.gameBoard = null;
        this.building = null;
    }
    
    public String getActionName(){
        return actionName;
    };

    public BufferedImage getActionImage() {
        return actionImage;
    }

    public int getRemaining() {
        return remaining;
    }

    public Boolean isIsActive() {
        return isActive;
    }
    
    
    protected abstract void initAction();
    public abstract void doAction();
    public abstract void cancelAction();
    public abstract void tick();
}
