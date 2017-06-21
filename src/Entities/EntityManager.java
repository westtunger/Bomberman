package Entities;

import Entities.Classes.*;

import java.util.ArrayList;

/**
 * EntityManager Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class EntityManager {
    private final ArrayList<Bomb> bombs;
    private final ArrayList<Explosion> explosions;
    private final ArrayList<Player> players;
    private final ArrayList<PowerUp> powerUps;
    private final ArrayList<Wall> walls;
    private static EntityManager manager;

    public static EntityManager getManager()
    {
        if(manager == null)
        {
            manager = new EntityManager();
        }

        return manager;
    }

    private EntityManager(){
        bombs = new ArrayList<>();
        explosions = new ArrayList<>();
        players = new ArrayList<>();
        powerUps = new ArrayList<>();
        walls = new ArrayList<>();
    }

    public void removeEntity(Entity entity)
    {
        if(entity instanceof Explosion)
        {
            explosions.remove(entity);
        }
        else if(entity instanceof Wall)
        {
            walls.remove(entity);
        }
        else if(entity instanceof Bomb)
        {
            bombs.remove(entity);
        }
        else if(entity instanceof PowerUp)
        {
            powerUps.remove(entity);
        }
    }

    public void addEntity(Entity entity)
    {
        if(entity instanceof Explosion)
        {
            explosions.add((Explosion) entity);
        }
        else if(entity instanceof Bomb)
        {
            bombs.add((Bomb) entity);
        }
        else if(entity instanceof PowerUp)
        {
            powerUps.add((PowerUp) entity);
        }
        else if(entity instanceof Wall)
        {
            walls.add((Wall) entity);
        }
        else if(entity instanceof Player)
        {
            players.add((Player) entity);
        }
    }

    /**
     * Return a copy of the List containing all the created entity.
     * @return a copy of the list with all the created entity.
     * @see ArrayList
     */
    public ArrayList<Entity> getEntities()
    {
        ArrayList<Entity> entities = new ArrayList<>();

        entities.addAll(walls);
        entities.addAll(bombs);
        entities.addAll(explosions);
        entities.addAll(powerUps);
        entities.addAll(players);

        return entities;
    }

    public ArrayList<Bomb> getBombs() {
        return new ArrayList<>(bombs);
    }

    public ArrayList<Explosion> getExplosions() {
        return new ArrayList<>(explosions);
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public ArrayList<PowerUp> getPowerUps() {
        return new ArrayList<>(powerUps);
    }

    public ArrayList<Wall> getWalls() {
        return new ArrayList<>(walls);
    }

    public void clear()
    {
        walls.clear();
        bombs.clear();
        explosions.clear();
        powerUps.clear();
        players.clear();
    }
}
