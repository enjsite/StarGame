package net.enjy.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.enjy.base.ScaledTouchUpButton;
import net.enjy.math.Rect;
import net.enjy.screen.GameScreen;

public class NewGame extends ScaledTouchUpButton {

    private Game game;

    public NewGame(TextureAtlas atlas, Game game, Rect worldBounds) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
        setHeightProportion(0.1f);
        setTop(worldBounds.getTop());
    }

    @Override
    protected void action() {
        game.setScreen(new GameScreen());
    }
}
