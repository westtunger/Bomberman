package Entities.Classes;

import Entities.SpriteManager;

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
        super("Wall",pos,0);

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

        if(dropPowerUp < 20)
        {
            if(powerUpType > 66)
            {
                new PowerUp(PowerUp.BOMB,this.getPosition());
            }
            else if( powerUpType > 33)
            {
                new PowerUp(PowerUp.POWER,this.getPosition());
            }
            else
            {
                new PowerUp(PowerUp.SPEED,this.getPosition());
            }
        }
    }

    @Override
    public void destroy()
    {
        this.dropPowerUp();

        super.destroy();
    }

    @Override
    public Image getImage() {
        return SpriteManager.getSprite(this.isBreakable() ? 0 : 1,8);
    }
}
