package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.sprites.Background;

/**
 * Created by Loïc on 03.06.2017.
 */

public class GameOverState extends State implements InputProcessor{

    private Background background;
    private Sprite btnRetry, btnQuit;
    private Rectangle btnRetryBounds, btnQuitBounds;
    private Music audio;

    private int score;

    private BitmapFont fontTitle, fontText;
    private GlyphLayout glyphLayoutTitle, glyphLayoutText;

    public GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
        Gdx.input.setInputProcessor(this);
        initFont();
        initSprites();
    }

    private void initFont() {
        // get the font which preloaded
        fontTitle = Helpers.kenVector_150;
        fontText = Helpers.openSans_100;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        glyphLayoutTitle = new GlyphLayout();
        glyphLayoutText = new GlyphLayout();
        glyphLayoutTitle.setText(fontTitle, "Game Over");
        glyphLayoutText.setText(fontText, "Score : "+String.valueOf(score));
    }

    private void initSprites(){
        // Initialisation
        background = new Background();
        btnRetry = new Sprite(new Texture("game_over/retry_button.png"));
        btnQuit = new Sprite(new Texture("game_over/quit_button.png"));
        initAudio();
        // Sizes
        btnRetry.setSize(Helpers.getWidthAdaptToResolution(btnRetry.getWidth()), Helpers.getHeightAdaptToResolution(btnRetry.getHeight()));
        btnQuit.setSize(Helpers.getWidthAdaptToResolution(btnQuit.getWidth()), Helpers.getHeightAdaptToResolution(btnQuit.getHeight()));
        // Positions
        btnRetry.setPosition((Helpers.MOBILE_WIDTH / 2) - btnRetry.getWidth() / 2, (Helpers.MOBILE_HEIGHT / 2) - btnRetry.getHeight() / 2);
        btnQuit.setPosition(btnRetry.getX(), btnRetry.getY() - btnQuit.getHeight() - Helpers.getHeightAdaptToResolution(40));
        // Bounds
        btnRetryBounds = new Rectangle(btnRetry.getX(), btnRetry.getY(), btnRetry.getWidth(), btnRetry.getHeight());
        btnQuitBounds = new Rectangle(btnQuit.getX(), btnQuit.getY(), btnQuit.getWidth(), btnQuit.getHeight());
    }

    private void initAudio() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("game_over/game_over.ogg"));
        audio.play();
        audio.setLooping(true);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.draw(sb);
        btnRetry.draw(sb);
        btnQuit.draw(sb);
        fontTitle.draw(sb, glyphLayoutTitle, (Helpers.MOBILE_WIDTH / 2) - (glyphLayoutTitle.width / 2), Helpers.MOBILE_HEIGHT - glyphLayoutTitle.height - Helpers.getHeightAdaptToResolution(150));
        fontText.draw(sb, glyphLayoutText, (Helpers.MOBILE_WIDTH / 2) - (glyphLayoutText.width / 2), btnRetry.getY() + btnRetry.getHeight() + Helpers.getHeightAdaptToResolution(250));
        sb.end();
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
        btnRetry.getTexture().dispose();
        btnQuit.getTexture().dispose();
        audio.dispose();
    }


    /*-------------------------------------------------------------------*/


    @Override
    public boolean keyDown(int keycode) {
        // Touch the physical back key of the phone
        if(keycode == Input.Keys.BACK){
            // Set the new state in place of the old
            gsm.set(new MenuState(gsm, null));
            // dispose all assets elements, to prevent memory leaks
            this.dispose();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Because x-y from touch event provides top-left coord.
        screenY = Helpers.MOBILE_HEIGHT - screenY;

        // Touch the quit button
        if(btnQuitBounds.contains(screenX, screenY)){
            Gdx.app.exit();
        }
        // Touch the quit button
        if(btnRetryBounds.contains(screenX, screenY)){
            // Load the gameState
            gsm.set(new GameState(gsm));
            // dispose all assets elements, to prevent memory leaks
            this.dispose();
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
