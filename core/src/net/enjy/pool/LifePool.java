package net.enjy.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.enjy.base.SpritesPool;
import net.enjy.math.Rect;
import net.enjy.sprite.Life;
import net.enjy.sprite.MainShip;

public class LifePool extends SpritesPool<Life> {

    private TextureRegion regionLife;
    private Rect worldBounds;
    private Sound lifeSound;
    private MainShip mainShip;

    public LifePool(TextureRegion regionLife, Rect worldBounds, Sound lifeSound, MainShip mainShip) {
        this.regionLife = regionLife;
        this.worldBounds = worldBounds;
        this.lifeSound = lifeSound;
        this.mainShip = mainShip;
    }

    @Override
    protected Life newObject() {
        return new Life(regionLife, worldBounds, lifeSound, mainShip);
    }
}
