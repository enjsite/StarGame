package net.enjy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.BaseScreen;
import net.enjy.math.Rect;
import net.enjy.math.Rnd;
import net.enjy.pool.BulletPool;
import net.enjy.pool.EnemyPool;
import net.enjy.sprite.Background;
import net.enjy.sprite.Enemy;
import net.enjy.sprite.MainShip;
import net.enjy.sprite.Star;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star starList[];

    private MainShip mainShip;
    private BulletPool bulletPool;

    private EnemyPool enemyPool;
    private Vector2 posEnemy;

    private float reloadInterval = 5f;
    private float reloadTimer;

    private static Sound gameSound = Gdx.audio.newSound(Gdx.files.internal("sounds/music.mp3"));

    @Override
    public void show() {
        super.show();

        gameSound.play();
        bg = new Texture("textures/bg2.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        starList = new Star[64];
        for (int i = 0; i < starList.length; i++){
            starList[i] = new Star(atlas);
        }

        bulletPool = new BulletPool();
        mainShip = new MainShip(atlas, bulletPool);

        enemyPool = new EnemyPool();
        posEnemy = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList){
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyedSprites();
        draw();
    }

    private void update(float delta){
        for (Star star : starList){
            star.update(delta);
        }

        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            enemies();
        }

        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
    }

    private void freeAllDestroyedSprites() {
        bulletPool.freeAllDestroyedSprites();
        enemyPool.freeAllDestroyedSprites();
    }

    private void draw(){
        batch.begin();

        background.draw(batch);
        for (Star star : starList){
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);

        batch.end();
    }

    public void enemies() {
        Enemy enemy = enemyPool.obtain();
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = 0.5f;
        this.posEnemy.set(posX, posY);
        enemy.set(this.posEnemy, 0.15f, worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        gameSound.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return false;
    }
}
