package entity;

import java.io.IOException;
import java.util.PriorityQueue;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.tiled.TileLayer;
import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.collisions.TiledCollisionMapper;

import com.badlogic.gdx.files.FileHandle;

public class GameMap extends TiledMap {
	private int positionX = 0;
	private int positionY = 0;
	private float renderScale = 1f;
	private float mapOffsetScale = 1.5f;
	private PriorityQueue<GameObject> objects;

	private byte[][] collisions;

	public GameMap(FileHandle fileHandle) throws IOException {
		super(fileHandle);
		STRICT_LAYER_VISIBILITY = true; // True para não renderizar layers ocultas, como a Collisions.
		collisions = TiledCollisionMapper.mapCollisionsByLayer(this, "Collisions");
		objects = new PriorityQueue<GameObject>();

	}

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

	public void render(GameContainer gc, Graphics g) {
		g.scale(renderScale, renderScale);
		this.draw(g, positionX, positionY);

		objects.forEach(gameObject -> gameObject.render(gc, g));
		g.scale(1, 1);
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void removeObject(GameObject o) {
		objects.remove(o);
	}

	@Override
	protected void onLayerRendered(Graphics g, TileLayer layer, int startTileX, int startTileY, int widthInTiles,
			int heightInTiles) {
		super.onLayerRendered(g, layer, startTileX, startTileY, widthInTiles, heightInTiles);
	}

	public byte[][] getCollisions() {
		return collisions;
	}

	// FIXME: Não acho que esse é um bom nome.
	public float getPositionXbyTile(int tile) {
		return positionX + (tile * this.getTileWidth());
	}

	public float getPositionYbyTile(int tile) {
		return positionY + (tile * this.getTileHeight());
	}
}
