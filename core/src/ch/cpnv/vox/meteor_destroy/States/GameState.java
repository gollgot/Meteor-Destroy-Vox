package ch.cpnv.vox.meteor_destroy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ch.cpnv.vox.meteor_destroy.Helpers;
import ch.cpnv.vox.meteor_destroy.sprites.Background;
import ch.cpnv.vox.meteor_destroy.sprites.game.Controller;
import ch.cpnv.vox.meteor_destroy.sprites.game.Hud;
import ch.cpnv.vox.meteor_destroy.sprites.game.Meteor;
import ch.cpnv.vox.meteor_destroy.sprites.game.Player;

/**
 * The Game State is the principal state, we play the game on this one.
 */

public class GameState extends State implements InputProcessor{

    private Background mBackground;
    private Player mPlayer;
    private Controller mController;
    private Music mAudio;
    private Hud mHud;
    private Sound mGreenLaserSound, mRedLaserSound;
    private static Sound mExplosionSound, mLifeDownSound, mDeviateSound;
    private long mStart_time;

    public static float meteorSpeedY;
    public static ArrayList<Meteor> meteors;

    /**
     * Constructor with initialization
     * @param gsm The current Game State Manager
     */
    public GameState(GameStateManager gsm) {
        super(gsm);
        // Mandatory to use the InputProcessor on this current state
        Gdx.input.setInputProcessor(this);
        // init Sprites
        mBackground = new Background();
        mPlayer = new Player();
        mController =  new Controller();
        mHud = new Hud();
        // Audio / sound effects
        initAudio();
        // Built meteors
        mStart_time =  System.currentTimeMillis(); // Calcul the time passed, to have a new meteor each 2seconds
        meteorSpeedY = Helpers.getHeightAdaptToResolution(8);
        meteors = new ArrayList<>();
        buildMeteor();
    }

    /**
     * Audio initialization
     */
    private void initAudio() {
        mGreenLaserSound = Gdx.audio.newSound(Gdx.files.internal("audio/green_laser.ogg"));
        mRedLaserSound = Gdx.audio.newSound(Gdx.files.internal("audio/red_laser.ogg"));
        mExplosionSound = Gdx.audio.newSound(Gdx.files.internal("audio/explosion.ogg"));
        mDeviateSound = Gdx.audio.newSound(Gdx.files.internal("audio/deviate.ogg"));
        mLifeDownSound = Gdx.audio.newSound(Gdx.files.internal("audio/life_down.ogg"));
        mAudio = Gdx.audio.newMusic(Gdx.files.internal("audio/game.ogg"));
        mAudio.play();
        mAudio.setLooping(true);
    }

    /**
     * Update method from State class called from the Game State Manager
     * @param dt Delta time between each frame
     */
    @Override
    public void update(float dt) {
        mPlayer.update();
        buildMeteor();
        if(hasMeteor()){
           for(Meteor meteor: meteors){
               meteor.update();
           }
        }
        removeMeteorIfNotAlive();
        mHud.update();
        checkGameOver();
    }

    /**
     * Render method from State class called from the Game State Manager
     * @param sb The spriteBatch require to display element on screen
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        mBackground.draw(sb);
        mPlayer.render(sb);
        if(hasMeteor()){
            for(Meteor meteor: meteors){
                meteor.render(sb);
            }
        }
        mController.render(sb);
        mHud.render(sb);
        sb.end();
    }

    /**
     * Dispose method from State class
     */
    @Override
    public void dispose() {
        mHud.dispose();
        mController.dispose();
        mBackground.getTexture().dispose();
        mPlayer.dispose();
        for(Meteor meteor: meteors){
            meteor.dispose();
        }
        mAudio.dispose();
    }

    /**
     * Each 2 seconds, build a new meteor
     * We cannot do this in a thread because we can only create Sprite in the applicationThread who display OpenGL
     */
    private void buildMeteor() {
        long end_time = System.currentTimeMillis();
        if(end_time - mStart_time >= 2000){
            meteors.add(new Meteor());
            mStart_time = System.currentTimeMillis();
        }
    }

    /**
     * Are there meteors ?
     * @return true if we have a meteor in the screen, false other case
     */
    private boolean hasMeteor(){
        if(meteors.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Remove all dead meteors, this way we prevent memory leaks. There are max 3-4 meteors all the time on the screen
     */
    private void removeMeteorIfNotAlive() {
        for (int i=0; i < meteors.size(); i++){
            if(!meteors.get(i).isAlive()){
                // Remove his texture (prevent memory, cpu leaks)
                meteors.get(i).dispose();
                // Remove from the list
                meteors.remove(i);
            }
        }
    }

    /**
     * Check if the player his alive
     */
    public void checkGameOver(){
        if(Player.life <= 0){
            gsm.set(new GameOverState(gsm, Hud.score));
            // dispose all assets elements, to prevent memory leaks
            this.dispose();
        }
    }

    /**
     * Play the explosion sound
     */
    public static void playExplosionSound(){
        mExplosionSound.play();
    }

    /**
     * play the deviate sound
     */
    public static void playDeviateSound() {
        mDeviateSound.play();
    }

    /**
     * Play the life down sound (when we loose a life)
     */
    public static void playlifeDownSound() {
        mLifeDownSound.play();
    }

    /*-------------------------------------------------------------------*/

    /**
     * All methods under this one is for manage the inputs (Touch, mouse, scroll, etc...)
     */
    @Override
    public boolean keyDown(int keycode) {
        // Touch the physical back key of the phone
        if(keycode == Input.Keys.BACK){
            // Set the new state in place of the old
            gsm.set(new MenuState(gsm, null));
            // dispose all assets elements, to prevent memory leaks
            this.dispose();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        // Left mController detection
        if(mController.getLeftBounds().contains(screenX, screenY)){
            mPlayer.setDirection("left");
        }
        // Right mController detection
        if(mController.getRightBounds().contains(screenX, screenY)){
            mPlayer.setDirection("right");
        }
        // Shoot mController detection
        if(mController.getShootBounds().contains(screenX, screenY)){
            if(mPlayer.getLaserType() == "redLaser"){
                mRedLaserSound.play();
            }else{
                mGreenLaserSound.play();
            }
            mPlayer.shoot();
        }
        // Change Weapon
        if(mController.getWeaponBounds().contains(screenX, screenY)){
            if(mPlayer.getLaserType() == "redLaser"){
                mPlayer.setLaserType("greenLaser");
            }else if(mPlayer.getLaserType() == "greenLaser"){
                mPlayer.setLaserType("redLaser");
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // If we touch up from another location as the ShootButton or change weapon button, we stop the mPlayer
        // this way, we can shoot or change Weapon in movement
        if(!mController.getShootBounds().contains(screenX, screenY) &&
            !mController.getWeaponBounds().contains(screenX, screenY)){
            mPlayer.setDirection("stop");
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
