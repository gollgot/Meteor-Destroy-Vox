package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.vox.meteor_destroy.sprites.Background;
import ch.cpnv.vox.meteor_destroy.sprites.menu.PlayButton;
import ch.cpnv.vox.meteor_destroy.sprites.menu.Title;

/**
 * Created by Loïc on 19.05.2017.
 */

public class MenuState extends State {

    private Sprite background;
    private Sprite title;
    private Sprite btnPlay;

    private Rectangle btnPlayBounds; //(Bounds = limits)
    private Vector3 touch;


    public MenuState(GameStateManager gsm) {
        super(gsm);

        background = new Background();
        title = new Title();
        btnPlay = new PlayButton();
        // Create a rectangle exactly same x/y as the button
        btnPlayBounds = new Rectangle(btnPlay.getX(), btnPlay.getY(), btnPlay.getWidth(), btnPlay.getHeight());
    }

    @Override
    public void handleInput() {
        // Touch listener -> While button is touched
        if(Gdx.input.justTouched()){
            touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Detect if we touched the button
            if(btnPlayBounds.contains(touch.x, touch.y)){
                System.out.println("TOUCH !");
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.draw(sb);
        title.draw(sb);
        btnPlay.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
        title.getTexture().dispose();
        btnPlay.getTexture().dispose();
    }
}
