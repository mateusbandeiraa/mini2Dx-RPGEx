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
	}

	@Override
	public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
		player.update(gc, delta);
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// FIXME: Essa lógica não deveria estar no método render.
		float scale = 1.5f;
		g.setScale(scale, scale);
		float tiledMapWidth = tiledMap.getPixelWidth() * scale;
		float tiledMapHeight = tiledMap.getPixelHeight() * scale;
		float gcWidth = gc.getWidth();
		float gcHeight = gc.getHeight();
		float posX = (gcWidth - tiledMapWidth) / (2 * scale);
		float posY = (gcHeight - tiledMapHeight) / (2 * scale);

		tiledMap.draw(g, (int) posX, (int) posY);

		player.render(gc, g);
	}

	@Override
	public int getId() {
		return IndoorScreen.ID;
	}

}
