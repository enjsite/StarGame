package net.enjy.utils;

import com.badlogic.gdx.math.Vector2;

import net.enjy.math.Rect;
import net.enjy.math.Rnd;
import net.enjy.pool.LifePool;
import net.enjy.sprite.Life;

public class LifeGenerator {

    private static final float LIFE_HEIGHT = 0.05f;
    private static final int LIFE_HP = 5;

    private Rect worldBounds;

    private float generateInterval = 12f;
    private float generateTimer;

    private final Vector2 lifeV = new Vector2(0, -0.5f);

    private LifePool lifePool;

    public LifeGenerator(LifePool lifePool, Rect worldBounds) {
        this.lifePool = lifePool;
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;

            Life life = lifePool.obtain();
            life.set(
                    lifeV,
                    LIFE_HEIGHT,
                    LIFE_HP
            );
            life.pos.x = Rnd.nextFloat(worldBounds.getLeft() + life.getHalfWidth(),
                    worldBounds.getRight() - life.getHalfWidth());
            life.setBottom(worldBounds.getTop());
        }
    }
}
