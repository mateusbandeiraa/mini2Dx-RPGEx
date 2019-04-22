package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import com.badlogic.gdx.graphics.Texture;

public abstract class GridObject extends GameObject {
	protected Sprite sprite;
	protected GameMap map;
	protected int gridX;
	protected int gridY;
	protected int dimensions = 16;

	public GridObject(Texture texture, GameMap map) {
		this.map = map;
		sprite = new Sprite(texture);
		sprite.setSize(dimensions, dimensions);
	}

	public GridObject(int x, int y, Texture texture, GameMap map) {
		this(texture, map);
		this.gridX = x;
		this.gridY = y;
	}

	@Override
	public void update(GameContainer gc, float delta) {
		sprite.setX(map.getPositionXbyTile(gridX));
		sprite.setY(map.getPositionYbyTile(gridY));
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawSprite(sprite);
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

}
