package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Loïc on 21.05.2017.
 */

public class Helpers {

    public static final int MOBILE_WIDTH = Gdx.graphics.getWidth();
    public static final int MOBILE_HEIGHT = Gdx.graphics.getHeight();

    /*
     * All the assets images are defined for the max resolution I chose: 1440px width x 2560px height
     * So, I do a simple rule of 3 with the imgWidth or simple width (at the max resolution I chose), the screenWidth, the max resolution width I chose.
     * The result is the new width in proportion of the current screen resolution
     * e.g : I want a 300px margin top, I call getHeightAdaptToResolution(300) and the result is the 300px margin top in proportion of my screen and the reference screen
     * Same for the image size
     */
    public static float getWidthAdaptToResolution(float widthToChange){
        int referenceWidth = 1440;
        float newWidth = widthToChange * Gdx.graphics.getWidth() / referenceWidth;
        return newWidth;
    }
    /* Same as getWidthAdaptToResolution, but for the height*/
    public static float getHeightAdaptToResolution(float heightToChange){
        int referenceHeight = 2560;
        float newHeight = heightToChange * Gdx.graphics.getHeight() / referenceHeight;
        return newHeight;
    }

}
