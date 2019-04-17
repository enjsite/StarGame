package net.enjy;

import com.badlogic.gdx.Game;

import net.enjy.screen.MenuScreen;

public class StarGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}

}
