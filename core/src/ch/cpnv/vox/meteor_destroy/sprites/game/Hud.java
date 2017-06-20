package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.sprites.hud.Life;

/**
 * This is the HUD of the game, here we display lives of the player and score
 */

public class Hud {

    private int mNbOfLife;
    public  static int score;

    private ArrayList<Life> mLives;
    private Life mLife1;
    private Life mLife2;
    private Life mLife3;

    private float mMarginX = Helpers.getWidthAdaptToResolution(40);
    private float mMarginY = Helpers.getHeightAdaptToResolution(40);

    private BitmapFont mFont;
    private GlyphLayout mGlyphLayout;

    /**
     * Constructor with initialization
     */
    public Hud (){
        init();
    }

    /**
     * Initialization method, init the score, create array list of Life, positions
     */
    private void init(){
        score = 0;
        // Init lives
        mLives = new ArrayList();
        mNbOfLife = Player.life;
        mLife1 = new Life();
        mLife2 = new Life();
        mLife3 = new Life();

        // Set the positions
        mLife1.setPosition(mMarginX, Helpers.MOBILE_HEIGHT - mLife1.getHeight() - mMarginY);
        mLife2.setPosition(mLife1.getX() + mLife1.getWidth() + mMarginX, Helpers.MOBILE_HEIGHT - mLife2.getHeight() - mMarginY);
        mLife3.setPosition(mLife2.getX() + mLife2.getWidth() + mMarginX, Helpers.MOBILE_HEIGHT - mLife3.getHeight() - mMarginY);

        // Fill the lives arrayList
        mLives.add(mLife1);
        mLives.add(mLife2);
        mLives.add(mLife3);
        initFont();
    }

    /**
     * Font initialization
     */
    private void initFont() {
        // get the font which preloaded
        mFont = Helpers.openSans_70;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        mGlyphLayout = new GlyphLayout();
    }

    /**
     * Update, here we do logical things. Called from GameState
     */
    public void update(){
        mNbOfLife = Player.life;
        mGlyphLayout.setText(mFont, String.valueOf(score));
    }

    /**
     * Draw the sprites and font, called from GameState render method
     * @param sb The spriteBatch require to display element on screen
     */
    public void render(SpriteBatch sb) {
        // Display only lives we have
        for(int i=0; i < mNbOfLife; i++){
            mLives.get(i).draw(sb);
        }
        // Display the score
        mFont.draw(sb, mGlyphLayout, Helpers.MOBILE_WIDTH - mGlyphLayout.width - mMarginX, Helpers.MOBILE_HEIGHT - mGlyphLayout.height);
    }

    /**
     * Dispose, to prevent memory leaks (Called from GameState)
     */
    public void dispose(){
        mLife1.getTexture().dispose();
        mLife2.getTexture().dispose();
        mLife3.getTexture().dispose();
    }

}
