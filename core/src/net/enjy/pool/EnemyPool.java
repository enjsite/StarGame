package net.enjy.pool;

import com.badlogic.gdx.audio.Sound;

import net.enjy.base.SpritesPool;
import net.enjy.math.Rect;
import net.enjy.sprite.Enemy;
import net.enjy.sprite.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private Rect worldBounds;
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Sound shootSound;
    private MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Rect worldBounds, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, shootSound, worldBounds, mainShip);
    }
}
