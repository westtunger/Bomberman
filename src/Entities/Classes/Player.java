package Entities.Classes;

import Entities.Enum.Direction;
import Entities.Enum.Images;

import java.awt.*;

/**
 * Player Object.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Player extends Entity
{
    private int power = 1;
    private int nbBombMax = 1;
    private int speed = 7;
    private int nbBombPlaced = 0;
    private int playerNumber;
    private boolean canWalkOnBomb = false;
    private Direction dir = Direction.down;
    private int timer;

    public Player(int playerNumber,String name, Point position){
        super(name,position, null);

        this.setPlayerNumber(playerNumber);
        this.setImages(this.getPlayerImages(playerNumber));
    }

    public boolean isCanWalkOnBomb() {
        return canWalkOnBomb;
    }

    @Override
    public void tick()
    {
        if(timer > 0)
            timer--;


        boolean coll = false;

        if(this.canWalkOnBomb)
            for(Entity entity : Entity.getEntities())
            {
                if(entity instanceof Bomb && ((Bomb)entity).getOwner().equals(this) && this.checkCollision(entity))
                {
                    coll = true;
                }
            }

        if(!coll)
            this.canWalkOnBomb = false;
    }

    /**
     * Plant a new bomb below the player.
     * @see Bomb
     */
    public void plant()
    {
        if(this.nbBombPlaced<this.nbBombMax && timer == 0)
        {
            timer = 10;
            this.augmentNbBombPlaced();
            new Bomb(this.power, new Point(this.getPosition().x,this.getPosition().y),this);
            this.canWalkOnBomb = true;
        }
    }

    /**
     * Move the player in the given direction, according to his speed.
     * @param dir the direction to move in.
     * @see Direction
     */
    public void move(Direction dir)
    {
        this.dir = dir;
        super.move(dir,this.speed);
        if(this.playerNumber == 0)
            switch (dir)
            {
                case down:
                    this.setImages(Images.playerOneFront);
                    break;

                case left:
                    this.setImages(Images.playerOneLeft);
                    break;

                case right:
                    this.setImages(Images.playerOneRight);
                    break;

                case up:
                    this.setImages(Images.playerOneBack);
                    break;
            }
        else
            switch (dir)
            {
                case down:
                    this.setImages(Images.playerTwoFront);
                    break;

                case left:
                    this.setImages(Images.playerTwoLeft);
                    break;

                case right:
                    this.setImages(Images.playerTwoRight);
                    break;

                case up:
                    this.setImages(Images.playerTwoBack);
                    break;
            }
    }

    /**
     * Return the images needed in function of the player number.
     * @param number the number of the player.
     * @return the images needed.
     */
    private Images getPlayerImages(int number)
    {
        if(number == 0)
            return Images.playerOneFront;
        else
            return Images.playerTwoFront;
    }

    /**
     * Augment the power of the bomb who can be planted by the player.
     */
    public void augmentPower() {
        if(this.power <5)
            this.power++;
    }

    /**
     * Return the maximum number of bomb usable at the same time by the player.
     */
    private int getNbBombMax() {
        return nbBombMax;
    }

    /**
     * Augment the maximum number of bomb usable at the same time by the player.
     */
    public void augmentNbBombMax() {
        if(this.nbBombMax < 5)
            this.nbBombMax++;
    }

    /**
     * Augment the speed of the player.
     */
    public void augmentSpeed() {
        if(this.speed < 12)
        this.speed++;
    }

    /**
     * Augment the number of bom already placed by the player.
     */
    private void augmentNbBombPlaced() {
        if(this.nbBombPlaced < this.getNbBombMax())
            this.nbBombPlaced++;
    }

    /**
     * Reduce the number of bom already placed by the player.
     */
    public void reduceNbBombPlaced() {
        if(this.nbBombPlaced > 0)
            this.nbBombPlaced--;
    }

    /**
     * Give the player number.
     *
     * @return the player number.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Give the direction of the player.
     * @return the direction of the player.
     * @see Direction
     */
    public Direction getDir()
    {
        return this.dir;
    }

    /**
     * Set the player number.
     * @param playerNumber the number of the player.
     */
    private void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Interface.Window.getWindowSize().width/20, Interface.Window.getWindowSize().width/20);
    }

    public boolean equals(Object o)
    {
        if(o instanceof Player)
        {
            return super.equals(this) && ((Player) o).getPlayerNumber() == this.getPlayerNumber();
        }

        return false;
    }
}
