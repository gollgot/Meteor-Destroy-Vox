package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.VocabularyManager;
import ch.cpnv.vox.meteor_destroy.states.GameState;

/**
 * This class define a Meteor Object. All meteors created in the GameState class, each 2 seconds
 */

public class Meteor extends Sprite {

    private float mVelocityX = 0;
    private boolean mAlive;
    private float mAngle;
    private Rectangle mBounds;

    private String mTranslateWord;
    private Vector2 mTranslateWordPosition;

    private BitmapFont mFont;
    private GlyphLayout mGlyphLayout;

    /**
     * Constructor with initialization
     */
    public Meteor(){
        super(new Texture("game/meteor.png"));
        init();
    }

    /**
     * Initialization method, set the size, position, bounds, etc.
     */
    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position
        setX((float) randomNumber(0, (int) (Helpers.MOBILE_WIDTH - getWidth())));
        setY(Helpers.MOBILE_HEIGHT);
        mAlive = true;
        // Set the origin in center, this way when we rotate, we rotate from the center
        setOrigin(getWidth() / 2, getHeight() / 2);
        mAngle = (float) randomNumber(-3, 3);
        // Set the bounds of the meteor for collision detection
        mBounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
        // Get a random translateWord
        mTranslateWord = VocabularyManager.getRandomTranslateWord();
        initFont();
        mTranslateWordPosition = new Vector2((getX() + getWidth() / 2) - (mGlyphLayout.width / 2), getY() + getHeight() + Helpers.getHeightAdaptToResolution(80));
    }

    /**
     * Font initialization
     */
    private void initFont() {
        // get the font which preloaded
        mFont = Helpers.openSans_70;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        mGlyphLayout = new GlyphLayout();
        mGlyphLayout.setText(mFont, mTranslateWord);
    }

    /**
     * Update, here we do logical things. Called from GameState.update method
     */
    public void update(){
        move();
        rotate(mAngle); // Rotate the meteor (style effect)
        updateBounds();
        checkCollision();
    }

    /**
     * Draw the sprite, called from GameState.render method
     * @param sb The spriteBatch require to display element on screen
     */
    public void render(SpriteBatch sb) {
        draw(sb);
        // Draw the translate word above the meteor
        mFont.draw(sb, mGlyphLayout, mTranslateWordPosition.x, mTranslateWordPosition.y);
    }

    /**
     * move the meteor sprite on the x-y axes and the translate word associate
     */
    private void move() {
        setY(getY() - GameState.meteorSpeedY);
        setX(getX() + mVelocityX);
        mTranslateWordPosition.set((getX() + getWidth() / 2) - (mGlyphLayout.width / 2), getY() + getHeight() + Helpers.getHeightAdaptToResolution(80));
    }

    /**
     * generate a random number min and max included
     * @param min The minimum value you want
     * @param max The maximum value you want
     * @return A random number
     */
    private double randomNumber(int min, int max){
        Random rand = new Random();
        return min + (rand.nextDouble() * (max - min));
    }

    /**
     * The meteor moves, so we have to update the rectabgle bounds of the meteor, to detect in the Laser.checkCollision
     * method if he touches a meteor or not.
     */
    private void updateBounds() {
        mBounds = mBounds.set(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Check collisions, a meteor can only get out of the screen (collision with laser is on Laser.checkCollison method and
     * collision with the player is in the Player.checkCollision method)
     */
    private void checkCollision() {
        // Kill itself if it get out of the screen
        if((getY() + getHeight()) <= 0){
            mAlive = false;
        }
    }

    /**
     * Check if the meteor is alive
     * @return True if he's alive, false otherwise
     */
    public boolean isAlive() {
        return mAlive;
    }

    /**
     * Set the velocity on the X axe
     * @param velocityX The new X velocity
     */
    public void setVelocityX(float velocityX) {
        mVelocityX = velocityX;
    }

    /**
     * get the Rectangle bounds of the meteor
     * @return The Rectangle bounds
     */
    public Rectangle getBounds() {
        return mBounds;
    }

    /**
     * Get the value of the translate word under the meteor
     * @return The value of the translate word under the meteor
     */
    public String getTranslateWord() {
        return mTranslateWord;
    }

    /**
     * Dispose, to prevent memory leaks, called from GameState.dispose method
     */
    public void dispose(){
        getTexture().dispose();
    }

}
