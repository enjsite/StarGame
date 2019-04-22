package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.Sprite;
import net.enjy.math.Rect;

public class Enemy extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2(0f, -0.2f);

    private TextureRegion enemyRegion;
    private TextureAtlas atlas = new TextureAtlas("textures/mainAtlas.tpack");;

    public Enemy() {
        regions = new TextureRegion[1];
    }

    public void set(
            Vector2 pos0,
            float height,
            Rect worldBounds
    ) {
        this.regions[0] = atlas.findRegion("enemy0");
        this.pos.set(pos0);
        this.v.set(v);
        setHeightProportion(height);
        float aspect = regions[0].getRegionWidth() / (float) regions[0].getRegionHeight();
        setWidth(height * aspect);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }
}
