/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import GameElement.Tree;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class ImgResources
{

    private static Hashtable<String, BufferedImage> imgs;

    public ImgResources()
    {
        imgs = new Hashtable<>();

        try
        {

            imgs.put("tree1", ImageIO.read(new File("src/Resources/tree1.png")));
            imgs.put("tree2", ImageIO.read(new File("src/Resources/tree2_1.png")));
            imgs.put("gayTree", ImageIO.read(new File("src/Resources/gayTree.png")));
            imgs.put("deadTree", ImageIO.read(new File("src/Resources/deadTree.png")));
            imgs.put("treeDown", ImageIO.read(new File("src/Resources/treeDown.png")));
            imgs.put("treeIcon", ImageIO.read(new File("src/Resources/treeIcon.png")));
            imgs.put("grass", ImageIO.read(new File("src/Resources/grass.png")));
            imgs.put("water", ImageIO.read(new File("src/Resources/water.png")));
            imgs.put("selectView", ImageIO.read(new File("src/Resources/selectView.png")));
            imgs.put("actionView", ImageIO.read(new File("src/Resources/actionView.png")));

        }
        catch (IOException ex)
        {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static BufferedImage getImg(String name)
    {
        return imgs.get((String) name);
    }

}
