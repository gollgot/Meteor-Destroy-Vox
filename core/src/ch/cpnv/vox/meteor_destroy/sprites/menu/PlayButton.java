package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * This class is used to create a PlayButton
 */

public class PlayButton extends Sprite{

    private Rectangle bounds;

    /**
     * Constructor with initialization
     */
    public PlayButton(){
        super(new Texture("menu/play_button.png"));
        init();
    }

    /**
     * Initialization of the tize, position, rectangle bounds
     */
    private void init(){
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(this.getWidth()), Helpers.getHeightAdaptToResolution(this.getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY((Helpers.MOBILE_HEIGHT / 2) - (getHeight() / 2));

        // I created an invisible rectangle, this will be the limits of the button touch detection
        bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Get the bounds of the button
     * @return A Rectangle object, this is the bounds of the play button
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
