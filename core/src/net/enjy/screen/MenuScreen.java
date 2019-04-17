package net.enjy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.base.BaseScreen;
import net.enjy.math.Rect;
import net.enjy.sprite.Background;
import net.enjy.sprite.Logo;
import net.enjy.sprite.Ship;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Background background;

    private Texture img;
    private Ship ship;

    private Texture textureLogo;
    private Logo logo;

    @Override
    public void show() {
        super.show();

        bg = new Texture("textures/bg2.jpg");
        background = new Background(new TextureRegion(bg));

        img = new Texture("color_sphere.png");
        ship = new Ship(new TextureRegion(img));

        textureLogo = new Texture("badlogic.jpg");
        logo = new Logo(new TextureRegion(textureLogo));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        ship.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta){
        logo.update(delta);
        ship.update(delta);
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        ship.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
        textureLogo.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        ship.touchDown(touch, pointer);
        logo.touchDown(touch,pointer);
        return false;
    }

}
