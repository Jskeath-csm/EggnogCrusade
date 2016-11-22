package game;

public class GameFrame {
	private static GameFrame gameInstance = new GameFrame();

	
	private GameFrame() {
		
	}
	
	public GameFrame getInstance() {
		return gameInstance;
	}
}
