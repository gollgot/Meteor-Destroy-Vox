package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.sprites.Background;

/**
 * Created by Loïc on 03.06.2017.
 */

public class GameOverState extends State implements InputProcessor{

    private Background background;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setInputProcessor(this);
        background = new Background();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
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
