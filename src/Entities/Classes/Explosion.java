package Entities.Classes;

import Entities.EntityManager;
import Entities.Enum.Direction;
import Entities.Interfaces.Layers;
import Entities.SpriteManager;

import java.awt.*;

/**
 * Explosion Class.
 *
 * @author Nicolas Viseur
 * @version 1.2
 */
public class Explosion extends Entity {

    private int power;
    private int timer = 3;
    private final Direction dir;

    public Explosion(int power, Direction dir, Point pos)
    {
        super("Explosion",pos,5, Layers.collidable);

        this.power = power;
        this.dir = dir;
    }

    @Override
    public void tick()
    {
        this.timer--;

        this.power--;

        if (this.dir != null && this.power > 0) {
            this.extend();
        }

        if(this.timer <= 0)
        {
            this.destroy();
        }
    }

    /**
     * Extend the explosion area of effect, reducing the power every time.
     */
    private void extend()
    {
        EntityManager.getManager().addEntity(new Explosion(this.power, this.dir,this.getSpot(this.dir)));
    }

    @Override
    public Image getImage() {
        return SpriteManager.getSprite(this.getImageIndex(),10);
    }

    @Override
    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Visual.Window.getWindowSize().width/20, Visual.Window.getWindowSize().width/20);
    }

    @Override
    public void onExplode() {
    }
}
