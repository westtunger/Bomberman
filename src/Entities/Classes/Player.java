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
    private int speed = 1;
    private int nbBombPlaced = 0;
    private int playerNumber;
    private Direction dir;
    private boolean isAlive = true;

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
        addEntity(new Bomb(this.power,this.move(dir,1),this));
        this.augmentNbBombPlaced();
    }

    /**
     * Return the images needed in function of the player number.
     * @param number the number of the player.
     */
    public Images getPlayerImages(int number)
    {
        if(number == 0)
            return Images.playerOneFront;
        else
            return Images.playerTwoFront;
    }

    //Getters, setters and modifiers

    /**
     * Return the power of the bomb planted by the player.
     */
    public int getPower() {
        return power;
    }

    /**
     * Augment the power of the bomb planted by the player.
     */
    public void augmentPower() {
        this.power++;
    }

    /**
     * Return the maximum number od bomb usable at the same time.
     */
    public int getNbBombMax() {
        return nbBombMax;
    }

    /**
     * Augment the max number of bomb usable at the same time.
     */
    public void augmentNbBombMax() {
        this.nbBombMax++;
    }

    /**
     * Return the speed of the player
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Augment the speed of the player.
     */
    public void augmentSpeed() {
        this.speed++;
    }

    /**
     * Return the number of bomb already placed by the player.
     */
    public int getNbBombPlaced() {
        return nbBombPlaced;
    }

    /**
     * Augment the number of bom already placed by the player.
     */
    public void augmentNbBombPlaced() {
        if(this.nbBombPlaced+1 <= this.getNbBombMax())
            this.nbBombPlaced++;
    }

    /**
     * Reduce the number of bom already placed by the player.
     */
    public void reduceNbBombPlaced() {
        this.nbBombPlaced--;
    }

    /**
     * Return the player number.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Set the player number.
     * @param playerNumber the number of the player
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * Return true if the player is alive, otherwise false.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Set the actual state of the player.
     * @param alive the state of the player.
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
