package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.states.GameState;
import ch.cpnv.vox.meteor_destroy.states.GameStateManager;
import ch.cpnv.vox.meteor_destroy.states.MenuState;

/**
 * Created by Loïc on 21.05.2017.
 */

public class PlayButton extends Sprite{

    private GameStateManager gsm;

    public PlayButton(GameStateManager gsm){
        super(new Texture("menu/play_button.png"));
        this.gsm = gsm;
        init();
    }

    private void init(){
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(this.getWidth()), Helpers.getHeightAdaptToResolution(this.getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY((Helpers.MOBILE_HEIGHT / 2) - (getHeight() / 2));
    }

}
