package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.states.GameStateManager;
import ch.cpnv.vox.meteor_destroy.states.MenuState;

public class MeteorDestroy extends ApplicationAdapter {

    private GameStateManager gsm;
    private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        // Added the first state on the states stack : Menu state
        gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
        // Refresh the board
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // The GameStateManager will update and render the first State on the States Stack
        gsm.update(Gdx.graphics.getDeltaTime()); // The deltaTime is the difference between the render of two frame (prevent lag etc..)
        gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
