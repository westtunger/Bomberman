package Entities.Classes;

import Entities.Enum.Direction;
import Entities.Enum.Images;
import Interface.*;
import Interface.Window;

import java.awt.*;
import java.util.ArrayList;

/**
 * Entity Object.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public abstract class Entity{
    private String name;
    private Point position;
    private Images images;
    private int imageIndex;
    private static ArrayList<Entity> entities = new ArrayList<>();

    Entity(String name, Point position, Images images) {
        this.name = name;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        addEntity(this);
    }

    /**
     * Remove the entity from the entity array, cutting all reference to him so the gc will be free to free the memory.
     */
    public void destroy() {
        entities.remove(this);
    }

    /**
     * Return the name of the entity.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the image of the entity
     * @param images the image to use.
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * Return the actual position of the entity.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Return the ArrayList containing all the images used by the entity.
     * @see ArrayList
     */
    public Images getImageEnum() {
        return this.images;
    }

    /**
     * Return a new position moved in a given direction according to his speed.
     *
     * @param  direction  the direction where the entity should go.
     * @param  speed the speed of the movement.
     */
    public Point move(Direction direction, int speed) {
        return new Point(this.getPosition().x += direction.getDirection().getX()*speed,this.getPosition().y += direction.getDirection().getY()*speed);
    }

    public Point getSpot(Direction dir)
    {
        Point pos = null;

        switch (dir)
        {
            case up:
                pos = new Point(this.getPosition().x,this.getPosition().y- Window.getWindowSize().width/15);
                break;

            case right:
                pos = new Point(this.getPosition().x+ Window.getWindowSize().width/15,this.getPosition().y);
                break;

            case left:
                pos = new Point(this.getPosition().x- Window.getWindowSize().width/15,this.getPosition().y);
                break;

            case down:
                pos = new Point(this.getPosition().x,this.getPosition().y+ Window.getWindowSize().width/15);
                break;
        }

        return pos;
    }

    /**
     * Add a new entity to the game.
     * @param entity the entity to add.
     */
    public static void addEntity(Entity entity)
    {
        entities.add(entity);
    }

    /**
     * Do all the action needed by the entity during a turn ( move, die, ...).
     */
    public abstract void tick();

    /**
     * Change the index of the image used by the entity to play an animation.
     */
    public void changeImageIndex() {
        if(imageIndex < this.getImageEnum().getImages().length-1)
            this.imageIndex++;
        else
            this.imageIndex=0;
    }

    public static ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public int getImageIndex()
    {
        return this.imageIndex;
    }

    public void setImageIndex(int imageIndex)
    {
        this.imageIndex = imageIndex;
    }
}
