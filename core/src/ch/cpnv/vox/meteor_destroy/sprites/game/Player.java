package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loic.DESSAULES on 24.05.2017.
 */

public class Player extends Sprite {

    private String direction = "stop";
    private float vPlayer = 10;
    private final float V_MAX = 7;

    public Player(){
        super(new Texture("game/player.png"));
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2));
        setY(Helpers.getHeightAdaptToResolution(300));
    }

    public void update(){
        move();
    }

    private void move(){
        switch (direction){
            case "left":
                vPlayer -= .8;
                break;
            case "right":
                vPlayer += .8;
                break;
            default:
                vPlayer *= 0.7;
                break;
        }
        setX(getX() + vPlayer);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
