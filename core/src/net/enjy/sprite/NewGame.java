package net.enjy.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.enjy.base.ScaledTouchUpButton;
import net.enjy.screen.GameScreen;

public class NewGame extends ScaledTouchUpButton {

    private GameScreen gameScreen;
    private static final float HEIGHT = 0.06f;
    private static final float TOP_MARGIN = -0.01f;

    public NewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
        setHeightProportion(HEIGHT);
        setTop(TOP_MARGIN);
    }

    @Override
    protected void action() {
        gameScreen.reset();
    }
}
