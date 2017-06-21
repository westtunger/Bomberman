package Entities.Classes;

import Entities.EntityManager;
import Entities.Enum.Direction;
import Entities.Interfaces.Layers;
import Entities.SpriteManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * Player Object.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Player extends Entity
{
    private static int nbPlayers;

    private final ArrayList<Bomb> bombs;
    private final int powerMax = 5;
    private final int speedMax = 12;
    private final int nbBombMax = 5;
    private int nbBomb = 1;
    private int power = 1;
    private int speed = 7;
    private final int playerNumber;
    private Direction dir = Direction.down;
    private int timer;

    public Player(String name, Point position) {
        super(name, position, 8, Layers.collidable);
        nbPlayers++;
        this.bombs = new ArrayList<>();
        this.playerNumber = nbPlayers;
    }

    @Override
    public void tick()
    {
        if(timer > 0)
            timer--;

        for(Bomb bomb : new ArrayList<>(bombs))
        {
            if(bomb.isDestroyed())
                bombs.remove(bomb);
        }
    }

    /**
     * Plant a new bomb below the player.
     * @see Bomb
     */
    public void plant()
    {
        if(this.bombs.size() < this.nbBomb && timer == 0)
        {
            timer = 10;

            Bomb bomb = new Bomb(this.power, new Point(this.getPosition()));
            this.bombs.add(bomb);
            EntityManager.getManager().addEntity(bomb);
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

        processMove();
    }

    /**
     * Move in a given direction according to the player speed.
     */
    public void processMove() {
        this.getPosition().x += this.dir.getDirection().getX()*this.speed;
        this.getPosition().y += this.dir.getDirection().getY()*this.speed;
    }

    /**
     * Cancel the last move.
     */
    public void cancelMove() {
        this.getPosition().x -= this.dir.getDirection().getX()*this.speed;
        this.getPosition().y -= this.dir.getDirection().getY()*this.speed;
    }

    /**
     * Pick up a given power up and apply the correct bonus to the player.
     * @param powerUp the powerup to pick up.
     * @see PowerUp
     */
    public void pickUpPowerUp(PowerUp powerUp)
    {
        switch (powerUp.getType())
        {
            case PowerUp.BOMB:
                this.augmentNbBomb();
                break;

            case PowerUp.POWER:
                this.augmentPower();
                break;

            case PowerUp.SPEED:
                this.augmentSpeed();
                break;
        }

        powerUp.onExplode();
    }

    /**
     * Augment the power of the bomb who can be planted by the player.
     */
    public void augmentPower() {
        if(this.power <this.powerMax)
            this.power++;
    }

    /**
     * Augment the maximum number of bomb usable at the same time by the player.
     */
    public void augmentNbBomb() {
        if(this.nbBomb < this.nbBombMax)
            this.nbBomb++;
    }

    /**
     * Augment the speed of the player.
     */
    public void augmentSpeed() {
        if(this.speed < this.speedMax)
        this.speed++;
    }

    /**
     * Give the player number.
     *
     * @return the player number.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public Rectangle getBBox()
    {
        return new Rectangle(this.getPosition().x,this.getPosition().y, Visual.Window.getWindowSize().width/20, Visual.Window.getWindowSize().width/20);
    }

    public boolean equals(Object o)
    {
        if(o instanceof Player)
        {
            return super.equals(this) && ((Player) o).getPlayerNumber() == this.getPlayerNumber();
        }

        return false;
    }

    /**
     * Reset the number of players.
     */
    public static void resetNumbers()
    {
        nbPlayers = 0;
    }

    @Override
    public Image getImage() {
        int y=this.getPlayerNumber() == 1 ? 0 : 4;

        switch (this.dir)
        {
            case up:
                    y+=1;
                break;

            case down:
                    y+=0;
                break;

            case left:
                    y+=2;
                    break;

            case right:
                    y+=3;
                break;
        }

        return SpriteManager.getSprite(this.getImageIndex(),y);
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    @Override
    public void onExplode() {
        this.destroy();
    }

    public int getOtherPlayerNumber()
    {
        return this.getPlayerNumber() == 2 ? 1 : 2;
    }
}
