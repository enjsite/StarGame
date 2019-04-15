package net.enjy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.BaseScreen;
import net.enjy.math.Rect;
import net.enjy.sprite.Background;
import net.enjy.sprite.Ship;

public class MenuScreen extends BaseScreen {

//    private Vector2 touch;
//    private Vector2 pos;
//    private Vector2 v;
//    private Vector2 buf;

    private Texture bg;
    private Background background;

    private Texture img;
    private Ship ship;

    @Override
    public void show() {
        super.show();
//        touch = new Vector2();
//        pos = new Vector2();
//        buf = new Vector2();

        bg = new Texture("textures/bg2.jpg");
        background = new Background(new TextureRegion(bg));

        img = new Texture("color_sphere.png");
        ship = new Ship(new TextureRegion(img));
        ship.v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        ship.resize(worldBounds);
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

        ship.update(1);

		batch.begin();
        background.draw(batch);
        ship.draw(batch);
		batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        ship.touchDown(touch, pointer);
        return false;
    }

}
