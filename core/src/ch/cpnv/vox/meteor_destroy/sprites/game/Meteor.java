package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 25.05.2017.
 */

public class Meteor extends Sprite {

    private float velocity = Helpers.getHeightAdaptToResolution(8);
    private boolean alive;
    private float angle;

    public Meteor(){
        super(new Texture("game/meteor.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position
        setX((float) randomNumber(0, (int) (Helpers.MOBILE_WIDTH - getWidth())));
        setY(Helpers.MOBILE_HEIGHT);
        alive = true;
        // Set the origin in center, this way when we rotate, we rotate from the center
        setOrigin(getWidth() / 2, getHeight() / 2);
        angle = (float) randomNumber(-3, 3);
    }

    public void update(){
        move();
        rotate(angle);
        checkCollision();
    }

    public void render(SpriteBatch sb) {
        draw(sb);
    }

    private void move() {
        setY(getY() - velocity);
    }

    // Return double, this way it works with negative number
    private double randomNumber(int min, int max){
        Random rand = new Random();
        return min + (rand.nextDouble() * (max - min));
    }

    private void checkCollision() {
        // Kill itself if it get out of the screen
        if((getY() + getHeight()) <= 0){
            alive = false;
        }
    }

    public void dispose(){
        getTexture().dispose();
    }

    public boolean isAlive() {
        return alive;
    }
}
