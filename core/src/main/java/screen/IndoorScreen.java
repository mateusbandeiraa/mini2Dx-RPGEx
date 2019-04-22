package screen;

import java.io.IOException;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;

import com.badlogic.gdx.Gdx;

import entity.GameMap;
import entity.Player;

public class IndoorScreen extends BasicGameScreen {
	public static final int ID = 0;

	private GameMap tiledMap;
	private Player player;

	@Override
	public void initialise(GameContainer gc) {
		try {
			tiledMap = new GameMap(Gdx.files.internal("Maps/sample_indoor.tmx"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		player = new Player(16, 18, tiledMap);
		 tiledMap.addObject(player);
	}

	@Override
	public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
		tiledMap.update(gc, screenManager, delta);
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		tiledMap.render(gc, g);
	}

	@Override
	public int getId() {
		return IndoorScreen.ID;
	}

}
