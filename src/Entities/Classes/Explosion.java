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

    private int power;
    private int timer = 15;
    private final Direction dir;

    public Explosion(int power, Direction dir, Point pos)
    {
        super("Explosion",pos, Images.explosion);

        this.power = power;
        this.dir = dir;
    }

    @Override
    public void tick()
    {
        this.timer--;

        this.power--;

        if (this.power > 0) {
            this.extend();
        }

        if(this.timer <= 0)
        {
            this.destroy();
        }
    }

    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Interface.Window.getWindowSize().width/20, Interface.Window.getWindowSize().width/20);
    }

    /**
     * Extend the explosion area of effect, reducing the power every time.
     */
    private void extend()
    {
        new Explosion(this.power, this.dir,this.getSpot(this.dir));
    }
}
