package entity;

import java.io.IOException;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.tiled.TileLayer;
import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.collisions.TiledCollisionMapper;

import com.badlogic.gdx.files.FileHandle;

public class GameMap extends TiledMap {
	
	private float positionX = 0;
	private float positionY = 0;
	
	private byte[][] collisions;
	
	public GameMap(FileHandle fileHandle) throws IOException {
		super(fileHandle);
		collisions = TiledCollisionMapper.mapCollisionsByLayer(this, "Collisions");
		
	}

	@Override
	protected void onLayerRendered(Graphics g, TileLayer layer, int startTileX, int startTileY, int widthInTiles,
			int heightInTiles) {
		super.onLayerRendered(g, layer, startTileX, startTileY, widthInTiles, heightInTiles);
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		this.positionX = x;
		this.positionY = y;
		super.draw(g, x, y);
	}
	
	public byte[][] getCollisions(){
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
