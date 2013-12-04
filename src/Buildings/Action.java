/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Buildings;

import java.awt.image.BufferedImage;

/**
 *
 * @author Adam
 */
public abstract class Action {
    protected String actionName;
    protected BufferedImage actionImage;
    protected Boolean isActive = false;
    
    public Action() {
        this.actionName = null;
        this.actionImage = null;
    }
    
    public String getActionName(){
        return actionName;
    };

    public BufferedImage getActionImage() {
        return actionImage;
    }
    
    protected abstract void initAction();
    protected abstract void doAction();
}
