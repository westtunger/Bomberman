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

    public PowerUp(PowerUpTypes type, Point pos)
    {
        super("PowerUp",pos,null);
        this.type = type;

        this.setImages(this.getPowerUpImages(type));
    }

    @Override
    public void tick()
    {

    }

    /**
     * Return the images needed in function of the type.
     * @param type the type of the power up.
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

    public PowerUpTypes getType()
    {
        return this.type;
    }
}
