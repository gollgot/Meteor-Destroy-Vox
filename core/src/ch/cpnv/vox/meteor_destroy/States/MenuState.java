package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.sprites.Background;
import ch.cpnv.vox.meteor_destroy.sprites.menu.PlayButton;
import ch.cpnv.vox.meteor_destroy.sprites.menu.Title;

/**
 * this is the first State of the game, the menu with a play button
 */

public class MenuState extends State implements InputProcessor{

    private Background mBackground;
    private Title mTitle;
    private PlayButton mBtnPlay;
    private Music mAudio;
    private String mVocError;
    private BitmapFont mErrorFont;
    private GlyphLayout mGlyphLayout;

    /**
     * Constructor with initialization
     * @param gsm The current GameStateManager
     * @param vocError The error string from HttpManager class. null if no error
     */
    public MenuState(GameStateManager gsm, String vocError) {
        super(gsm);
        this.mVocError = vocError;
        // Mandatory to use the InputProcessor on this current state
        Gdx.input.setInputProcessor(this);

        // init Sprite
        mBackground = new Background();
        // If we have an error with the HttpManager, we display the error and no the button play
        if(vocError == null){
            mBtnPlay = new PlayButton();
        }else{
            initFont();
        }
        mTitle = new Title();
        initAudio();
    }

    /**
     * Font initialization
     */
    private void initFont() {
        // get the font which preloaded
        mErrorFont = Helpers.openSans_100;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        mGlyphLayout = new GlyphLayout();
        mGlyphLayout.setText(mErrorFont, mVocError);
    }

    /**
     * Audio initialization
     */
    private void initAudio() {
        mAudio = Gdx.audio.newMusic(Gdx.files.internal("audio/menu.ogg"));
        mAudio.play();
        mAudio.setLooping(true);
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
        mTitle.draw(sb);
        if(mVocError == null) {
            mBtnPlay.draw(sb);
        }else{
            mErrorFont.draw(sb, mVocError, Helpers.getWidthAdaptToResolution(40),Helpers.MOBILE_HEIGHT/2);
        }
        sb.end();
    }

    /**
     * Dispose method from State class
     */
    @Override
    public void dispose() {
        mBackground.getTexture().dispose();
        mTitle.getTexture().dispose();
        if(mVocError == null) {
            mBtnPlay.getTexture().dispose();
        }
        mAudio.dispose();
    }

    /*-------------------------------------------------------------------*/

    /**
     * All methods under this one is for manage the inputs (Touch, mouse, scroll, etc...)
     */
    @Override
    public boolean keyDown(int keycode) {
        // Touch the physical back key on the phone
        if(keycode == Input.Keys.BACK){
            Gdx.app.exit();
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
        if (mVocError == null){
            // I check if the play button bounds (rectangle) contains our Touch X,Y
            if (mBtnPlay.getBounds().contains(screenX, screenY)) {
                // Delete the first state and add new one
                mAudio.stop();
                gsm.set(new GameState(gsm));
                // dispose all assets elements, to prevent memory leaks
                this.dispose();
            }
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
