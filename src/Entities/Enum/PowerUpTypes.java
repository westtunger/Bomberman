package Entities.Enum;

/**
 * PowerUpTypes Enum.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public enum PowerUpTypes {
    power(0), bomb(1), speed(2);

    private int value;

    PowerUpTypes(int type)
    {
        this.value = type;
    }

    /**
     * Return the type of the powerUp.
     */
    public int getType()
    {
        return this.value;
    }
}
