package Entities.Classes;

import Entities.Enum.Images;
import Entities.Enum.PowerUpTypes;

import java.awt.*;

/**
 * Wall Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Wall extends Entity{

    private final boolean isBreakable;

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
    private Images getWallImages(boolean breakable)
    {
        if(breakable)
            return Images.breakableWall;
        else
            return Images.unbreakableWall;
    }

    @Override
    public void tick() {

    }

    public boolean isBreakable()
    {
        return this.isBreakable;
    }

    public void destroy()
    {
        int dropPowerUp = (int)(Math.random()*100);
        int powerUpType = (int)(Math.random()*100);

        if(dropPowerUp < 20)
        {
            if(powerUpType > 66)
            {
                new PowerUp(PowerUpTypes.bomb,this.getPosition());
            }
            else if( powerUpType > 33)
            {
                new PowerUp(PowerUpTypes.power,this.getPosition());
            }
            else
            {
                new PowerUp(PowerUpTypes.speed,this.getPosition());
            }
        }

        super.destroy();
    }
}
