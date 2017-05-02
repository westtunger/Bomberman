package Entities.Classes;

import Interface.Window;

import java.awt.*;

import static Entities.Enum.Direction.*;
import static Entities.Enum.Images.bomb;

/**
 * Bomb Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Bomb extends Entity {

    private final int power;
    private int timer = 35;
    private final Player owner;

    public Bomb(int power, Point pos, Player owner)
    {
        super("Bomb",pos, bomb);
        this.power = power;
        this.owner = owner;
    }

    @Override
    public void tick()
    {
        if(this.timer > 0)
            this.timer--;
        else
            this.explode();
    }

    /**
     * Create the explosions at each end of the bomb, then destroy the bomb.
     */
    public void explode()
    {
        new Explosion(this.power, up,this.getSpot(up));
        new Explosion(this.power, right,this.getSpot(right));
        new Explosion(this.power, down,this.getSpot(down));
        new Explosion(this.power, left,this.getSpot(left));
        new Explosion(this.power, null,new Point(this.getPosition().x,this.getPosition().y));

        owner.reduceNbBombPlaced();

        this.destroy();
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
    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Window.getWindowSize().width/20,Window.getWindowSize().width/20);
    }

    public Player getOwner()
    {
        return this.owner;
    }
}