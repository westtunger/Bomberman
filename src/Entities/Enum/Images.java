package Entities.Enum;

import java.util.ArrayList;

/**
 * Images Enum.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public enum Images {

    playerOneFront(new String[]{"Sprites/Bomberman/Front/S1.png","Sprites/Bomberman/Front/S2.png","Sprites/Bomberman/Front/S3.png","Sprites/Bomberman/Front/S4.png","Sprites/Bomberman/Front/S5.png","Sprites/Bomberman/Front/S6.png","Sprites/Bomberman/Front/S7.png","Sprites/Bomberman/Front/S8.png"}),
    playerOneBack(new String[]{"Sprites/Bomberman/Back/S1.png","Sprites/Bomberman/Back/S2.png","Sprites/Bomberman/Back/S3.png","Sprites/Bomberman/Back/S4.png","Sprites/Bomberman/Back/S5.png","Sprites/Bomberman/Back/S6.png","Sprites/Bomberman/Back/S7.png","Sprites/Bomberman/Back/S8.png"}),
    playerOneLeft(new String[]{"Sprites/Bomberman/Left/S1.png","Sprites/Bomberman/Left/S2.png","Sprites/Bomberman/Left/S3.png","Sprites/Bomberman/Left/S4.png","Sprites/Bomberman/Left/S5.png","Sprites/Bomberman/Left/S6.png","Sprites/Bomberman/Left/S7.png","Sprites/Bomberman/Left/S8.png"}),
    playerOneRight(new String[]{"Sprites/Bomberman/Right/S1.png","Sprites/Bomberman/Right/S2.png","Sprites/Bomberman/Right/S3.png","Sprites/Bomberman/Right/S4.png","Sprites/Bomberman/Right/S5.png","Sprites/Bomberman/Right/S6.png","Sprites/Bomberman/Right/S7.png","Sprites/Bomberman/Right/S8.png"}),
    playerTwoFront(new String[]{"Sprites/Creep/Front/S1.png","Sprites/Creep/Front/S2.png","Sprites/Creep/Front/S3.png","Sprites/Creep/Front/S4.png","Sprites/Creep/Front/S5.png","Sprites/Creep/Front/S6.png"}),
    playerTwoBack(new String[]{"Sprites/Creep/Back/S1.png","Sprites/Creep/Back/S2.png","Sprites/Creep/Back/S3.png","Sprites/Creep/Back/S4.png","Sprites/Creep/Back/S5.png","Sprites/Creep/Back/S6.png"}),
    playerTwoLeft(new String[]{"Sprites/Creep/Left/S1.png","Sprites/Creep/Left/S2.png","Sprites/Creep/Left/S3.png","Sprites/Creep/Left/S4.png","Sprites/Creep/Left/S5.png","Sprites/Creep/Left/S6.png"}),
    playerTwoRight(new String[]{"Sprites/Creep/Right/S1.png","Sprites/Creep/Right/S2.png","Sprites/Creep/Right/S3.png","Sprites/Creep/Right/S4.png","Sprites/Creep/Right/S5.png","Sprites/Creep/Right/S6.png"}),
    bomb(new String[]{"Sprites/Bomb/B1.png","Sprites/Bomb/B2.png","Sprites/Bomb/B3.png","Sprites/Bomb/B4.png"}),
    explosion(new String[]{"Sprites/Flame/F1.png","Sprites/Flame/F2.png","Sprites/Flame/F3.png","Sprites/Flame/F4.png","Sprites/Flame/F5.png"}),
    breakableWall(new String[]{"Sprites/Blocks/BW.png"}),
    powerUp(new String[]{"Sprites/Powerups/PUPower.png"}),
    speedUp(new String[]{"Sprites/Powerups/PUSpeed.png"}),
    bombUp(new String[]{"Sprites/Powerups/PUBomb.png"}),
    unbreakableWall(new String[]{"Sprites/Blocks/SW.png"});

    private final String[] images;

    Images(String[] images)
    {
        this.images = images;
    }

    /**
     * Return the String array of the given entity, with the path of each images.
     *
     * @see ArrayList
     */
    public String[] getImages()
    {
        return this.images;
    }
}
