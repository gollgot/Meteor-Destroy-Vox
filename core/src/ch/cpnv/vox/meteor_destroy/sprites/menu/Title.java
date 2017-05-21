package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 21.05.2017.
 */

public class Title extends Sprite {

    private Sprite title = this;

    public Title(){
        super(new Texture("menu/title.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        title.setSize(Helpers.getWidthAdaptToResolution(title.getWidth()), Helpers.getHeightAdaptToResolution(title.getHeight()));
        // Set the position (fixed)
        title.setX((Helpers.MOBILE_WIDTH / 2) - (title.getWidth() / 2));
        title.setY(Helpers.MOBILE_HEIGHT - title.getHeight() - Helpers.getHeightAdaptToResolution(200));
    }

}
