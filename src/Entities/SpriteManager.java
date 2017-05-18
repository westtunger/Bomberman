package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Créé par Westtunger le 14-05-17.
 */
public abstract class SpriteManager {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 86;

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
