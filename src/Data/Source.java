/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Honza
 */
public interface Source
{

    public int getRemainingResource();

    
    
    public void setRemainingResource(int count);

    public BufferedImage getIcon();
}
