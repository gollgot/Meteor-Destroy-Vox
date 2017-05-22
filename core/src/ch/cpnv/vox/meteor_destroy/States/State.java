package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Loïc on 19.05.2017.
 */

public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;



    public State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }


    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();

}
