package game;

public abstract class Boundary {
	protected int pixelX;
	protected int pixelY;
	
	protected int sizeX;
	protected int sizeY;

	protected boolean isVisible = true;
	
	public boolean detectCollision(Eggnog proj) {
		int pX = proj.getPosition().x;
		int pY = proj.getPosition().y;
		
		if((pX > pixelX && pX < pixelX + sizeX) && (pY > pixelY && pY < pixelY + sizeY))
			return true;
		else
			return false;
	}
}
