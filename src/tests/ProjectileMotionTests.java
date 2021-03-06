package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Bucket;
import game.Eggnog;
import game.GameFrame;

public class ProjectileMotionTests {

	private static GameFrame gf;

	@BeforeClass
	public static void setup(){
		gf = GameFrame.getInstance();
	}

	@Test
	public void testPositions() {
		Eggnog nog = gf.getProjectile();

		//multiple projectile position tests

		nog.setDeltaT(1.0);
		nog.setAngle(45.0);
		nog.reset();

		//updates motion every dt (in reality dt small, update called from timer every dt seconds)
		nog.updateMotion();
		nog.updateMotion();
		nog.updateMotion();

		Point pos = new Point();
		pos.setLocation(333,231); //predicted position
		assertEquals(pos, nog.getPosition());

		//2nd position test
		nog.setAngle(25.0);
		nog.reset();

		//updates motion every dt (in reality dt small, update called from timer every dt seconds)
		nog.updateMotion();
		nog.updateMotion();
		nog.updateMotion();

		pos.setLocation(404,333); //predicted position
		assertEquals(pos, nog.getPosition());

		//3rd position test
		nog.setAngle(15.0);
		nog.reset();

		//updates motion every dt (in reality dt small, update called from timer every dt seconds)
		nog.updateMotion();
		nog.updateMotion();
		nog.updateMotion();

		pos.setLocation(426,392); //predicted position
		assertEquals(pos, nog.getPosition());
	}

	@Test
	public void testOutOfBounds(){
		Eggnog nog = gf.getProjectile();

		//multiple projectile position tests

		nog.setDeltaT(100.0); //large deltaT, definitely will go off screen after 1 update
		nog.setAngle(45.0);
		nog.reset();

		nog.updateMotion();

		assertFalse(nog.isMoving());
	}

	@Test
	public void testCollision(){
		Eggnog nog = gf.getProjectile();
		Bucket b = new Bucket();
		b.setPosition(new Point(3,0));

		//multiple projectile position tests

		nog.setDeltaT(0.030); //roughly 33FPS
		nog.setAngle(15.0); //low enough to hit wall
		nog.reset(); //resets position


		//3 seconds of travel time, had to hit wall
		for(int i=0;i<100;i++){
			nog.updateMotion();
		}

		assertFalse(nog.isMoving());

		/*
		 * TEST 2
		 */

		//test if we hit a bucket
		b.setPosition(new Point(3,0)); //needs to change so we know the projectile can hit it

		nog.setDeltaT(0.030); //roughly 33FPS
		nog.setAngle(45.0); //angle to hit bucket
		nog.reset(); //resets position

		//3 seconds of travel time to make sure we hit the bucket
		for(int i=0;i<100;i++){
			nog.updateMotion();
		}
	}

}
