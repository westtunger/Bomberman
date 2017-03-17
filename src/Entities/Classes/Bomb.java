package Entities.Classes;

import Entities.Enum.Direction;
import Entities.Enum.Images;
import Interface.*;
import Interface.Window;

import java.awt.*;

/**
 * Bomb Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Bomb extends Entity {

    private int power;
    private int timer = 35;
    private Player owner;

    public Bomb(int power, Point pos, Player owner)
    {
        super("Bomb",pos, Images.bomb);
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
    private void explode()
    {
        new Explosion(this.power, Direction.up,this.getSpot(Direction.up));
        new Explosion(this.power, Direction.right,this.getSpot(Direction.right));
        new Explosion(this.power, Direction.down,this.getSpot(Direction.down));
        new Explosion(this.power, Direction.left,this.getSpot(Direction.left));

        owner.reduceNbBombPlaced();

        this.destroy();
    }

    public void changeImageIndex() {
        int index = this.getImageIndex();

        if(index<3)
        {
           super.changeImageIndex();
        }
        else
        {
            this.setImageIndex(2);
        }
    }

    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Window.getWindowSize().width/20,Window.getWindowSize().width/20);
    }
}
