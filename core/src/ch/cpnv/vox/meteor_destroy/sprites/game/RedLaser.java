package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 24.05.2017.
 */

public class RedLaser extends Sprite{

    public RedLaser(){
        super(new Texture("game/redLaser.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY(Helpers.getHeightAdaptToResolution(500));
    }



}
