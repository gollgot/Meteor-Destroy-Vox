package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * This class will manage all our state system. We have a stack of state, we can add, pick a state on the stack.
 * And we update and display the first state of the stack. This way we can easily change game state like
 * MenuState, GameState, GameOverState, SettingsState etc.
 */

public class GameStateManager{

    private Stack<State> mStates;

    /**
     * Constructor, initialize a new stack of State
     */
    public GameStateManager() {
        // Create the states stack
        mStates = new Stack<State>();
    }

    /**
     * Add a State object on the stack
     * @param state The State object to add to the stack
     */
    public void push(State state){
        mStates.push(state);
    }

    /**
     * Remove the first state on the stack
     */
    public void pop(){
        mStates.pop();
    }

    /**
     * Remove the first state on the stack and add a new one
     * @param state The new State object to add to the stack
     */
    public void set(State state){
        mStates.pop();
        mStates.push(state);
    }

    /**
     * Update the first State on the stack
     * @param dt Delta time between each frame
     */
    public void update(float dt){
        mStates.peek().update(dt);
    }

    /**
     * Display the first State on the stack
     * @param sb The spriteBatch require to display element on screen
     */
    public void render(SpriteBatch sb){
        mStates.peek().render(sb);
    }

}
