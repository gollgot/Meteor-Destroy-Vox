package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is the mother class of all our State. They extends of this one and this way we can override
 * the method update / render. Each state is in a stack of the GameStateManager and we will call the update and render method
 * of the first state in the stack.
 */

public abstract class State {

    protected GameStateManager gsm;

    /**
     * Constructor
     * @param gsm The current GameStateManager
     */
    public State(GameStateManager gsm){
        this.gsm = gsm;
    }

    /**
     * Update the first State on the stack
     * @param dt Delta time between each frame
     */
    public abstract void update(float dt);

    /**
     * Display the first State on the stack
     * @param sb The spriteBatch require to display element on screen
     */
    public abstract void render(SpriteBatch sb);

    /**
     * Dispose all elements (graphical, sound, etc...) To prevent memory leaks
     */
    public abstract void dispose();

}
