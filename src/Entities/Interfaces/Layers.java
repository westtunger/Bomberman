package Entities.Interfaces;

/**
 * Créé par Westtunger le 21-06-17.
 */
public interface Layers {
    String collidable = "COLLIDABLE";
    String pickable = "PICKABLE";
    String bombTemp = "BOMBTEMP";

    String getLayer();
}
