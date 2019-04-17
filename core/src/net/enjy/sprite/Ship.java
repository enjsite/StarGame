package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.Sprite;
import net.enjy.math.Rect;

public class Ship extends Sprite {

    private Vector2 touch;
    private Vector2 v;
    private Vector2 bufV;
    private Vector2 buf;

    public Ship(TextureRegion region) {
        super(region);

        setHeightProportion(0.1f);

        touch = new Vector2();
        v = new Vector2();
        bufV = new Vector2();
        buf = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.1f);
        pos.set(-0.1f, -0.1f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        buf.set(touch);
        bufV.set(v);
        if (buf.sub(pos).len() <= bufV.scl(delta).len()) {
            pos.set(touch);
        }else{
            pos.mulAdd(v, delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        v.set(touch.cpy().sub(pos));
        v.setLength(0.05f);
        return false;
    }
}
