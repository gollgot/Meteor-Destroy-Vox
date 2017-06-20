package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * This class contain the controller used to move our player, shoot, change weapon
 */

public class Controller{

    private Sprite mLeft, mRight, mShoot, mWeapon;
    private Rectangle mLeftBounds, mRightBounds, mShootBounds, mWeaponBounds;

    /**
     * Constructor with initilization
     */
    public Controller(){
        init();
    }

    /**
     * Initialization method, create sprites, set position, create bounds Rectangle
     */
    private void init() {
        // Create sprites
        mLeft = new Sprite(new Texture("controls/mLeft.png"));
        mRight = new Sprite(new Texture("controls/mRight.png"));
        mShoot = new Sprite(new Texture("controls/mShoot.png"));
        mWeapon = new Sprite(new Texture("controls/mWeapon.png"));

        // Set size
        mLeft.setSize(Helpers.getWidthAdaptToResolution(mLeft.getWidth()), Helpers.getHeightAdaptToResolution(mLeft.getHeight()));
        mRight.setSize(Helpers.getWidthAdaptToResolution(mRight.getWidth()), Helpers.getHeightAdaptToResolution(mRight.getHeight()));
        mShoot.setSize(Helpers.getWidthAdaptToResolution(mShoot.getWidth()), Helpers.getHeightAdaptToResolution(mShoot.getHeight()));
        mWeapon.setSize(Helpers.getWidthAdaptToResolution(mWeapon.getWidth()), Helpers.getHeightAdaptToResolution(mWeapon.getHeight()));

        // Set position (fixed)
        mLeft.setPosition(Helpers.getWidthAdaptToResolution(50), Helpers.getHeightAdaptToResolution(50));
        mRight.setPosition(mLeft.getWidth() + Helpers.getWidthAdaptToResolution(80), Helpers.getHeightAdaptToResolution(50));
        mShoot.setPosition(Helpers.MOBILE_WIDTH - Helpers.getWidthAdaptToResolution(50) - mShoot.getWidth(), Helpers.getHeightAdaptToResolution(50));
        mWeapon.setPosition(mShoot.getX() - mWeapon.getWidth() - Helpers.getWidthAdaptToResolution(30), Helpers.getHeightAdaptToResolution(50));

        // I created an invisible rectangle, this will be the limits of the button touch detection
        // !!! Carefull, Touch x-y is from top-mLeft of the screen and rectangle bottom mLeft I calculate in inverse to match with touch x-y!!!
        mLeftBounds = new Rectangle(mLeft.getX(), Helpers.MOBILE_HEIGHT - (mLeft.getY()+ mLeft.getHeight()), mLeft.getWidth(), mLeft.getHeight());
        mRightBounds = new Rectangle(mRight.getX(), Helpers.MOBILE_HEIGHT - (mRight.getY()+ mLeft.getHeight()), mRight.getWidth(), mRight.getHeight());
        mShootBounds = new Rectangle(mShoot.getX(), Helpers.MOBILE_HEIGHT - (mShoot.getY()+ mShoot.getHeight()), mShoot.getWidth(), mShoot.getHeight());
        mWeaponBounds = new Rectangle(mWeapon.getX(), Helpers.MOBILE_HEIGHT - (mWeapon.getY()+ mShoot.getHeight()), mWeapon.getWidth(), mWeapon.getHeight());
    }

    /**
     * Draw the sprites, called from GameState render method
     * @param sb The spriteBatch require to display element on screen
     */
    public void render(SpriteBatch sb) {
        mLeft.draw(sb);
        mRight.draw(sb);
        mShoot.draw(sb);
        mWeapon.draw(sb);
    }

    /**
     * Dispose, to prevent memory leaks (Called from GameState)
     */
    public void dispose(){
        mLeft.getTexture().dispose();
        mRight.getTexture().dispose();
        mShoot.getTexture().dispose();
        mWeapon.getTexture().dispose();
    }

    /**
     * Get the bounds of the left controller button
     * @return The bounds Rectangle of the left controller button
     */
    public Rectangle getLeftBounds() {
        return mLeftBounds;
    }

    /**
     * Get the bounds of the right controller button
     * @return The bounds Rectangle of the right controller button
     */
    public Rectangle getRightBounds() {
        return mRightBounds;
    }

    /**
     * Get the bounds of the shoot controller button
     * @return The bounds Rectangle of the shoot controller button
     */
    public Rectangle getShootBounds() {
        return mShootBounds;
    }

    /**
     * Get the bounds of the change wapon controller button
     * @return The bounds Rectangle of the change weapon controller button
     */
    public Rectangle getWeaponBounds() {
        return mWeaponBounds;
    }
}
