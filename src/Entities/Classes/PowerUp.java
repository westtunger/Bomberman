package Entities.Classes;

import Entities.Interfaces.Layers;
import Entities.SpriteManager;

import java.awt.*;

/**
 * PowerUp Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class PowerUp extends Entity {
    public static final int POWER = 0;
    public static final int BOMB = 1;
    public static final int SPEED = 2;
    private final int type;
    private boolean invincible;
    private int timer = 20;

    public PowerUp(int type, Point pos)
    {
        super("PowerUp",pos,0, Layers.pickable);
        this.type = type;
        this.invincible = true;
    }

    @Override
    public void tick()
    {
        if(timer> 0)
        {
            timer--;
        }
        else
        {
            this.invincible = false;
        }
    }

    public boolean isInvincible() {
        return invincible;
    }

    /**
     * Give the type of this power up.
     * @return the type of the power up.
     */
    public int getType()
    {
        return this.type;
    }

    @Override
    public Image getImage() {
        return SpriteManager.getSprite(this.getType(),11);
    }

    @Override
    public void onExplode() {
        if(!this.isInvincible())
            this.destroy();
    }
}
