package RPGEx.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import RPGEx.RPGEx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(RPGEx.GAME_IDENTIFIER);
		config.vSyncEnabled = true;
		config.resizable = true;
		new DesktopMini2DxGame(new RPGEx(), config);
	}
}
