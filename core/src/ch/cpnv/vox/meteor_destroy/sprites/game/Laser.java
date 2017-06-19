package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.VocabularyManager;
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
                        // We have to shoot the good meteor word, I check that
                        if(player.getWordToFind().getValue2() == GameState.meteors.get(i).getTranslateWord()){
                            // Play sound of explosion
                            GameState.playExplosionSound();

                            Hud.score += 50;
                            // We shot the good word, we generate a new one
                            player.setWordToFind(VocabularyManager.getWordToFind());

                            // Up the meteor speed
                            GameState.meteorSpeedY += Helpers.getHeightAdaptToResolution(1);
                        }else {
                            // Play sound of life down
                            GameState.playlifeDownSound();
                            player.life--;
                            Hud.score -= 25;
                        }
                        // Destroy the meteor
                        GameState.meteors.get(i).dispose();
                        GameState.meteors.remove(i);
                        break;
                    case "greenLaser":
                        // Play sound of Deviation
                        GameState.playDeviateSound();

                        // Case of the green laser touch the Right part of the meteor
                        if(getX()+(getWidth()/2) >= GameState.meteors.get(i).getX()+(GameState.meteors.get(i).getWidth()/2)){
                            // Deviate on left side
                            GameState.meteors.get(i).setVelocityX(Helpers.getWidthAdaptToResolution(-4));
                        }
                        // Else, left part
                        else{
                            // Deviate on right side
                            GameState.meteors.get(i).setVelocityX(Helpers.getWidthAdaptToResolution(4));
                        }
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
