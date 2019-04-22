package net.enjy.pool;

import net.enjy.base.SpritesPool;
import net.enjy.sprite.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {

    @Override
    protected Enemy newObject() {
        return new Enemy();
    }
}
