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
	protected static final float INITIAL_DIMENSIONS = 16f;

	public GridObject(Texture texture, GameMap map) {
		this.map = map;
		sprite = new Sprite(texture);
		sprite.setSize(INITIAL_DIMENSIONS, INITIAL_DIMENSIONS);
	}

	public GridObject(int x, int y, Texture texture, GameMap map) {
		this(texture, map);
		this.gridX = x;
		this.gridY = y;
	}

	@Override
	public void update(GameContainer gc, float delta) {
		this.setGridX(gridX);
		this.setGridY(gridY);
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
		this.setPositionX(map.getPositionXbyTile(gridX));
	}

	@Override
	public float getPositionX() {
		return sprite.getX();
	}

	public void setPositionX(float positionX) {
		this.sprite.setX(positionX);
	}

	@Override
	public float getWidth() {
		return sprite.getWidth();
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
		this.setPositionY(map.getPositionYbyTile(gridY));
	}

	@Override
	public float getPositionY() {
		return sprite.getY();
	}

	public void setPositionY(float positionY) {
		this.sprite.setY(positionY);
	}

	@Override
	public float getHeight() {
		return sprite.getHeight();
	}

}
