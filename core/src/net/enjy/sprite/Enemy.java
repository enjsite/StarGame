package net.enjy.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import net.enjy.base.Ship;
import net.enjy.math.Rect;
import net.enjy.pool.BulletPool;

public class Enemy extends Ship {

    private MainShip mainShip;

    public Enemy(BulletPool bulletPool, Sound shootSound, Rect worldBounds, MainShip mainShip) {
        this.mainShip = mainShip;
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
        v.set(v0);
        reloadTimer = reloadInterval;
    }
}
