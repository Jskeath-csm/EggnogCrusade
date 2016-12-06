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
		gf = GameFrame.getInstance();
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
