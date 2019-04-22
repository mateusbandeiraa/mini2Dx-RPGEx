package RPGEx;

import org.mini2Dx.core.game.ScreenBasedGame;

import screen.IndoorScreen;

public class RPGEx extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "dev.mateusbandeira.RPGEx";
	
	@Override
    public void initialise() {
		this.addScreen(new IndoorScreen());
    }

	@Override
	public int getInitialScreenId() {
		return IndoorScreen.ID;
	}
}
