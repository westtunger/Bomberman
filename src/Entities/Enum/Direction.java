package Entities.Enum;

import java.awt.*;

/**
 * Direction Enumerator.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public enum Direction {
    up(0,-1), right(1,0), down(0,1), left(-1,0);

    private int x;
    private  int y;

    Direction(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the information needed to move in this direction.
     *
     * @see Point
     */
    public Point getDirection()
    {
        return new Point(this.x,this.y);
    }
}
