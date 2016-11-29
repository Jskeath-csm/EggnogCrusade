package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameFrame;

public class TestGameInit {

	private static GameFrame gf;
	
	@BeforeClass
	public static void setup(){
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add("images/Backdrop.png");
		filenames.add("images/Bucket.png");
		filenames.add("images/Button.png");
		filenames.add("images/Catapult.png");
		filenames.add("images/CoolSanta.png");
		filenames.add("images/Nog.png");
		filenames.add("images/NormalSanta.png");
		gf = GameFrame.getInstance();
		gf.setConfigFiles("", filenames);
	}
	
	@Test
	public void drawTrajectoryTest() {
		gf.calculateTrajectory(45.0);
		ArrayList<Point> pts = gf.getTrajectory();
		assertTrue(pts.size() > 0);
	}
	
	@Test
	public void loadImagesTest(){
		try{
			gf.loadImages();
			assertTrue(true);
		}
		catch(IOException e){
			assertTrue(false);
		}
	}
	

}
