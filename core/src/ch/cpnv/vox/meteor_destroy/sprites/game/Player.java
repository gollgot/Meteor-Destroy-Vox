package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.Model.Word;
import ch.cpnv.vox.meteor_destroy.VocabularyManager;
import ch.cpnv.vox.meteor_destroy.states.GameState;

/**
 * This class define a Player Object used to shoot laser and move to avoid meteor and display the word to find
 */

public class Player extends Sprite {

    private String mDirection = "stop";
    private float m_vPlayer = Helpers.getWidthAdaptToResolution(10); // Velocity
    public  static int life;

    private ArrayList<Laser> mAllLasers; //(missiles)
    private String mLaserType;

    private Word mWordToFind;

    private BitmapFont mFont;
    private GlyphLayout mGlyphLayout;

    /**
     * Constructor with initialization
     */
    public Player(){
        super(new Texture("game/player.png"));
        init();
    }

    /**
     * Initialization method, set life, size, position, laser, word to find
     */
    private void init() {
        life = 3;
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2) - (m_vPlayer *= 0.7)); // Middle - Little deplacement arrived when we setX(vitesse) in move method
        setY(Helpers.getHeightAdaptToResolution(350));
        // init array of redLaser
        mLaserType = "redLaser";
        mAllLasers = new ArrayList<Laser>();
        // Get a word to find
        mWordToFind = VocabularyManager.getWordToFind();
        // init the font
        initFont();
    }

    /**
     * Font initialization
     */
    private void initFont() {
        // get the font which preloaded
        mFont = Helpers.openSans_100;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        mGlyphLayout = new GlyphLayout();
        mGlyphLayout.setText(mFont,mWordToFind.getValue1());
    }

    /**
     * Update, here we do logical things. Called from GameState.update method
     */
    public void update(){
        checkCollision();
        move();
        // If we shoot on, one or more redLaser, we updated them
        if(hasShot()){
            removeLaserIfNotAlive();
            for(Laser laser : mAllLasers){
                laser.update();
            }
        }
    }

    /**
     * Draw the sprite, called from GameState.render method
     * @param sb The spriteBatch require to display element on screen
     */
    public void render(SpriteBatch sb) {
        draw(sb);
        // If we shoot on, one or more redLaser, we updated them
        if(hasShot()){
            for(Laser laser : mAllLasers){
                laser.render(sb);
            }
        }
        // Draw the word to search
        mFont.draw(sb, mGlyphLayout, (Helpers.MOBILE_WIDTH / 2) - (mGlyphLayout.width / 2), getY() - Helpers.getHeightAdaptToResolution(20));
    }

    /**
     * Check collisions. With screen side, and with meteor. (If we touch meteor we loose a life)
     */
    private void checkCollision() {
        // Left screen side
        if(getX() <= 0){
            // Stop the player
            setX(0);
            m_vPlayer = 0;
        }

        // Right screen side
        if(getX() + getWidth() >= Helpers.MOBILE_WIDTH){
            // Stop the player
            setX(Helpers.MOBILE_WIDTH - getWidth());
            m_vPlayer = 0;
        }

        // Collision with meteor
        for(int i = 0; i < GameState.meteors.size(); i++){
            // If the meteor touch the left, middle or right side of our player
            if(GameState.meteors.get(i).getBounds().contains(getX(), getY()) ||
                GameState.meteors.get(i).getBounds().contains(getX() + getWidth()/2, getY())||
                GameState.meteors.get(i).getBounds().contains(getX() + getWidth(), getY())
            ){
                // Play sound of explosion
                GameState.playlifeDownSound();
                // Destroy the meteor and remove a life
                GameState.meteors.get(i).dispose();
                GameState.meteors.remove(i);
                life--;
                Hud.score -= 25;
            }
        }
    }

    /**
     * Updated the X position of the player (called from the GameState when we touch controller button)
     */
    private void move(){
        /* All the calculs under, is to have a smoothly move */
        // We add or remove 0.8 to the velocity, this way, the velocity is more and more faster
        switch (mDirection){
            case "left":
                m_vPlayer -= Helpers.getWidthAdaptToResolution((float) 0.8);
                break;
            case "right":
                m_vPlayer += Helpers.getWidthAdaptToResolution((float) 0.8);
                break;
            default:
                // If we don't move, we multiply velocity by < 1, this way, we will stop smoothly
                m_vPlayer *= 0.7;
                break;
        }
        // Set the player position
        setX(getX() + m_vPlayer);
    }

    /**
     * Shoot, create a new Laser object and add it on our array of laser
     */
    public void shoot() {
        mAllLasers.add(new Laser(this, mLaserType));
    }

    /**
     * Check if we has shot
     * @return true if we has shot, false otherwise
     */
    private boolean hasShot(){
        if(mAllLasers.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Very important ! If the laser is dead, we remove it from the list, and we dispose (prevent memory leaks, cpu etc..)
     */

    private void removeLaserIfNotAlive() {
        for(int i = 0; i < mAllLasers.size(); i++){
            if(!mAllLasers.get(i).isAlive()){
                mAllLasers.get(i).dispose();
                mAllLasers.remove(i);
            }
        }
    }

    /**
     * Set the direction (just stop or nothing)
     * @param direction the direction
     */
    public void setDirection(String direction) {
        mDirection = direction;
    }

    /**
     * Set the new value of the word to find in the font text
     * @param wordToFind The new Word object to find
     */
    public void setWordToFind(Word wordToFind) {
        mWordToFind = wordToFind;
        mGlyphLayout.setText(mFont, wordToFind.getValue1());
    }

    /**
     * Set the laser type (redLaser or greenLaser)
     * @param laserType The new type of laser
     */
    public void setLaserType(String laserType) {
        this.mLaserType = laserType;
    }

    /**
     * Get the type of laser selected (redLaser or greenLaser)
     * @return the type of laser selected
     */
    public String getLaserType() {
        return mLaserType;
    }

    /**
     * Get the Word object to find
     * @return the Word object to find
     */
    public Word getWordToFind() {
        return mWordToFind;
    }

    /**
     * Dispose, to prevent memory leaks, called from GameState.dispose method
     */
    public void dispose(){
        this.getTexture().dispose();
        for(Laser laser: mAllLasers){
            laser.dispose();
        }
    }
}
