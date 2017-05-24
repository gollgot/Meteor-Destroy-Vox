package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;

/**
 * Created by Loïc on 24.05.2017.
 */

public class RedLaser extends Sprite{

    private Player player;
    private float velocity = Helpers.getHeightAdaptToResolution(8);
    private boolean alive;

    public RedLaser(Player player){
        super(new Texture("game/redLaser.png"));
        this.player = player;
        init();
    }

    private void init() {
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX(player.getX() + (player.getWidth() / 2) - (getWidth() / 2));
        setY(player.getY() + player.getHeight() + Helpers.getHeightAdaptToResolution(30));
        alive = true;
    }

    public void update() {
        move();
        checkCollision();
    }

    public void render(SpriteBatch sb) {
        draw(sb);
    }

    private void move() {
        setY(getY() + velocity);
    }

    private void checkCollision() {
        // Go outside of the screen, without touch any meteor etc..
        if(getY() >= Helpers.MOBILE_HEIGHT){
            alive = false;
        }
    }

    public boolean isAlive(){
        return alive;
    }

    public void dispose() {
        getTexture().dispose();
    }
}
