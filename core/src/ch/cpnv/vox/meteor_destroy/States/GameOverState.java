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
 * The GameOverState is the state displayed when we loose all our life in the game.
 */

public class GameOverState extends State implements InputProcessor{

    private Background mBackground;
    private Sprite mBtnRetry, mBtnQuit;
    private Rectangle mBtnRetryBounds, mBtnQuitBounds;
    private Music mAudio;

    private int mScore;

    private BitmapFont mFontTitle, mFontText;
    private GlyphLayout mGlyphLayoutTitle, mGlyphLayoutText;

    /**
     * Constructor, with some inits
     * @param gsm The current Game State Manager
     * @param score The score did on the GameState
     */
    public GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        this.mScore = score;
        // Notify that we use InputProcessor on this class, mandatory to works
        Gdx.input.setInputProcessor(this);
        initFont();
        initSprites();
    }

    /**
     * Initialize the fonts
     */
    private void initFont() {
        // get the font which preloaded
        mFontTitle = Helpers.kenVector_150;
        mFontText = Helpers.openSans_100;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        mGlyphLayoutTitle = new GlyphLayout();
        mGlyphLayoutText = new GlyphLayout();
        mGlyphLayoutTitle.setText(mFontTitle, "Game Over");
        mGlyphLayoutText.setText(mFontText, "Score : "+String.valueOf(mScore));
    }

    /**
     * Initialize the sprites
     */
    private void initSprites(){
        // Initialisation
        mBackground = new Background();
        mBtnRetry = new Sprite(new Texture("game_over/retry_button.png"));
        mBtnQuit = new Sprite(new Texture("game_over/quit_button.png"));
        initAudio();
        // Sizes
        mBtnRetry.setSize(Helpers.getWidthAdaptToResolution(mBtnRetry.getWidth()), Helpers.getHeightAdaptToResolution(mBtnRetry.getHeight()));
        mBtnQuit.setSize(Helpers.getWidthAdaptToResolution(mBtnQuit.getWidth()), Helpers.getHeightAdaptToResolution(mBtnQuit.getHeight()));
        // Positions
        mBtnRetry.setPosition((Helpers.MOBILE_WIDTH / 2) - mBtnRetry.getWidth() / 2, (Helpers.MOBILE_HEIGHT / 2) - mBtnRetry.getHeight() / 2);
        mBtnQuit.setPosition(mBtnRetry.getX(), mBtnRetry.getY() - mBtnQuit.getHeight() - Helpers.getHeightAdaptToResolution(40));
        // Bounds
        mBtnRetryBounds = new Rectangle(mBtnRetry.getX(), mBtnRetry.getY(), mBtnRetry.getWidth(), mBtnRetry.getHeight());
        mBtnQuitBounds = new Rectangle(mBtnQuit.getX(), mBtnQuit.getY(), mBtnQuit.getWidth(), mBtnQuit.getHeight());
    }

    /**
     * Initialize the audio
     */
    private void initAudio() {
        mAudio = Gdx.audio.newMusic(Gdx.files.internal("audio/game_over.ogg"));
        mAudio.play();
        mAudio.setLooping(false);
    }

    /**
     * Update method from State class
     * @param dt Delta time between each frame
     */
    @Override
    public void update(float dt) {
    }

    /**
     * Render method from State class
     * @param sb The spriteBatch require to display element on screen
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        mBackground.draw(sb);
        mBtnRetry.draw(sb);
        mBtnQuit.draw(sb);
        mFontTitle.draw(sb, mGlyphLayoutTitle, (Helpers.MOBILE_WIDTH / 2) - (mGlyphLayoutTitle.width / 2), Helpers.MOBILE_HEIGHT - mGlyphLayoutTitle.height - Helpers.getHeightAdaptToResolution(150));
        mFontText.draw(sb, mGlyphLayoutText, (Helpers.MOBILE_WIDTH / 2) - (mGlyphLayoutText.width / 2), mBtnRetry.getY() + mBtnRetry.getHeight() + Helpers.getHeightAdaptToResolution(250));
        sb.end();
    }

    /**
     * Dispose method from State class
     */
    @Override
    public void dispose() {
        mBackground.getTexture().dispose();
        mBtnRetry.getTexture().dispose();
        mBtnQuit.getTexture().dispose();
        mAudio.dispose();
    }


    /*-------------------------------------------------------------------*/

    /**
     * All methods under this one is for manage the inputs (Touch, mouse, scroll, etc...)
     */
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
        if(mBtnQuitBounds.contains(screenX, screenY)){
            Gdx.app.exit();
        }
        // Touch the quit button
        if(mBtnRetryBounds.contains(screenX, screenY)){
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
