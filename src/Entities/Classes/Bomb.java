package Entities.Classes;

import Entities.Enum.Direction;
import Entities.Enum.Images;

import java.awt.*;

/**
 * Bomb Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Bomb extends Entity {

    private int power;
    private int timer = 10;
    private Player owner;

    public Bomb(int power, Point pos, Player owner)
    {
        super("Bomb",pos, Images.bomb);
        this.power = power;
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
        addEntity(new Explosion(this.power, Direction.up,this.move(Direction.up,1)));
        addEntity(new Explosion(this.power, Direction.right,this.move(Direction.right,1)));
        addEntity(new Explosion(this.power, Direction.down,this.move(Direction.down,1)));
        addEntity(new Explosion(this.power, Direction.left,this.move(Direction.left,1)));

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
}
