package Entities.Classes;

import Entities.EntityManager;
import Entities.Interfaces.Layers;
import Entities.SpriteManager;

import java.awt.*;

/**
 * Wall Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Wall extends Entity {

    private final boolean isBreakable;

    public Wall(boolean breakable, Point pos)
    {
        super("Wall",pos,0, Layers.collidable);

        this.isBreakable = breakable;
    }

    @Override
    public void tick() {

    }

    /**
     * Tell if the wall is breakable or not.
     * @return true if the wall can be break.
     */
    public boolean isBreakable()
    {
        return this.isBreakable;
    }

    private void dropPowerUp()
    {
        int dropPowerUp = (int)(Math.random()*100);
        int powerUpType = (int)(Math.random()*100);

        //20% of chance to drop a power up
        if(dropPowerUp < 20)
        {
            //each powerup have about 33% of chance to drop(33%-33%-34%)
            if(powerUpType > 66)
            {
                EntityManager.getManager().addEntity(new PowerUp(PowerUp.BOMB,this.getPosition()));
            }
            else if( powerUpType > 33)
            {
                EntityManager.getManager().addEntity(new PowerUp(PowerUp.POWER,this.getPosition()));
            }
            else
            {
                EntityManager.getManager().addEntity(new PowerUp(PowerUp.SPEED,this.getPosition()));
            }
        }
    }

    @Override
    public Image getImage() {
        return SpriteManager.getSprite(this.isBreakable() ? 0 : 1,8);
    }

    @Override
    public void onExplode()
    {
        if(this.isBreakable)
        {
            this.dropPowerUp();
            this.destroy();
        }
    }
}
