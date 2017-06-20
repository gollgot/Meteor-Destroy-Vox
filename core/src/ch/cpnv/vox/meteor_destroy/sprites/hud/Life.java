package ch.cpnv.vox.meteor_destroy.sprites.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * This class is used in the HUD to display lives on screen (little player images)
 */

public class Life extends Sprite{

    /**
     * Constructor with initialization
     */
    public Life(){
        super(new Texture("hud/life.png"));
        init();
    }

    /**
     * Initialization method
     */
    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
    }

}
