package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

/**
 * Representa o nível de vida de um {@link GameObject}.
 * 
 * Criado em 24/04/19
 * 
 * @author Mateus Bandeira
 */
public class Health extends GameObject {
	/**
	 * Máximo de vida deste objeto.
	 */
	private float maxHealth;
	/**
	 * Vida atual deste objeto.
	 */
	private float curHealth;
	/**
	 * GameObject ao qual este objeto está vinculado.
	 */
	private GameObject target;

	/**
	 * Largura da barra de vida a ser renderizada por este objeto (em pixels).
	 */
	private float innerWidth;
	/**
	 * Altura da barra de vida a ser renderizada por este objeto (em pixels).
	 */
	private float innerHeight = 3f;
	/**
	 * Expessura da borda branca a ser renderizada em volta deste objeto (em
	 * pixels).
	 */
	private float border = .5f;

	/**
	 * Deslocamento horizontal em relação à posição do {@link Health#target} (em
	 * pixels).
	 * <br/>
	 * Valores positivos deslocam à direita. Valores negativos deslocam à esquerda.
	 */
	private float offsetX = 0;

	/**
	 * Deslocamento vertical em relação à posição do {@link Health#target} (em
	 * pixels).
	 * <br/>
	 * Valores positivos deslocam para baixo. Valores negativos deslocam para cima.
	 */
	private float offsetY = -8f;

	/**
	 * Constrói uma instância deste objeto.
	 */
	private Health() {

	}

	/**
	 * Constrói uma instância deste objeto.
	 * 
	 * @param maxHealth
	 *            O valor máximo de vida deste objeto.
	 * @param target
	 *            O {@link GameObject} ao qual este objeto está vinculado.
	 */
	public Health(float maxHealth, GameObject target) {
		this();
		this.maxHealth = maxHealth;
		this.target = target;
		this.innerWidth = target.getWidth();
	}

	/**
	 * Constrói uma instância deste objeto.
	 * 
	 * @param maxHealth
	 *            O valor máximo de vida deste objeto.
	 * @param curHealth
	 *            O valor de vida atual deste objeto.
	 * @param target
	 *            O {@link GameObject} ao qual este objeto está vinculado.
	 */
	public Health(float maxHealth, float curHealth, GameObject target) {
		this(maxHealth, target);
		this.curHealth = curHealth;
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			this.changeHealth(-1);
		} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
			this.changeHealth(+1);
		}
	}

	/**
	 * Calcula a porcentagem de vida atual em relação ao máximo possível.
	 * 
	 * @return A porcentagem de vida atual.
	 */
	public float getPercentage() {
		return curHealth / maxHealth;
	}

	/**
	 * Atualiza o valor de vida atual. Valores positivos aumentam a vida. Valores
	 * negativos decrementam a vida.
	 * 
	 * @param amount
	 *            A quantidade de vida a ser alterada.
	 */
	public void changeHealth(int amount) {
		if (curHealth + amount >= 0 && curHealth + amount <= maxHealth) {
			curHealth += amount;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		g.setColor(getPercentage() > 0.4f ? Color.GREEN : Color.RED);
		g.fillRect((getPositionX() + border), (getPositionY() + border), (getInnerWidth() * getPercentage()),
				innerHeight);
	}

	@Override
	public float getPositionX() {
		return target.getPositionX() + offsetX;
	}

	@Override
	public float getPositionY() {
		return target.getPositionY() + offsetY;
	}

	@Override
	public float getWidth() {
		return this.innerWidth + (border * 2);
	}

	/**
	 * Obtém a largura interna, em pixels) deste objeto (sem contar com a borda).
	 * 
	 * @return A largura interna.
	 */
	private float getInnerWidth() {
		return this.innerWidth;
	}

	@Override
	public float getHeight() {
		return this.innerHeight + (border * 2);
	}

}
