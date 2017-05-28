package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loic.DESSAULES on 24.05.2017.
 */

public class Controller{

    private Sprite left, right, shoot, weapon;
    private Rectangle leftBounds, rightBounds, shootBounds, weaponBounds;

    public Controller(){
        init();
    }

    private void init() {
        // Create sprites
        left = new Sprite(new Texture("controls/left.png"));
        right = new Sprite(new Texture("controls/right.png"));
        shoot = new Sprite(new Texture("controls/shoot.png"));
        weapon = new Sprite(new Texture("controls/weapon.png"));
        // Set size
        left.setSize(Helpers.getWidthAdaptToResolution(left.getWidth()), Helpers.getHeightAdaptToResolution(left.getHeight()));
        right.setSize(Helpers.getWidthAdaptToResolution(right.getWidth()), Helpers.getHeightAdaptToResolution(right.getHeight()));
        shoot.setSize(Helpers.getWidthAdaptToResolution(shoot.getWidth()), Helpers.getHeightAdaptToResolution(shoot.getHeight()));
        weapon.setSize(Helpers.getWidthAdaptToResolution(weapon.getWidth()), Helpers.getHeightAdaptToResolution(weapon.getHeight()));

        // Set position (fixed)
        left.setPosition(Helpers.getWidthAdaptToResolution(50), Helpers.getHeightAdaptToResolution(50));
        right.setPosition(left.getWidth() + Helpers.getWidthAdaptToResolution(80), Helpers.getHeightAdaptToResolution(50));
        shoot.setPosition(Helpers.MOBILE_WIDTH - Helpers.getWidthAdaptToResolution(50) - shoot.getWidth(), Helpers.getHeightAdaptToResolution(50));
        weapon.setPosition(shoot.getX() - weapon.getWidth() - Helpers.getWidthAdaptToResolution(30), Helpers.getHeightAdaptToResolution(50));

        // I created an invisible rectangle, this will be the limits of the button touch detection
        // !!! Carefull, Rectangle x-y is from top-left of the screen and sprite bottom left !!!
        leftBounds = new Rectangle(left.getX(), Helpers.MOBILE_HEIGHT - (left.getY()+left.getHeight()), left.getWidth(), left.getHeight());
        rightBounds = new Rectangle(right.getX(), Helpers.MOBILE_HEIGHT - (right.getY()+left.getHeight()), right.getWidth(), right.getHeight());
        shootBounds = new Rectangle(shoot.getX(), Helpers.MOBILE_HEIGHT - (shoot.getY()+shoot.getHeight()), shoot.getWidth(), shoot.getHeight());
        weaponBounds = new Rectangle(weapon.getX(), Helpers.MOBILE_HEIGHT - (weapon.getY()+shoot.getHeight()), weapon.getWidth(), weapon.getHeight());
    }

    // Draw the sprites, called from GameState render method
    public void render(SpriteBatch sb) {
        left.draw(sb);
        right.draw(sb);
        shoot.draw(sb);
        weapon.draw(sb);
    }

    // Dispose, to prevent memory leaks (Called from GameState)
    public void dispose(){
        left.getTexture().dispose();
        right.getTexture().dispose();
        shoot.getTexture().dispose();
        weapon.getTexture().dispose();
    }

    public Rectangle getLeftBounds() {
        return leftBounds;
    }
    public Rectangle getRightBounds() {
        return rightBounds;
    }
    public Rectangle getShootBounds() {
        return shootBounds;
    }
    public Rectangle getWeaponBounds() {
        return weaponBounds;
    }
}
