package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.Model.Word;
import ch.cpnv.vox.meteor_destroy.VocabularyManager;
import ch.cpnv.vox.meteor_destroy.states.GameState;

/**
 * Created by Loic.DESSAULES on 24.05.2017.
 */

public class Player extends Sprite {

    private String direction = "stop";
    private float vPlayer = Helpers.getWidthAdaptToResolution(10); // Velocity
    public  static int life;

    private ArrayList<Laser> allLasers; //(missiles)
    private String laserType;

    private Word wordToFind;

    private BitmapFont font;
    private GlyphLayout glyphLayout;

    public Player(){
        super(new Texture("game/player.png"));
        init();
    }

    private void init() {
        life = 3;
        // Modify the image size in proportion of the mobile resolution
        setSize(Helpers.getWidthAdaptToResolution(getWidth()), Helpers.getHeightAdaptToResolution(getHeight()));
        // Set the position (fixed)
        setX((Helpers.MOBILE_WIDTH / 2) - (getWidth() / 2) - (vPlayer *= 0.7)); // Middle - Little deplacement arrived when we setX(vitesse) in move method
        setY(Helpers.getHeightAdaptToResolution(350));
        // init array of redLaser
        laserType = "redLaser";
        allLasers = new ArrayList<Laser>();
        // Get a word to find
        wordToFind = VocabularyManager.getWordToFind();
        // init the font
        initFont();
    }

    private void initFont() {
        // get the font which preloaded
        font = Helpers.fontPlayerWordToSearch;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,wordToFind.getValue1());
    }

    public void update(){
        checkCollision();
        move();
        // If we shoot on, one or more redLaser, we updated them
        if(hasShot()){
            removeLaserIfNotAlive();
            for(Laser laser : allLasers){
                laser.update();
            }
        }
    }

    public void render(SpriteBatch sb) {
        draw(sb);
        // If we shoot on, one or more redLaser, we updated them
        if(hasShot()){
            for(Laser laser : allLasers){
                laser.render(sb);
            }
        }
        // Draw the word to search
        font.draw(sb, glyphLayout, (Helpers.MOBILE_WIDTH / 2) - (glyphLayout.width / 2), getY() - Helpers.getHeightAdaptToResolution(20));
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

        // Collision with meteor
        for(int i = 0; i < GameState.meteors.size(); i++){
            // If the meteor touch the left, middle or right side of our player
            if(GameState.meteors.get(i).getBounds().contains(getX(), getY()) ||
                GameState.meteors.get(i).getBounds().contains(getX() + getWidth()/2, getY())||
                GameState.meteors.get(i).getBounds().contains(getX() + getWidth(), getY())
            ){
                // Destroy the meteor and remove a life
                GameState.meteors.get(i).dispose();
                GameState.meteors.remove(i);
                life--;
            }
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

    private boolean hasShot(){
        if(allLasers.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void shoot() {
        allLasers.add(new Laser(this, laserType));
    }

    // Very important ! Id the laser is dead, we remove it from the list, and we dispose (prevent memory leaks, cpu etc..)
    private void removeLaserIfNotAlive() {
        for(int i = 0; i < allLasers.size(); i++){
            if(!allLasers.get(i).isAlive()){
                allLasers.get(i).dispose();
                allLasers.remove(i);
            }
        }
    }

    public String getLaserType() {
        return laserType;
    }

    public void setLaserType(String laserType) {
        this.laserType = laserType;
    }
}
