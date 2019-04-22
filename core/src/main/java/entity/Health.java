package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Health extends GameObject {
	private float maxHealth;
	private float curHealth;
	private GameObject target;

	private float width;
	private float height = 5f;
	
	private float offsetX = 0;
	private float offsetY = -8f;

	private Health() {

	}

	public Health(float maxHealth, GameObject target) {
		this();
		this.maxHealth = maxHealth;
		this.target = target;
		this.width = target.getWidth();
	}

	public Health(float maxHealth, float curHealth, GameObject target) {
		this(maxHealth, target);
		this.curHealth = curHealth;
	}

	@Override
	public void update(GameContainer gc, float delta) {
		
	}

	public float getPercentage() {
		return curHealth / maxHealth;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(getPositionX(), getPositionY(), 16, 3f);
		g.setColor(getPercentage() > 0.4f ? Color.GREEN : Color.RED);
		g.fillRect(getPositionX(), getPositionY(), (16f * getPercentage()), 3f);
	}
	
	public float getPositionX() {
		return target.getPositionX() + offsetX;
	}

	public float getPositionY() {
		return target.getPositionY() + offsetY;
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

}
