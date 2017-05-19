package ch.cpnv.vox.meteor_destroy.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Loïc on 19.05.2017.
 */

public class GameStateManager{

    private Stack<State> states;



    public GameStateManager() {
        // Create the states stack
        states = new Stack<State>();
    }



    // Add state on the stack
    public void push(State state){
        states.push(state);
    }
    // Remove the first state on the stack
    public void pop(){
        states.pop();
    }
    // Remove the first state on the stack and add a new one
    public void set(State state){
        states.pop();
        states.push(state);
    }



    // update the first State on the stack
    public void update(float dt){
        states.peek().update(dt);
    }
    // Display the first State on the stack
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
