package net.enjy.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import net.enjy.StarGame;
import net.enjy.base.BaseScreen;
import net.enjy.math.Rect;
import net.enjy.pool.BulletPool;
import net.enjy.pool.EnemyPool;
import net.enjy.pool.ExplosionPool;
import net.enjy.sprite.Background;
import net.enjy.sprite.Bullet;
import net.enjy.sprite.Enemy;
import net.enjy.sprite.GameOver;
import net.enjy.sprite.MainShip;
import net.enjy.sprite.NewGame;
import net.enjy.sprite.Star;
import net.enjy.sprite.TrackingStar;
import net.enjy.utils.EnemyGenerator;
import net.enjy.utils.Font;

import java.util.List;

public class GameScreen extends BaseScreen {

    private static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final String LEVEL = "Level: ";

    private enum State {PLAYING, PAUSE, GAME_OVER}
    private State state;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TrackingStar starList[];

    private MainShip mainShip;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;

    private Music music;
    private Sound laserSound;
    private Sound bulletSound;
    private Sound explosionSound;

    private EnemyGenerator enemyGenerator;

    private GameOver gameOver;
    private NewGame newGame;

    private Font font;
    private StringBuilder sbFrags = new StringBuilder();
    private StringBuilder sbHp = new StringBuilder();
    private StringBuilder sbLevel = new StringBuilder();

    private int frags = 0; // count of murdered enemies

    @Override
    public void show() {
        super.show();

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        bg = new Texture("textures/bg2.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        starList = new TrackingStar[64];

        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound);

        for (int i = 0; i < starList.length; i++){
            starList[i] = new TrackingStar(atlas, mainShip.getV());
        }

        enemyPool = new EnemyPool(bulletPool, explosionPool, bulletSound, worldBounds, mainShip);
        enemyGenerator = new EnemyGenerator(atlas, enemyPool, worldBounds);

        font = new Font("font/font.fnt", "font/font.png");
        font.setFontSize(0.02f);
        state = State.PLAYING;

        gameOver = new GameOver(atlas);
        newGame = new NewGame(atlas, this);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList){
            star.resize(worldBounds);
        }
        if (state == State.PLAYING) {
            mainShip.resize(worldBounds);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        freeAllDestroyedSprites();
        draw();
    }

    private void update(float delta){
        for (Star star : starList){
            star.update(delta);
        }
        explosionPool.updateActiveSprites(delta);
        if (state == State.PLAYING) {
            mainShip.update(delta);
            enemyGenerator.generate(delta, frags);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
        } else if (state == State.GAME_OVER) {
            gameOver.update(delta);
            newGame.update(delta);
        }
    }

    private void checkCollisions() {
        if (state != State.PLAYING) {
            return;
        }
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy : enemyList){
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + mainShip.getHalfWidth();
            if (enemy.pos.dst(mainShip.pos) < minDist) {
                enemy.destroy();
                mainShip.destroy();
                state = State.GAME_OVER;
                return;
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList){
            if (bullet.isDestroyed()) {
                continue;
            }
            if (bullet.getOwner() == mainShip) {
                for (Enemy enemy : enemyList) {
                    if (enemy.isDestroyed()) {
                        continue;
                    }
                    if (enemy.isBulletCollision(bullet)) {
                        enemy.damage(bullet.getDamage());
                        bullet.destroy();
                        if (enemy.isDestroyed()) {
                            frags++;
                        }
                        return;
                    }
                }
            } else {
                if (mainShip.isBulletCollision(bullet)) {
                    mainShip.damage(bullet.getDamage());
                    if (mainShip.isDestroyed()) {
                        state = State.GAME_OVER;
                    }
                    bullet.destroy();
                    return;
                }
            }
        }
    }

    private void freeAllDestroyedSprites() {
        bulletPool.freeAllDestroyedSprites();
        enemyPool.freeAllDestroyedSprites();
        explosionPool.freeAllDestroyedSprites();
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        for (Star star : starList){
            star.draw(batch);
        }
        if (state == State.PLAYING) {
            mainShip.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
        } else if (state == State.GAME_OVER) {
            gameOver.draw(batch);
            newGame.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        printInfo();
        batch.end();
    }

    private void printInfo() {
        sbFrags.setLength(0);
        sbHp.setLength(0);
        sbLevel.setLength(0);
        font.draw(batch, sbFrags.append(FRAGS).append(frags), worldBounds.getLeft(), worldBounds.getTop());
        font.draw(batch, sbHp.append(HP).append(mainShip.getHp()), worldBounds.pos.x, worldBounds.getTop(), Align.center);
        font.draw(batch, sbLevel.append(LEVEL).append(enemyGenerator.getStage()), worldBounds.getRight(), worldBounds.getTop(), Align.right);
    }

    @Override
    public void pause() {
        super.pause();
        if (state == State.PLAYING) {
            state = State.PAUSE;
        }
    }

    @Override
    public void resume() {
        super.resume();
        if (state == State.PAUSE) {
            state = State.PLAYING;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        music.dispose();
        laserSound.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        font.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            mainShip.touchDown(touch, pointer);
        } else if (state == State.GAME_OVER) {
            newGame.touchDown(touch, pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            mainShip.touchUp(touch, pointer);
        } else if (state == State.GAME_OVER) {
            newGame.touchUp(touch, pointer);
        }
        return false;
    }

    public void reset() {
        state = State.PLAYING;
        mainShip.reset();
        frags = 0;

        enemyGenerator.setStage(1);

        bulletPool.freeAllActiveSprites();
        enemyPool.freeAllActiveSprites();
        explosionPool.freeAllActiveSprites();

    }
}
