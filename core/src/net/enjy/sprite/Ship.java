package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.enjy.base.Sprite;
import net.enjy.math.Rect;

public class Ship extends Sprite {
    public Ship(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        pos.set(-0.45f, -0.45f);
    }
}
