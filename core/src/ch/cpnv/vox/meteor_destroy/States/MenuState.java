package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * Created by Loïc on 19.05.2017.
 */

public class MenuState extends State {

    private int screenWidth;
    private int screenHeight;
    private Texture background;
    private Texture title;
    private Texture btnPlay;


    private Rectangle btnPlayBounds; //(Bounds = limits)
    private Vector3 touch;


    public MenuState(GameStateManager gsm) {
        super(gsm);

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        background = new Texture("background.png");
        title = new Texture("menu_title.png");
        btnPlay = new Texture("play_button.png");

        // Create a rectangle exactly same x/y as the button
        btnPlayBounds = new Rectangle((screenWidth / 2) - (btnPlay.getWidth() / 2), (screenHeight / 2) - (btnPlay.getHeight() / 2), btnPlay.getWidth(), btnPlay.getHeight());
    }

    @Override
    public void handleInput() {
        // Touch listener -> While button is touched
        if(Gdx.input.isTouched()){
            touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Detect if we touched the button
            if(btnPlayBounds.contains(touch.x, touch.y)){
                btnPlay = new Texture("play_button_pressed.png");
            }
        }
        // If screen not touched, set buttonUnpressed
        else{
            btnPlay = new Texture("play_button.png");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(background, 0, 0);
        sb.draw(title, (screenWidth / 2) - (title.getWidth() / 2), screenHeight - title.getHeight() - 200);
        sb.draw(btnPlay, (screenWidth / 2) - (btnPlay.getWidth() / 2), (screenHeight / 2) - (btnPlay.getHeight() / 2));

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        btnPlay.dispose();
    }
}
