package net.enjy.pool;

import net.enjy.base.SpritesPool;
import net.enjy.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
