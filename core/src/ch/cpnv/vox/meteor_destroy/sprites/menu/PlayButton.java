package ch.cpnv.vox.meteor_destroy.sprites.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 21.05.2017.
 */

public class PlayButton extends Sprite{

    private Rectangle btnPlayBounds; //(Bounds = limits)
    private Vector3 touch;

    public PlayButton(){
        super(new Texture("menu/play_button.png"));
        init();
    }

    private void init(){
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(this.getWidth()), Helpers.getHeightAdaptToResolution(this.getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY((Helpers.MOBILE_HEIGHT / 2) - (getHeight() / 2));
        // Create a rectangle exactly same x/y as the button (For touch detection)
        btnPlayBounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void handleInput() {
        // Touch listener -> While button is touched
        if(Gdx.input.justTouched()){
            touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Detect if we touched the button
            if(btnPlayBounds.contains(touch.x, touch.y)){
                System.out.println("TOUCH !");
            }
        }
    }
}
