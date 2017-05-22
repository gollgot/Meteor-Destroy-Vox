package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 21.05.2017.
 */

public class Title extends Sprite {

    public Title(){
        super(new Texture("menu/title.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY(Helpers.MOBILE_HEIGHT - getHeight() - Helpers.getHeightAdaptToResolution(200));
    }

}
