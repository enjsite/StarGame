package net.enjy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	static Texture bgTexture;
	static Sprite bgSprite;
	Texture img;
	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bgTexture = new Texture("textures/bg1.jpg");
		bgSprite = new Sprite(bgTexture);
		//bgSprite.setSize(bgSprite.getWidth(), bgSprite.getHeight());
		//bgSprite.setPosition(0f, 0f);

		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img, 100, 100, 150, 150);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.5f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bgTexture, 0, 0, bgSprite.getWidth(), bgSprite.getHeight());
		batch.draw(img, 0, 0, 256, 256);
		batch.draw(region, 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bgTexture.dispose();
	}
}
