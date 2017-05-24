package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.sprites.Background;
import ch.cpnv.vox.meteor_destroy.sprites.game.Controller;
import ch.cpnv.vox.meteor_destroy.sprites.game.Player;
import ch.cpnv.vox.meteor_destroy.sprites.game.RedLaser;

/**
 * Created by Loic.DESSAULES on 22.05.2017.
 */

public class GameState extends State implements InputProcessor{

    private Background background;
    private Player player;
    private Controller controller;

    public GameState(GameStateManager gsm) {
        super(gsm);
        // Mandatory to use the InputProcessor
        Gdx.input.setInputProcessor(this);
        // init Sprites
        background = new Background();
        player = new Player();
        controller =  new Controller();
    }

    @Override
    public void update(float dt) {
        player.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.draw(sb);
        controller.render(sb);
        player.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        controller.dispose();
        background.getTexture().dispose();
        player.getTexture().dispose();
    }

    /*-------------------------------------------------------------------*/

    @Override
    public boolean keyDown(int keycode) {
        // Touch the physical back key of the phone
        if(keycode == Input.Keys.BACK){
            gsm.pop();
            gsm.push(new MenuState(gsm));
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

        // Left controller detection
        if(controller.getLeftBounds().contains(screenX, screenY)){
            player.setDirection("left");
            System.out.println("LEFT");
        }
        // Right controller detection
        if(controller.getRightBounds().contains(screenX, screenY)){
            player.setDirection("right");
            System.out.println("Right");
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        player.setDirection("stop");
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
