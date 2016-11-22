package game;

public class GameFrame {
	private static GameFrame gameInstance = new GameFrame();
	
	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;
	
	private GameFrame() {
		
	}
	
	public GameFrame getInstance() {
		return gameInstance;
	}
}
