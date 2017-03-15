package Entities.Classes;

import Entities.Enum.Direction;
import Entities.Enum.Images;

import java.awt.*;

/**
 * Explosion Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Explosion extends Entity {

    int power;
    Direction dir;

    public Explosion(int power, Direction dir, Point pos)
    {
        super("Explosion",pos, Images.explosion);

        this.power = power;
        this.dir = dir;
    }

    @Override
    public void tick()
    {
        this.power--;

        if(this.power > 0)
        {
            this.extend();
        }

        this.destroy();
    }

    /**
     * Extend the explosion area of effect, reducing the power every time.
     */
    public void extend()
    {
        addEntity(new Explosion(this.power, this.dir,this.getSpot(this.dir)));
    }
}
