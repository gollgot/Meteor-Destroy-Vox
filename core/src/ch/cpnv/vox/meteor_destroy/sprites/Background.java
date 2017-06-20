package ch.cpnv.vox.meteor_destroy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Background class, just a little sprite with 0;0 position
 */

public class Background extends Sprite {

    /**
     * Constructor with initialization
     */
    public Background(){
        super(new Texture("general/background.png"));
        init();
    }

    /**
     * initialization, set the size and the position
     */
    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX(0);
        setY(0);
    }
}
