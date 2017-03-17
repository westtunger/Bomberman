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
    private int speed = 5;
    private int nbBombPlaced = 0;
    private int playerNumber;
    private Direction dir = Direction.down;

    public Player(int playerNumber,String name, Point position){
        super(name,position, null);

        this.setPlayerNumber(playerNumber);
        this.setImages(this.getPlayerImages(playerNumber));
    }

    @Override
    public void tick()
    {
    }

    /**
     * Plant a new bomb in front of the player.
     */
    public void plant()
    {
        if(this.nbBombPlaced<this.nbBombMax)
        {
            this.augmentNbBombPlaced();
            new Bomb(this.power,this.getSpot(this.dir),this);
        }
    }

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
     */
    private Images getPlayerImages(int number)
    {
        if(number == 0)
            return Images.playerOneFront;
        else
            return Images.playerTwoFront;
    }

    //Getters, setters and modifiers

    /**
     * Augment the power of the bomb planted by the player.
     */
    public void augmentPower() {
        if(this.power <5)
            this.power++;
    }

    /**
     * Return the maximum number od bomb usable at the same time.
     */
    private int getNbBombMax() {
        return nbBombMax;
    }

    /**
     * Augment the max number of bomb usable at the same time.
     */
    public void augmentNbBombMax() {
        if(this.nbBombMax < 5)
            this.nbBombMax++;
    }

    /**
     * Augment the speed of the player.
     */
    public void augmentSpeed() {
        if(this.speed < 10)
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
     * Return the player number.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    public Direction getDir()
    {
        return this.dir;
    }
    /**
     * Set the player number.
     * @param playerNumber the number of the player
     */
    private void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Interface.Window.getWindowSize().width/20, Interface.Window.getWindowSize().width/20);
    }
}
