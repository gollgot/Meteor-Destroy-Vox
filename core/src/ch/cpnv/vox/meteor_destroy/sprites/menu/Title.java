package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * This class is used to create the big title in the MenuState. This is not so pretty, it create an image,
 * not a font, because I don't want to load a big font just to do that.
 */

public class Title extends Sprite {

    /**
     * Constructor with initialization
     */
    public Title(){
        super(new Texture("menu/title.png"));
        init();
    }

    /**
     * Initialization method
     */
    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY(Helpers.MOBILE_HEIGHT - getHeight() - Helpers.getHeightAdaptToResolution(200));
    }

}
