package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.enjy.base.Sprite;

public class GameOver extends Sprite {

    private static final float HEIGHT = 0.09f;
    private static final float BOTTOM_MARGIN = 0.01f;

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM_MARGIN);
    }
}
