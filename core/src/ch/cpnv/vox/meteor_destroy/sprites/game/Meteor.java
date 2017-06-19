package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.VocabularyManager;
import ch.cpnv.vox.meteor_destroy.states.GameState;

/**
 * Created by Loïc on 25.05.2017.
 */

public class Meteor extends Sprite {

    private float velocityX = 0;
    private boolean alive;
    private float angle;
    private Rectangle bounds;

    private String translateWord;
    private Vector2 translateWordPosition;

    private BitmapFont font;
    private GlyphLayout glyphLayout;

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
        // Set the bounds of the meteor for collision detection
        bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
        // Get a random translateWord
        translateWord = VocabularyManager.getRandomTranslateWord();
        initFont();
        translateWordPosition = new Vector2((getX() + getWidth() / 2) - (glyphLayout.width / 2), getY() + getHeight() + Helpers.getHeightAdaptToResolution(80));
    }

    private void initFont() {
        // get the font which preloaded
        font = Helpers.openSans_70;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, translateWord);
    }

    public void update(){
        move();
        rotate(angle);
        updateBounds();
        checkCollision();
    }

    public void render(SpriteBatch sb) {
        draw(sb);
        // Draw the translate word above the meteor
        font.draw(sb, glyphLayout, translateWordPosition.x, translateWordPosition.y);
    }

    private void move() {
        setY(getY() - GameState.meteorSpeedY);
        setX(getX() + velocityX);
        translateWordPosition.set((getX() + getWidth() / 2) - (glyphLayout.width / 2), getY() + getHeight() + Helpers.getHeightAdaptToResolution(80));
    }

    // Return double, this way it works with negative number
    private double randomNumber(int min, int max){
        Random rand = new Random();
        return min + (rand.nextDouble() * (max - min));
    }

    private void updateBounds() {
        bounds = bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    private void checkCollision() {
        // Kill itself if it get out of the screen
        if((getY() + getHeight()) <= 0){
            alive = false;
        }
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose(){
        getTexture().dispose();
    }

    public boolean isAlive() {
        return alive;
    }

    public String getTranslateWord() {
        return translateWord;
    }
}
