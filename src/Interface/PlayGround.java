package Interface;

import Entities.Classes.Entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Playground Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class PlayGround extends JPanel{

    public PlayGround()
    {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Entity> entities = Entity.getEntities();

        for (Entity entity : entities)
        {
            BufferedImage img = null;

            try {
                img = ImageIO.read(new File(entity.getImageEnum().getImages()[entity.getImageIndex()]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            g.drawImage(img,entity.getPosition().x,entity.getPosition().y,null);

            entity.changeImageIndex();
        }
    }
}
