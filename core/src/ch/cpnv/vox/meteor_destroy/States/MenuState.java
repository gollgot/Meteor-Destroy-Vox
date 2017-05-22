package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
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

public class MenuState extends State {

    private Background background;
    private Title title;
    private PlayButton btnPlay;

    private Music audio;


    public MenuState(GameStateManager gsm) {
        super(gsm);

        background = new Background();
        title = new Title();
        btnPlay = new PlayButton();

        initAudio();
    }

    private void initAudio() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("menu/menu.ogg"));
        audio.play();
    }

    @Override
    public void handleInput() {
        btnPlay.handleInput();
    }

    @Override
    public void update(float dt) {
        handleInput();
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
}
