package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.states.GameState;

/**
 * Created by Loïc on 24.05.2017.
 */

public class Laser extends Sprite{

    private Player player;
    private float velocity = Helpers.getHeightAdaptToResolution(8);
    private boolean alive;
    private String type;

    public Laser(Player player, String type){
        super(new Texture("game/redLaser.png"));
        this.type = type;
        this.player = player;
        init();
    }

    private void init() {
        if(type == "redLaser"){
            setTexture(new Texture("game/redLaser.png"));
        }else if(type == "greenLaser"){
            setTexture(new Texture("game/greenLaser.png"));
        }

        //setTexture(new Texture("game/redLaser.png"));

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

        // Collision with meteor
        for(int i=0; i < GameState.meteors.size(); i++){
            if(GameState.meteors.get(i).getBounds().contains(getX(), getY())){
                // Laser is dead
                alive = false;
                // type redLaser : destroy the meteor
                // type greenLaser : Deviate the meteor
                switch(type){
                    case "redLaser":
                        GameState.meteors.get(i).dispose();
                        GameState.meteors.remove(i);
                        break;
                    case "greenLaser":
                        break;
                }
            }
        }
    }

    public boolean isAlive(){
        return alive;
    }

    public void dispose() {
        getTexture().dispose();
    }
}