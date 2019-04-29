package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.enjy.base.Sprite;
import net.enjy.math.Rect;

public class GameOver extends Sprite {

    public GameOver(TextureAtlas atlas, Rect worldBounds) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.1f);
        setTop(worldBounds.getTop()+0.2f);
    }
}
