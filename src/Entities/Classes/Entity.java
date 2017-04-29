package Entities.Classes;

import Entities.Enum.Direction;
import Entities.Enum.Images;
import Interface.Window;

import java.awt.*;
import java.util.ArrayList;

/**
 * Entity Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public abstract class Entity{
    private final String name;
    private final Point position;
    private Images images;
    private int imageIndex;
    private static final ArrayList<Entity> entities = new ArrayList<>();

    Entity(String name, Point position, Images images) {
        this.name = name;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        entities.add(this);
    }

    /**
     * Remove the entity from the entity array, cutting all reference to him so the gc will be free to free the memory.
     */
    public void destroy() {
        entities.remove(this);
    }

    /**
     * Give the name of the entity.
     * @return the name of the entity.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the image of the entity.
     * @param images the image to use.
     */
    void setImages(Images images) {
        this.images = images;
    }

    /**
     * Return the actual position of the entity.
     * @return the position of the entity.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Return the ArrayList containing all the images used by the entity.
     * @return The images of the entity.
     * @see ArrayList
     */
    public Images getImageEnum() {
        return this.images;
    }

    /**
     * Return a new position moved in a given direction according to his speed.
     * @param  direction  the direction where the entity should go.
     * @param  speed the speed of the movement.
     */
    void move(Direction direction, int speed) {
        this.getPosition().x += direction.getDirection().getX()*speed;
        this.getPosition().y += direction.getDirection().getY()*speed;
    }

    /**
     * Directly set the position of the entity.
     * Only to use with collision.
     * @param x the x coordinate of the entity.
     * @param y the y coordinate of the entity.
     */
    public void setPosition(int x, int y)
    {
        this.position.x = x;

        this.position.y = y;
    }

    /**
     * Check if there is a collision between this entity and another.
     * @param entity the entity to compare.
     * @return true if there is a collision.
     */
    public boolean checkCollision(Entity entity)
    {
        return this.getBBox().intersects(entity.getBBox());
    }

    /**
     * Give the spot that face the entity to allow to place it something.
     * @param dir the direction the entity is facing.
     * @return the spot the face the entity.
     * @see Point
     * @see Direction
     */
    Point getSpot(Direction dir)
    {
        Point pos = null;

        switch (dir)
        {
            case up:
                    pos = new Point(this.getPosition().x,this.getPosition().y- Window.getWindowSize().width/20);
                break;

            case right:
                    pos = new Point(this.getPosition().x+ Window.getWindowSize().width/20,this.getPosition().y);
                break;

            case left:
                    pos = new Point(this.getPosition().x- Window.getWindowSize().width/20,this.getPosition().y);
                break;

            case down:
                    pos = new Point(this.getPosition().x,this.getPosition().y+ Window.getWindowSize().width/20);
                break;
        }

        return pos;
    }

    /**
     * Do all the action needed by the entity during a turn ( move, die, ...).
     */
    public abstract void tick();

    /**
     * Change the index of the image used by the entity to play an animation.
     * @see Images
     */
    public void changeImageIndex() {
        if(imageIndex < this.getImageEnum().getImages().length-1)
            this.imageIndex++;
        else
            this.imageIndex=0;
    }

    /**
     * Give the List containing all the created entity.
     * @return the list with all the created entity.
     * @see ArrayList
     */
    public static ArrayList<Entity> getEntities()
    {
        return entities;
    }

    /**
     * Give the actual image index of the entity.
     * @return image index of the entity.
     * @see Images
     */
    public int getImageIndex()
    {
        return this.imageIndex;
    }

    /**
     * Reset the image index of the entity.
     * Only used with Bomb
     */
    void resetImageIndex()
    {
        this.imageIndex = 2;
    }

    /**
     * Give the bounding box of the entity.
     * @return the bounding box of the entity.
     * @see Rectangle
     */
    Rectangle getBBox()
    {
        return new Rectangle(this.position.x,this.position.y,Window.getWindowSize().width/15,Window.getWindowSize().width/15);
    }

    /**
     * Clear the List of all the entity, to allow a brand new game to start.
     * @see ArrayList
     */
    public static void clear()
    {
        entities.clear();
    }

    public boolean equals(Object o)
    {
        if(o instanceof Entity)
        {
            return ((Entity) o).getPosition().equals(this.getPosition()) && ((Entity) o).getName().equals(this.getName());
        }

        return false;
    }
}