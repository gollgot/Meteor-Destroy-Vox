package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * This class contains all static methods can help us, so we can call them where we want.
 */

public abstract class Helpers {

    public static final int MOBILE_WIDTH = Gdx.graphics.getWidth();
    public static final int MOBILE_HEIGHT = Gdx.graphics.getHeight();

    public static BitmapFont openSans_100;
    public static BitmapFont openSans_70;
    public static BitmapFont kenVector_150;

    /**
     * Take the param and return a with adapted to the device resolution which run the game. All my
     * images created for a 1440px width x 2560px height, so I can use a rule of 3 to calculate all width
     * I need for the device.
     * @param widthToChange The width you want to adapt
     * @return The new width adapted to your mobile resolution
     */
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

    /**
     * Take the param and return an height adapted to the device resolution which run the game. All my
     * images created for a 1440px width x 2560px height, so I can use a rule of 3 to calculate all heights
     * I need for the device.
     * @param heightToChange The height you want to adapt
     * @return The new height adapted to your mobile resolution
     */
    /* Same as getWidthAdaptToResolution, but for the height*/
    public static float getHeightAdaptToResolution(float heightToChange){
        int referenceHeight = 2560;
        float newHeight = heightToChange * Gdx.graphics.getHeight() / referenceHeight;
        return newHeight;
    }

    /**
     * Method called to load all the fonts we will use.
     */
    static public void loadFonts() {
        // openSans_100
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));

        parameter.size = (int) Helpers.getHeightAdaptToResolution(100);
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.WHITE;
        openSans_100 = generator.generateFont(parameter);

        // openSans_70
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));

        parameter2.size = (int) Helpers.getHeightAdaptToResolution(70);
        parameter2.borderWidth = 1;
        parameter2.borderColor = Color.BLACK;
        parameter2.color = Color.WHITE;
        openSans_70 = generator2.generateFont(parameter2);

        // kenVector_150
        FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator generator3 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenvector_future.ttf"));

        parameter3.size = (int) Helpers.getHeightAdaptToResolution(150);
        parameter3.borderWidth = 1;
        parameter3.borderColor = Color.BLACK;
        parameter3.color = Color.WHITE;
        kenVector_150 = generator3.generateFont(parameter3);
    }

}
