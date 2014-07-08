package model;
import java.awt.Rectangle;

/**
 * Represents a Hitbox of any kind, implementing basic collision and moving
 * @author LionC
 *
 */
public class Hitbox {
	private Rectangle theBox;
	
	/**
	 * Creates a new Hitbox
	 * @param x X-Position
	 * @param y Y-Position
	 * @param width Width
	 * @param height Height
	 */
	public Hitbox (int x, int y, int width, int height) {
		this.theBox = new Rectangle(x,y,width,height);
	}
	
	/**
	 * Check if this Hitbox collides with another one
	 * @param otherBox The other Hitbo
	 * @return True if the two boxes collide, false if not
	 */
	public boolean collides(Hitbox otherBox) {
		return this.theBox.intersects(otherBox.theBox);
	}
	
	/**
	 * Moves this Hitbox
	 * @param dx The amountof movement on the x-axis
	 * @param dy The amount of movement on the y-axis
	 */
	public void move(int dx, int dy) {
		this.theBox.setLocation((int)this.theBox.getX() + dx, (int)this.theBox.getY() + dy);
	}
	
	/**
	 * Sets the position of this Hitbox
	 * @param x The new x Position
	 * @param y The new y Position
	 */
	public void setPosition(int x, int y) {
		this.theBox.setLocation(x, y);
	}
}
