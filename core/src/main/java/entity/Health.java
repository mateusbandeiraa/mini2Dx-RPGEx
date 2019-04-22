package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class Health extends GameObject {
	private float maxHealth;
	private float curHealth;
	private GameObject target;

	private float innerWidth;
	private float innerHeight = 3f;
	private float border = .5f;
	
	private float offsetX = 0;
	private float offsetY = -8f;

	private Health() {

	}

	public Health(float maxHealth, GameObject target) {
		this();
		this.maxHealth = maxHealth;
		this.target = target;
		this.innerWidth = target.getWidth();
	}

	public Health(float maxHealth, float curHealth, GameObject target) {
		this(maxHealth, target);
		this.curHealth = curHealth;
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			this.changeHealth(-1);
		} else if(Gdx.input.isKeyJustPressed(Keys.W)){
			this.changeHealth(+1);
		}
	}

	public float getPercentage() {
		return curHealth / maxHealth;
	}
	
	public void changeHealth(int amount) {
		if(curHealth + amount >= 0 && curHealth + amount <= maxHealth) {
			curHealth += amount;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		g.setColor(getPercentage() > 0.4f ? Color.GREEN : Color.RED);
		g.fillRect((getPositionX() + border), (getPositionY() + border), (getInnerWidth() * getPercentage()), innerHeight);
	}
	
	public float getPositionX() {
		return target.getPositionX() + offsetX;
	}

	public float getPositionY() {
		return target.getPositionY() + offsetY;
	}

	@Override
	public float getWidth() {
		return this.innerWidth + (border * 2);
	}
	
	private float getInnerWidth() {
		return this.innerWidth;
	}

	@Override
	public float getHeight() {
		return this.innerHeight + (border * 2);
	}

}
