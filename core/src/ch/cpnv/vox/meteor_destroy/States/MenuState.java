package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.MeteorDestroy;
import ch.cpnv.vox.meteor_destroy.sprites.Background;
import ch.cpnv.vox.meteor_destroy.sprites.menu.PlayButton;
import ch.cpnv.vox.meteor_destroy.sprites.menu.Title;

/**
 * Created by Loïc on 19.05.2017.
 */

public class MenuState extends State implements InputProcessor{

    private Sprite background;
    private Title title;
    private PlayButton btnPlay;
    private Music audio;
    private String vocError;
    private BitmapFont errorFont;

    public MenuState(GameStateManager gsm, String vocError) {
        super(gsm);
        this.vocError = vocError;
        // Mandatory to use the InputProcessor
        Gdx.input.setInputProcessor(this);
        System.out.println(vocError);

        // init Sprite
        background = new Background();
        if(vocError == null){
            btnPlay = new PlayButton();
        }else{
            initFont();
        }
        title = new Title();
        initAudio();
    }

    private void initFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "abcdefghijklmnopqrstuvwxyzàéèêëùABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        FreeTypeFontGenerator generator = null;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        parameter.size = (int) Helpers.getHeightAdaptToResolution(100);
        parameter.color = Color.WHITE;
        errorFont = generator.generateFont(parameter);
    }

    private void initAudio() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("menu/menu.ogg"));
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
        title.draw(sb);
        if(vocError == null) {
            btnPlay.draw(sb);
        }else{
            errorFont.draw(sb, vocError, Helpers.getWidthAdaptToResolution(40),Helpers.MOBILE_HEIGHT/2);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
        title.getTexture().dispose();
        if(vocError == null) {
            btnPlay.getTexture().dispose();
        }
        audio.dispose();
        errorFont.dispose();
    }

    /*-------------------------------------------------------------------*/


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
        if (vocError == null){
            // I check if the play button bounds (rectangle) contains our Touch X,Y
            if (btnPlay.getBounds().contains(screenX, screenY)) {
                // Delete the first state and add new one
                audio.stop();
                gsm.pop();
                gsm.push(new GameState(gsm));
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
