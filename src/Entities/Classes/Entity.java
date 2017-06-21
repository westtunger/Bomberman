package Entities.Classes;

import Entities.EntityManager;
import Entities.Enum.Direction;
import Entities.Interfaces.Explodable;
import Entities.Interfaces.Layers;
import Visual.Window;

import java.awt.*;
import java.util.ArrayList;

/**
 * Entity Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public abstract class Entity implements Layers, Explodable{
    private final int nbAnim;
    private final String name;
    private final Point position;
    private int imageIndex;
    private static final EntityManager entityManager = EntityManager.getManager();
    private String layer;

    Entity(String name, Point position, int nbAnim, String layer) {
        this.name = name;
        this.position = position;
        this.imageIndex = 0;
        this.nbAnim = nbAnim;
        this.layer = layer;
    }

    /**
     * Remove the entity from the entities array.
     */
    public void destroy() {
        entityManager.removeEntity(this);
    }

    /**
     * Give the name of the entity.
     * @return the name of the entity.
     */
    private String getName() {
        return this.name;
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
    public abstract Image getImage();

    /**
     * Check if there is a collision between this entity and another.
     * @return the entity who collide with this.
     */
    public Entity checkCollision()
    {
        for(Entity entity : entityManager.getEntities())
        {
            if(this.getBBox().intersects(entity.getBBox()) && entity != this)
            {
                return entity;
            }
        }

        return null;
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
     */
    public void changeImageIndex() {
        if(imageIndex < this.nbAnim-1)
            this.imageIndex++;
        else
            this.imageIndex=0;
    }

    /**
     * Give the actual image index of the entity.
     * @return image index of the entity.
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

    public boolean equals(Object o)
    {
        if(o instanceof Entity)
        {
            return ((Entity) o).getPosition().equals(this.getPosition()) && ((Entity) o).getName().equals(this.getName());
        }

        return false;
    }

    public String getLayer()
    {
        return this.layer;
    }

    public void setLayer(String layer)
    {
        this.layer = layer;
    }
}