package Entities.Classes;

import Entities.Enum.Images;
import Entities.Enum.PowerUpTypes;

import java.awt.*;

/**
 * PowerUp Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class PowerUp extends Entity{
    private final PowerUpTypes type;
    private boolean invinsible;
    private int timer = 20;

    public PowerUp(PowerUpTypes type, Point pos)
    {
        super("PowerUp",pos,null);
        this.type = type;
        this.invinsible = true;

        this.setImages(this.getPowerUpImages(type));
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
            this.invinsible = false;
        }
    }

    public boolean isInvinsible() {
        return invinsible;
    }

    /**
     * Return the images needed in function of the type.
     * @param type the type of the power up.
     * @return the images needed.
     * @see PowerUpTypes
     */
    private Images getPowerUpImages(PowerUpTypes type)
    {
        switch (type)
        {
            case power:
                return Images.powerUp;

            case bomb:
                return Images.bombUp;

            case speed:
                return Images.speedUp;

            default:
                return null;
        }
    }

    /**
     * Give the type of this power up.
     * @return the type of the power up.
     * @see PowerUpTypes
     */
    public PowerUpTypes getType()
    {
        return this.type;
    }
}
