package entity;

import java.io.IOException;
import java.util.PriorityQueue;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.tiled.Tile;
import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.collisions.TiledCollisionMapper;

import com.badlogic.gdx.files.FileHandle;

/**
 * Representa um mapa de <code>Tile</code>s.
 * 
 * @author Mateus Bandeira
 * @see TiledMap
 * @see Tile
 * 
 *      Criado em 23/04/19
 */
public class GameMap extends TiledMap {
	/**
	 * Posição X (em pixels) na qual este objeto será renderizado.
	 */
	private int positionX = 0;
	/**
	 * Posição Y (em pixels) na qual este objeto será renderizado.
	 */
	private int positionY = 0;
	/**
	 * Multiplicador utilizado para ampliar o tamanho deste mapa em tempo de
	 * renderização.
	 */
	private float renderScale = 1f;
	/**
	 * Multiplicador utilizado para ampliar este mapa além dos limites do contêiner.
	 * É útil para cortar as bordas pretas que envolvem mapas menores que o tamanho
	 * padrão de 32x32 {@link Tile}s.
	 */
	private float mapOffsetScale = 1.5f;
	/**
	 * Fila de {@link GameObject}s vinculados a este mapa.
	 */
	private PriorityQueue<GameObject> objects;

	/**
	 * Array bidimensional que representa a camada Collisions deste mapa.
	 * Uma célula de valor <code>0</code> representa um {@link Tile} sem restrição
	 * de colisão. Uma célula de valor 1 representa um Tile que pode ser colidido.
	 * 
	 * @see TiledCollisionMapper
	 */
	private byte[][] collisions;

	/**
	 * Constrói uma instância deste objeto.
	 * 
	 * @param fileHandle
	 *            Arquivo do asset com extensão <code>.tmx</code>.
	 * @throws IOException
	 */
	public GameMap(FileHandle fileHandle) throws IOException {
		super(fileHandle);
		STRICT_LAYER_VISIBILITY = true; // True para não renderizar layers ocultas, como a Collisions.
		collisions = TiledCollisionMapper.mapCollisionsByLayer(this, "Collisions");
		objects = new PriorityQueue<GameObject>();

	}

	/**
	 * Atualiza o estado deste objeto.
	 * A posição deste objeto é calculada de modo que seja
	 * renderizado no centro do contêiner. Em seguida o método
	 * <code>update</code> é chamado em todos os {@link GameObject}s em
	 * {@link GameMap#objects}.
	 * 
	 * @param gc
	 *            The {@link GameContainer} of the game
	 * @param screenManager
	 *            The {@link ScreenManager} of the game
	 * @param delta
	 *            The time in seconds since the last update
	 */
	public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
		super.update(delta);

		int tiledMapWidth = this.getPixelWidth();
		int tiledMapHeight = this.getPixelHeight();
		float gcWidth = gc.getWidth();
		float gcHeight = gc.getHeight();

		renderScale = ((gcWidth - tiledMapWidth) < (gcHeight - tiledMapHeight) ? gcWidth / tiledMapWidth
				: gcHeight / tiledMapHeight) * mapOffsetScale;

		float scaledTiledMapWidth = tiledMapWidth * renderScale;
		float scaledTiledMapHeight = tiledMapHeight * renderScale;

		positionX = (int) ((gcWidth - scaledTiledMapWidth) / (2 * renderScale));
		positionY = (int) ((gcHeight - scaledTiledMapHeight) / (2 * renderScale));

		objects.forEach(gameObject -> gameObject.update(gc, delta));
	}

	/**
	 * Renderiza este objeto na escala e posição calculadas anteriormente em
	 * {@link GameMap#update}. Em seguida, o método <code>render</code> é chamado em
	 * todos os {@link GameObject}s em {@link GameMap#objects}.
	 * 
	 * @param gc
	 *            The {@link GameContainer} of the game
	 * @param g
	 *            The {@link Graphics} context available for rendering
	 */
	public void render(GameContainer gc, Graphics g) {
		g.scale(renderScale, renderScale);
		this.draw(g, positionX, positionY);

		objects.forEach(gameObject -> gameObject.render(gc, g));
		g.scale(1, 1);
	}

	/**
	 * Adiciona um <code>GameObject</code> a este mapa.
	 * 
	 * @param o
	 *            O {@link GameObject} a ser adicionado.
	 */
	public void addObject(GameObject o) {
		objects.add(o);
	}

	/**
	 * Remove um <code>GameObject</code> deste mapa.
	 * 
	 * @param o
	 *            O {@link GameObject} a ser removido.
	 */
	public void removeObject(GameObject o) {
		objects.remove(o);
	}

	/**
	 * Obtém o array de colisões deste mapa. Uma célula com valor 0 não é uma
	 * colisão. Uma célula com valor 1 é uma colisão.
	 * 
	 * <pre>
	 * 
	 * byte[][] colisoes = getCollisions();
	 * if (colisoes[0][5] == 0) {
	 * 	// Tile na posição (0, 5) NÃO é uma colisão.
	 * }
	 * </pre>
	 *
	 * @see GameMap#collisions
	 * @return O array de colisões.
	 */
	public byte[][] getCollisions() {
		return collisions;
	}

	/**
	 * Obtém a posição X <strong>em pixels</strong> que um {@link Tile} é
	 * renderizado.
	 * <br/>
	 * O valor é calculado pela expressão:
	 * <code>posicaoXInicial&nbsp+&nbsp(tile&nbsp+&nbsplarguraDoTile)</code>.
	 * 
	 * @param numeroColuna
	 *            O índice da coluna do Tile.
	 * @return A posição X em pixels do Tile.
	 */
	public float getPositionXbyTile(int numeroColuna) {
		return positionX + (numeroColuna * this.getTileWidth());
	}

	/**
	 * Obtém a posição Y <strong>em pixels</strong> que um {@link Tile} é
	 * renderizado.
	 * <br/>
	 * O valor é calculado pela expressão:
	 * <code>posicaoYInicial&nbsp+&nbsp(tile&nbsp+&nbspalturaDoTile)</code>.
	 * 
	 * @param numeroLinha
	 *            O índice da linha do Tile.
	 * @return A posição Y em pixels do Tile.
	 */
	public float getPositionYbyTile(int numeroLinha) {
		return positionY + (numeroLinha * this.getTileHeight());
	}
}
