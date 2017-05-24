package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loic.DESSAULES on 24.05.2017.
 */

public class Player extends Sprite {

    private String direction = "stop";
    private float vPlayer = Helpers.getWidthAdaptToResolution(10); // Velocity

    public Player(){
        super(new Texture("game/player.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2) - (vPlayer *= 0.7)); // Middle - Little deplacement arrived when we setX(vitesse) in move method
        setY(Helpers.getHeightAdaptToResolution(300));
    }

    public void update(){
        checkCollision();
        move();
    }

    private void checkCollision() {
        // Left screen side
        if(getX() <= 0){
            // Stop the player
            setX(0);
            vPlayer = 0;
        }

        // Right screen side
        if(getX() + getWidth() >= Helpers.MOBILE_WIDTH){
            // Stop the player
            setX(Helpers.MOBILE_WIDTH - getWidth());
            vPlayer = 0;
        }
    }

    private void move(){
        // We add or remove 0.8 to the velocity, this way, the velocity is more and more faster
        switch (direction){
            case "left":
                vPlayer -= Helpers.getWidthAdaptToResolution((float) 0.8);
                break;
            case "right":
                vPlayer += Helpers.getWidthAdaptToResolution((float) 0.8);
                break;
            default:
                // If we don't move, we multiply velocity by < 1, this way, we will stop smoothly
                vPlayer *= 0.7;
                break;
        }
        // Set the player position
        setX(getX() + vPlayer);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
