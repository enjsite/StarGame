package net.enjy.pool;

import com.badlogic.gdx.audio.Sound;

import net.enjy.base.SpritesPool;
import net.enjy.math.Rect;
import net.enjy.sprite.Enemy;
import net.enjy.sprite.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private Rect worldBounds;
    private BulletPool bulletPool;
    private Sound shootSound;
    private MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, Sound shootSound, Rect worldBounds, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, shootSound, worldBounds, mainShip);
    }
}
