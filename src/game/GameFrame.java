package game;

import java.util.ArrayList;

public class GameFrame {
	private static GameFrame gameInstance = new GameFrame();
	
	//Size of the gameBoard in Pixels
	public static final int gameXSize = 600;
	public static final int gameYSize = 600;
	
	private String quizFileName;
	private ArrayList<String> imageFileNames = new ArrayList<String>();
	
	private GameFrame() {
		
	}
	
	public static GameFrame getInstance() {
		return gameInstance;
	}
	
	public void setConfigFiles(String quizFileName, String[] imageFileNames) {

	}
}
