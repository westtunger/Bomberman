package Entities.Classes;

import Entities.EntityManager;
import Entities.Interfaces.Layers;
import Entities.SpriteManager;

import java.awt.*;

import static Entities.Enum.Direction.*;

/**
 * Bomb Class.
 *
 * @author Nicolas Viseur
 * @version 1.2
 */
public class Bomb extends Entity{

    private final int power;
    private int timer = 35;
    private boolean destroyed = false;

    public Bomb(int power, Point pos)
    {
        super("Bomb",pos,4,Layers.bombTemp);
        this.power = power;
    }

    /**
     * return the boolean telling if the object have been destroyed or not.
     * @return the boolean telling if the object have been destroyed or not.
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void tick()
    {
        if(this.timer > 0)
            this.timer--;
        else
            this.onExplode();
    }

    @Override
    public void changeImageIndex() {
        int index = this.getImageIndex();

        if(index<3)
        {
            super.changeImageIndex();
        }
        else
        {
            super.resetImageIndex();
        }
    }

    @Override
    public Image getImage() {
        return SpriteManager.getSprite(this.getImageIndex(),9);
    }

    @Override
    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Visual.Window.getWindowSize().width/20, Visual.Window.getWindowSize().width/20);
    }

    @Override
    public void onExplode() {
        EntityManager.getManager().addEntity(new Explosion(this.power, up,this.getSpot(up)));
        EntityManager.getManager().addEntity(new Explosion(this.power, right,this.getSpot(right)));
        EntityManager.getManager().addEntity(new Explosion(this.power, down,this.getSpot(down)));
        EntityManager.getManager().addEntity(new Explosion(this.power, left,this.getSpot(left)));
        EntityManager.getManager().addEntity(new Explosion(this.power, null,new Point(this.getPosition().x,this.getPosition().y)));

        this.destroy();

        destroyed = true;
    }

    /**
     * Check if the bomb still have any collision.
     * If not, change the layer to collidable.
     * @see Layers
     */
    public void processCollision()
    {
        if(this.checkCollision() == null)
        {
            this.setLayer(Layers.collidable);
        }
    }
}