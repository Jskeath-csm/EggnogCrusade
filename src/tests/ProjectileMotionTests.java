package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Eggnog;
import game.GameFrame;

public class ProjectileMotionTests {

	private static GameFrame gf;

	@BeforeClass
	public void setup(){
		gf = GameFrame.getInstance();
	}

	@Test
	public void testPositions() {
		Eggnog nogg = gf.getProjectile();

		//multiple projectile position tests

		nogg.setDeltaT(1.0);
		nogg.setAngle(45.0);
		nogg.reset();

		//updates motion every dt (in reality dt small, update called from timer every dt seconds)
		nogg.updateMotion();
		nogg.updateMotion();
		nogg.updateMotion();

		Point pos = new Point();
		pos.setLocation(3.5,4); //predicted position
		assertEquals(pos, nogg.getPosition());

		//2nd position test
		nogg.setAngle(25.0);
		nogg.reset();

		//updates motion every dt (in reality dt small, update called from timer every dt seconds)
		nogg.updateMotion();
		nogg.updateMotion();
		nogg.updateMotion();

		pos.setLocation(3.5,4); //predicted position
		assertEquals(pos, nogg.getPosition());

		//3rd position test
		nogg.setAngle(15.0);
		nogg.reset();

		//updates motion every dt (in reality dt small, update called from timer every dt seconds)
		nogg.updateMotion();
		nogg.updateMotion();
		nogg.updateMotion();

		pos.setLocation(3.5,4); //predicted position
		assertEquals(pos, nogg.getPosition());

	}

}
