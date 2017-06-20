package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.VocabularyManager;
import ch.cpnv.vox.meteor_destroy.states.GameState;

/**
 * This class define a Laser Object used to destroy or deviate meteors
 */

public class Laser extends Sprite{

    private Player mPlayer;
    private float mVelocity = Helpers.getHeightAdaptToResolution(8);
    private boolean mAlive;
    private String mType;

    /**
     * Constructor, we need player (cause we will use player public method) and the type of laser to shoot
     * @param player The current player objet
     * @param type The type of the laser we want to shoot
     */
    public Laser(Player player, String type){
        super(new Texture("game/redLaser.png"));
        mType = type;
        mPlayer = player;
        init();
    }

    /**
     * initialization method, set the texture, size, position, etc.
     */
    private void init() {
        switch(mType){
            case "redLaser":
                setTexture(new Texture("game/redLaser.png"));
                break;
            case "greenLaser":
                setTexture(new Texture("game/greenLaser.png"));
                break;
        }

        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX(mPlayer.getX() + (mPlayer.getWidth() / 2) - (getWidth() / 2));
        setY(mPlayer.getY() + mPlayer.getHeight() + Helpers.getHeightAdaptToResolution(30));
        mAlive = true;
    }

    /**
     * Update, here we do logical things. Called from Player.update method
     */
    public void update() {
        move();
        checkCollision();
    }

    /**
     * Draw the sprite, called from Player.render method
     * @param sb The spriteBatch require to display element on screen
     */
    public void render(SpriteBatch sb) {
        draw(sb);
    }

    /**
     * Move the laser sprite on the Y axe
     */
    private void move() {
        setY(getY() + mVelocity);
    }

    /**
     * Check all collisions that a laser can find. Screen collision (laser dead when get out of the screen) and
     * collision with meteor
     */
    private void checkCollision() {
        // Go outside of the screen, without touch any meteor etc..
        if(getY() >= Helpers.MOBILE_HEIGHT){
            mAlive = false;
        }

        // Collision with meteor
        for(int i=0; i < GameState.meteors.size(); i++){
            if(GameState.meteors.get(i).getBounds().contains(getX(), getY())){
                // Laser is dead
                mAlive = false;
                // type redLaser : destroy the meteor
                // type greenLaser : Deviate the meteor
                switch(mType){
                    case "redLaser":
                        // We have to shoot the good meteor word, I check that
                        if(mPlayer.getWordToFind().getValue2() == GameState.meteors.get(i).getTranslateWord()){
                            // Play sound of explosion
                            GameState.playExplosionSound();

                            Hud.score += 50;
                            // We shot the good word, we generate a new one
                            mPlayer.setWordToFind(VocabularyManager.getWordToFind());

                            // Up the meteor speed
                            GameState.meteorSpeedY += Helpers.getHeightAdaptToResolution(1);
                        }else {
                            // Play sound of life down
                            GameState.playlifeDownSound();
                            mPlayer.life--;
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

    /**
     * Check if the laser is alive
     * @return True if the laser is alive, false otherwise
     */
    public boolean isAlive(){
        return mAlive;
    }

    /**
     * Dispose, to prevent memory leaks, called from Player.dispose method
     */
    public void dispose() {
        getTexture().dispose();
    }
}
