package entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.tiled.Tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Representa um {@link GameObject} cuja posição está vinculada ao grid de
 * um {@link GameMap}.
 * 
 * Criado em 23/04/19
 * 
 * @author Mateus Bandeira
 */
public abstract class GridObject extends GameObject {
	/**
	 * Armazena dados como posição, tamanho e textura que será renderizada por este
	 * objeto.
	 * 
	 * @see Sprite
	 */
	protected Sprite sprite;
	/**
	 * Mapa no qual este objeto está vinculado.
	 * 
	 * @see GameMap
	 */
	protected GameMap map;
	/**
	 * Posição X atual deste objeto em unidades de {@link Tile}.
	 * 
	 * @see TiledMap
	 * @see Tile
	 */
	protected int gridX;
	/**
	 * Posição Y atual deste objeto em unidades de {@link Tile}.
	 * 
	 * @see TiledMap
	 * @see Tile
	 */
	protected int gridY;
	/**
	 * Dimensão inicial do sprite (em pixels).
	 */
	protected static final float INITIAL_DIMENSIONS = 16f;

	/**
	 * Constrói uma instância deste objeto com uma imagem definida, e a vincula a um
	 * mapa.
	 * 
	 * @param texture
	 *            A imagem deste objeto que será renderizada.
	 * @param map
	 *            O mapa sobre o qual este objeto será renderizado e terá seus
	 *            movimentos limitados.
	 * @see Texture
	 * @see GridObject#map
	 */
	public GridObject(Texture texture, GameMap map) {
		this.map = map;
		sprite = new Sprite(texture);
		sprite.setSize(INITIAL_DIMENSIONS, INITIAL_DIMENSIONS);
	}

	/**
	 * Constrói uma instância deste objeto com posições X, Y, uma imagem definida, e
	 * a vincula a um mapa.
	 * 
	 * @param x
	 *            A posição X deste objeto (em unidades de {@link Tile}) em relação
	 *            ao mapa.
	 * @param y
	 *            A posição Y deste objeto (em unidades de {@link Tile}) em relação
	 *            ao mapa.
	 * @param texture
	 *            A imagem deste objeto que será renderizada.
	 * @param map
	 *            O mapa sobre o qual este objeto será renderizado e terá seus
	 *            movimentos limitados.
	 * 
	 * @see Texture
	 * @see GridObject#map
	 */
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

	/**
	 * Obtém a posição X deste objeto relativa ao mapa em que está vinculado em
	 * unidades de {@link Tile}.
	 * 
	 * @return A posição X deste objeto em unidades de {@link Tile}.
	 * @see GridObject#map
	 */
	public int getGridX() {
		return gridX;
	}

	/**
	 * Altera a posição X deste objeto em relação ao mapa, e atualiza a posição X do
	 * {@link GridObject#sprite} (em pixels).
	 * 
	 * @param gridX
	 *            A posição X (em unidades de {@link Tile}) que este objeto deve
	 *            assumir.
	 */
	public void setGridX(int gridX) {
		this.gridX = gridX;
		this.setPositionX(map.getPositionXbyTile(gridX));
	}

	/**
	 * Obtém a posição X (em pixels) do {@link GridObject#sprite} deste objeto.
	 * 
	 * @return A posição X em pixels deste objeto.
	 */
	@Override
	public float getPositionX() {
		return sprite.getX();
	}

	/**
	 * Altera a posição X (em pixels) do {@link GridObject#sprite} deste objeto.
	 * 
	 * @param positionX
	 *            A posição X (em pixels) que este objeto deve assumir.
	 */
	private void setPositionX(float positionX) {
		this.sprite.setX(positionX);
	}

	@Override
	public float getWidth() {
		return sprite.getWidth();
	}

	/**
	 * Obtém a posição Y deste objeto relativa ao mapa em que está vinculado em
	 * unidades de {@link Tile}.
	 * 
	 * @return A posição Y deste objeto em unidades de {@link Tile}.
	 * @see GridObject#map
	 */
	public int getGridY() {
		return gridY;
	}

	/**
	 * Altera a posição Y deste objeto em relação ao mapa, e atualiza a posição YX
	 * do
	 * {@link GridObject#sprite} (em pixels).
	 * 
	 * @param gridY
	 *            A posição Y (em unidades de {@link Tile}) que este objeto deve
	 *            assumir.
	 */
	public void setGridY(int gridY) {
		this.gridY = gridY;
		this.setPositionY(map.getPositionYbyTile(gridY));
	}

	@Override
	public float getPositionY() {
		return sprite.getY();
	}

	/**
	 * Altera a posição Y (em pixels) do {@link GridObject#sprite} deste objeto.
	 * 
	 * @param positionY
	 *            A posição Y (em pixels) que este objeto deve assumir.
	 */
	private void setPositionY(float positionY) {
		this.sprite.setY(positionY);
	}

	@Override
	public float getHeight() {
		return sprite.getHeight();
	}

}
