package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Model.Vocabulary;
import ch.cpnv.vox.meteor_destroy.states.GameState;
import ch.cpnv.vox.meteor_destroy.states.GameStateManager;
import ch.cpnv.vox.meteor_destroy.states.MenuState;

public class MeteorDestroy extends ApplicationAdapter{

    private GameStateManager gsm;
    private SpriteBatch batch;
	private HttpManager httpManager;
    public static Vocabulary mVocabulary;
    private String vocError = null;
	
	@Override
	public void create () {
        // Get the vocs and preload componements we needs before (like fonts)
        getVocs();
        Helpers.loadPlayerWordToSearchFont();
		// Mandatory, we say that we will use the physical backkey of the phone
		Gdx.input.setCatchBackKey(true);

		batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);

        // Added the first state on the states stack : Menu state
        gsm.push(new MenuState(gsm, vocError));
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


    private void getVocs() {
        // get the vocs
        httpManager = new HttpManager();
        httpManager.getVocs();
        // Freeze the app while we get the vocs
        while(!httpManager.isFinish()){
        }
        // error => Get the error Message
        // no error => Get the Vocabulary
        if(httpManager.hasError()){
            vocError = httpManager.getErrorMessage();
            System.out.println(vocError);
        }else{
            mVocabulary = httpManager.getVocabulary();
        }
    }
}
