package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

public abstract class GameObject {
	private float positionX;
	private float positionY;

	public abstract void update(GameContainer gc, float delta);

	public abstract void render(GameContainer gc, Graphics g);

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	
}
