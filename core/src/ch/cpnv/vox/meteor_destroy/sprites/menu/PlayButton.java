package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 21.05.2017.
 */

public class PlayButton extends Sprite{

    public Sprite playButton = this;

    public PlayButton(){
        super(new Texture("menu/play_button.png"));
        init();
    }

    private void init(){
        // Modify the image size in proportion of the mobile resolution
        playButton.setSize(Helpers.getWidthAdaptToResolution(this.getWidth()), Helpers.getHeightAdaptToResolution(this.getHeight()));
        // Set the position (fixed)
        playButton.setX((Helpers.MOBILE_WIDTH / 2) - (playButton.getWidth() / 2));
        playButton.setY((Helpers.MOBILE_HEIGHT / 2) - (playButton.getHeight() / 2));
    }

}
