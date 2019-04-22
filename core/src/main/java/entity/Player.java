package entity;

import org.mini2Dx.core.game.GameContainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player extends GridObject {

	public Player(int x, int y, GameMap map) {
		super(x, y, new Texture("tanks_tankDesert2.png"), map);
	}

	@Override
	public void update(GameContainer gc, float delta) {
		super.update(gc, delta);
		int candidateX = this.gridX;
		int candidateY = this.gridY;

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if(sprite.isFlipX()) {
				sprite.flip(true, false);
			}
			candidateX++;
		} else if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if(!sprite.isFlipX()) {
				sprite.flip(true, false);
			}
			candidateX--;
		} else if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			candidateY--;
		} else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			candidateY++;
		}

		if (map.getCollisions()[candidateX][candidateY] != 1) {
			this.gridX = candidateX;
			this.gridY = candidateY;
		}

	}
}