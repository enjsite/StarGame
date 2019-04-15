package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.Sprite;
import net.enjy.math.Rect;

public class Ship extends Sprite {

    public Vector2 v;
//    protected Vector2 buf;
//    protected Vector2 touch2;

    public Ship(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        pos.set(-0.45f, -0.45f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

//        buf.set(touch2);
//        if(buf.sub(pos).len() > v.len()){
//            pos.add(v);
//        }else{
//            v.setZero();
//            pos.set(touch2);
//        }
        pos.add(v);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
//        touch2.set(touch);
//        buf = new Vector2();

        super.touchDown(touch, pointer);
        v = touch.cpy().sub(pos);
        v.setLength(0.001f);
        return false;
    }
}
