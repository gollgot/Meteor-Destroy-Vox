package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.sprites.hud.Life;

/**
 * Created by Loic.DESSAULES on 31.05.2017.
 */

public class Hud {

    private int nbOfLife;

    private ArrayList<Life> lives;
    private Life life1;
    private Life life2;
    private Life life3;

    private float marginX = Helpers.getWidthAdaptToResolution(40);
    private float marginY = Helpers.getHeightAdaptToResolution(40);

    public Hud (){
        init();
    }

    private void init(){
        // Init lives
        lives = new ArrayList();
        this.nbOfLife = Player.life;
        life1 = new Life();
        life2 = new Life();
        life3 = new Life();

        // Set the positions
        life1.setPosition(marginX, Helpers.MOBILE_HEIGHT - life1.getHeight() - marginY);
        life2.setPosition(life1.getX() + life1.getWidth() + marginX, Helpers.MOBILE_HEIGHT - life2.getHeight() - marginY);
        life3.setPosition(life2.getX() + life2.getWidth() + marginX, Helpers.MOBILE_HEIGHT - life3.getHeight() - marginY);

        // Fill the lives arrayList
        lives.add(life1);
        lives.add(life2);
        lives.add(life3);
    }

    public void update(){
        nbOfLife = Player.life;
    }

    public void render(SpriteBatch sb) {
        // Display only lives we have
        for(int i=0; i < nbOfLife; i++){
           lives.get(i).draw(sb);
        }
    }

    public void dispose(){
        life1.getTexture().dispose();
        life2.getTexture().dispose();
        life3.getTexture().dispose();
    }

}
