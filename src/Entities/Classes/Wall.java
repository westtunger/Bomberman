package Entities.Classes;

import Entities.Enum.Images;

import java.awt.*;

/**
 * Wall Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Wall extends Entity{

    boolean isBreakable;

    public Wall(boolean breakable, Point pos)
    {
        super("Wall",pos, null);

        this.isBreakable = breakable;
        this.setImages(this.getWallImages(this.isBreakable));
    }

    /**
     * Return the images needed in function of the wall
     * @param breakable the possibility to break the wall
     */
    public Images getWallImages(boolean breakable)
    {
        if(breakable)
            return Images.breakableWall;
        else
            return Images.unbreakableWall;
    }

    @Override
    public void tick() {

    }
}
