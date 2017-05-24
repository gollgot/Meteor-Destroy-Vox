package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.vox.meteor_destroy.Helpers;
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

    private Rectangle btnPlayBounds; //(Bounds = limits)


    public MenuState(GameStateManager gsm) {
        super(gsm);
        // Mandatory to use the InputProcessor
        Gdx.input.setInputProcessor(this);

        // init Sprite
        background = new Background();
        title = new Title();
        btnPlay = new PlayButton(gsm);
        initAudio();

        // Create a rectangle exactly same x/y as the button (For touch detection)
        btnPlayBounds = new Rectangle(btnPlay.getX(), btnPlay.getY(), btnPlay.getWidth(), btnPlay.getHeight());
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
        btnPlay.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
        title.getTexture().dispose();
        btnPlay.getTexture().dispose();
        audio.dispose();
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
        if(btnPlayBounds.contains(screenX, screenY)){
            // Delete the first state and add new one
            audio.stop();
            gsm.pop();
            gsm.push(new GameState(gsm));
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
