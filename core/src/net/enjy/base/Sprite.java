package net.enjy.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.enjy.math.Rect;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    public int frame;

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        //this.frame = frame;
        regions[0] = region;
        System.out.println("FRAME "+ frame);
    }

    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);

    }

    public void resize(Rect worldBounds){

    }

    public void update(float delta){

    }

    public void draw(SpriteBatch batch){
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("touchDown touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        System.out.println("touchUp touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }
}
