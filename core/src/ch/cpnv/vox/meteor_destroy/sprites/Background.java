package ch.cpnv.vox.meteor_destroy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 21.05.2017.
 */

public class Background extends Sprite {

    private Sprite background = this;

    public Background(){
        super(new Texture("background.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        background.setSize(Helpers.getWidthAdaptToResolution(background.getWidth()), Helpers.getHeightAdaptToResolution(background.getHeight()));
        // Set the position (fixed)
        background.setX(0);
        background.setY(0);
    }
}
