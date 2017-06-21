package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * SpriteManager Class.
 *
 * @author Nicolas Viseur
 * @version 1.2
 */
public abstract class SpriteManager {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 86;

    /**
     * Return the sprite placed at the given coordinate in the spritesheet.
     * @return The sprite placed at the given coordinate in the spritesheet.
     * @param x The x coordinate of the sprite.
     * @param y The y coordinate of the sprite.
     */
    public static BufferedImage getSprite(int x, int y)
    {
        if(spriteSheet == null)
        {
            try {
                spriteSheet = ImageIO.read(new File("Sprites/spriteSheet.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return spriteSheet.getSubimage(x*TILE_SIZE,y*TILE_SIZE,TILE_SIZE,TILE_SIZE);
    }
}
