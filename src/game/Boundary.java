package game;

public abstract class Boundary {
	protected int pixelX;
	protected int pixelY;
	
	protected int sizeX;
	protected int sizeY;
	
	public abstract boolean detectCollision(Boundary testingBoundary);
}
