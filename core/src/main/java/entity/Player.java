package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.tiled.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

/**
 * Representa o jogador na tela.
 * 
 * Criado em 24/04/19
 * 
 * @author Mateus Bandeira
 */
public class Player extends GridObject {
	/**
	 * Vida atual deste objeto.
	 */
	private Health health;

	/**
	 * Constrói uma instância deste objeto.
	 * 
	 * @param x
	 *            Coordenada X deste objeto em unidades de {@link Tile}, relativo ao
	 *            mapa.
	 * @param y
	 *            Coordenada Y deste objeto em unidades de {@link Tile}, relativo ao
	 *            mapa.
	 * @param map
	 *            Mapa ao qual este objeto está vinculado.
	 */
	public Player(int x, int y, GameMap map) {
		super(x, y, new Texture("tanks_tankDesert2.png"), map);
		health = new Health(10, 10, this);
	}

	@Override
	public void update(GameContainer gc, float delta) {
		super.update(gc, delta);
		int candidateX = this.gridX;
		int candidateY = this.gridY;

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (sprite.isFlipX()) {
				sprite.flip(true, false);
			}
			candidateX++;
		} else if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if (!sprite.isFlipX()) {
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

		health.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		super.render(gc, g);
		health.render(gc, g);
	}
}
