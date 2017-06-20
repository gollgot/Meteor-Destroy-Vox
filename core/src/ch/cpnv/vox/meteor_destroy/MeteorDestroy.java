package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.vox.meteor_destroy.Model.Vocabulary;
import ch.cpnv.vox.meteor_destroy.states.GameStateManager;
import ch.cpnv.vox.meteor_destroy.states.MenuState;

/**
 * Main class of the project first entry point
 */
public class MeteorDestroy extends ApplicationAdapter{

    private GameStateManager mGsm;
    private SpriteBatch mBatch;
	private HttpManager mHttpManager;
    public static Vocabulary vocabulary;
    private String mVocError = null;

    /**
     * First method called from libgdx, we load some elements (vocs, fonts) and load the first state
     */
	@Override
	public void create () {
        // Get the vocs and preload componements we needs before (like fonts)
        getVocs();
        Helpers.loadFonts();
		// Mandatory, we say that we will use the physical backkey of the phone
		Gdx.input.setCatchBackKey(true);

		mBatch = new SpriteBatch();
        mGsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);

        // Added the first state on the states stack : Menu state
        mGsm.push(new MenuState(mGsm, mVocError));
	}

    /**
     * Display on the screen, called from ApplicationAdapter (LibGdx class)
     */
	@Override
	public void render () {
        // Refresh the board
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // The GameStateManager will update and render the first State on the States Stack
        mGsm.update(Gdx.graphics.getDeltaTime()); // The deltaTime is the difference between the render of two frame (prevent lag etc..)
        mGsm.render(mBatch);
	}

    /**
     * Dispose all elements to prevent memory leaks
     */
	@Override
	public void dispose () {
		mBatch.dispose();
	}

    /**
     * Get the vocabulary from our HttpManager class
     */
    private void getVocs() {
        // get the vocs
        mHttpManager = new HttpManager();
        mHttpManager.getVocs();
        // Freeze the app while we get the vocs
        while(!mHttpManager.isFinish()){
        }
        // error => Get the error Message
        // no error => Get the Vocabulary
        if(mHttpManager.hasError()){
            mVocError = mHttpManager.getErrorMessage();
            System.out.println(mVocError);
        }else{
            vocabulary = mHttpManager.getVocabulary();
        }
    }
}
