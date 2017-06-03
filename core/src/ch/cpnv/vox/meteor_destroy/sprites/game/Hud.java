package ch.cpnv.vox.meteor_destroy.sprites.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    public  static int score;

    private ArrayList<Life> lives;
    private Life life1;
    private Life life2;
    private Life life3;

    private float marginX = Helpers.getWidthAdaptToResolution(40);
    private float marginY = Helpers.getHeightAdaptToResolution(40);

    private BitmapFont font;
    private GlyphLayout glyphLayout;

    public Hud (){
        init();
    }

    private void init(){
        score = 0;
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
        initFont();
    }

    private void initFont() {
        // get the font which preloaded
        font = Helpers.openSans_70;
        // I used glyphLayout, because with this, we can use the .width attributs, this way it's simple to center the text where we want
        glyphLayout = new GlyphLayout();
    }

    public void update(){
        nbOfLife = Player.life;
        glyphLayout.setText(font, String.valueOf(score));
    }

    public void render(SpriteBatch sb) {
        // Display only lives we have
        for(int i=0; i < nbOfLife; i++){
           lives.get(i).draw(sb);
        }
        // Display the score
        font.draw(sb, glyphLayout, Helpers.MOBILE_WIDTH - glyphLayout.width - marginX, Helpers.MOBILE_HEIGHT - glyphLayout.height);
    }

    public void dispose(){
        life1.getTexture().dispose();
        life2.getTexture().dispose();
        life3.getTexture().dispose();
    }

}
