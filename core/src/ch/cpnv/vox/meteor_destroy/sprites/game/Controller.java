package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loic.DESSAULES on 24.05.2017.
 */

public class Controller{

    private Sprite left;
    private Sprite right;

    public Controller(){
        init();
    }

    private void init() {
        // Create sprites
        left = new Sprite(new Texture("controls/left.png"));
        right = new Sprite(new Texture("controls/right.png"));

        // Set size
        left.setSize(Helpers.getWidthAdaptToResolution(left.getWidth()), Helpers.getHeightAdaptToResolution(left.getHeight()));
        right.setSize(Helpers.getWidthAdaptToResolution(right.getWidth()), Helpers.getHeightAdaptToResolution(right.getHeight()));

        // Set position (fixed)
        left.setPosition(Helpers.getWidthAdaptToResolution(50), Helpers.getHeightAdaptToResolution(50));
        right.setPosition(left.getWidth() + Helpers.getWidthAdaptToResolution(80), Helpers.getHeightAdaptToResolution(50));
    }

    // Draw the sprites, called from GameState render method
    public void render(SpriteBatch sb) {
        left.draw(sb);
        right.draw(sb);
    }

    // Dispose, to prevent memory leaks (Called from GameState)
    public void dispose(){
        left.getTexture().dispose();
        right.getTexture().dispose();
    }

}
