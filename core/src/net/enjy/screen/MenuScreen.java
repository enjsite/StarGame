package net.enjy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Vector2 pos;
//    private Vector2 v;
//    private Vector2 buf;
    private Texture img;

    @Override
    public void show() {
        super.show();
//        touch = new Vector2();
        pos = new Vector2();
//        v = new Vector2();
//        buf = new Vector2();
        img = new Texture("badlogic.jpg");
        batch.getProjectionMatrix().idt();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        buf.set(touch);
//        if(buf.sub(pos).len() > v.len()){
//            pos.add(v);
//        }else{
//            v.setZero();
//            pos.set(touch);
//        }

		batch.begin();
		batch.draw(img, -0.5f, -0.5f, 1f, 1f);
		batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
////        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
////        v = touch.cpy().sub(pos);
////        v.setLength(1f);
//
//        return false;
//    }
}
