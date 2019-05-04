package net.enjy.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.Sprite;
import net.enjy.math.Rect;

public class Life extends Sprite {

    private Rect worldBounds;
    private Sound lifeSound;

    private Vector2 v;
    private MainShip mainShip;
    private int hp;

    public Life(TextureRegion regionLife, Rect worldBounds, Sound lifeSound, MainShip mainShip) {
        super(regionLife);
        this.worldBounds = worldBounds;
        this.lifeSound = lifeSound;
        this.mainShip = mainShip;
        this.v = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(
            Vector2 lifeV,
            float height,
            int hp
    ) {
        v.set(lifeV);
        setHeightProportion(height);
        this.hp = hp;
    }

    public void addLife() {
        mainShip.setHp(mainShip.getHp() + hp);
        lifeSound.play();
    }
}
