package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

public abstract class GameObject {
	public abstract void update(GameContainer gc, float delta);

	public abstract void render(GameContainer gc, Graphics g);

	public abstract float getPositionX();
	public abstract float getPositionY();

	public abstract float getWidth();
	public abstract float getHeight();

}
