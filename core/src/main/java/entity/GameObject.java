package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.GameScreen;

/**
 * Representa um objeto renderizável.
 * 
 * Criado em 23/04/19
 * 
 * @author Mateus Bandeira
 */
public abstract class GameObject {
	/**
	 * Atualiza o estado deste objeto.
	 * 
	 * @param gc
	 *            The {@link GameContainer} of the game
	 * @param delta
	 *            The time in seconds since the last update
	 * 
	 * @see GameScreen#update(GameContainer, org.mini2Dx.core.screen.ScreenManager,
	 *      float)
	 */
	public abstract void update(GameContainer gc, float delta);

	/**
	 * Renderiza este objeto na tela.
	 *
	 * @param gc
	 *            The {@link GameContainer} of the game
	 * @param g
	 *            The {@link Graphics} context available for rendering
	 * @see GameScreen#render(GameContainer, Graphics);
	 */
	public abstract void render(GameContainer gc, Graphics g);

	/**
	 * Obtém a posição X (em pixels) em que este objeto deve ser renderizado.
	 * 
	 * @return A posição X deste objeto.
	 */
	public abstract float getPositionX();

	/**
	 * Obtém a posição Y (em pixels) em que este objeto deve ser renderizado.
	 * 
	 * @return A posição Y deste objeto.
	 */
	public abstract float getPositionY();

	/**
	 * Obtém a largura (em pixels) deste objeto.
	 * 
	 * @return A largura deste objeto.
	 */
	public abstract float getWidth();

	/**
	 * Obtém a altura (em pixels) deste objeto.
	 * 
	 * @return A altura deste objeto
	 */
	public abstract float getHeight();

}
