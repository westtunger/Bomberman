package Entities.Interfaces;

/**
 * Layers Interface.
 *
 * @author Nicolas Viseur
 * @version 1.2
 */
public interface Layers {
    String collidable = "COLLIDABLE";
    String pickable = "PICKABLE";
    String bombTemp = "BOMBTEMP";

    /**
     * Return the layer of the entity.
     * @return the layer of the entity.
     */
    String getLayer();

    /**
     * Change the entity layer to the given one.
     * @param layer The layer to use.
     */
    void setLayer(String layer);
}
