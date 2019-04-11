package net.enjy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Texture img;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        pos = new Vector2();
        v = new Vector2();

        img = new Texture("color_sphere.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pos.add(v);
		batch.begin();
		batch.draw(img, pos.x, pos.y);
		batch.end();

        if(Math.abs(Math.abs(touch.y) - Math.abs(pos.y)) <= Math.abs(v.y)){
            v.setZero();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = touch.cpy().sub(pos);
        v.setLength(1f);

        return false;
    }
}
