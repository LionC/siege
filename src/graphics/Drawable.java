package graphics;

import java.awt.Graphics2D;

/**
 * A Drawable Object is an Object that can be drawn in a Graphics2D-context
 * @author LionC
 * 
 */
public interface Drawable {
	/**
	 * Draws this Drawable in a given Graphics2D context
	 * @param g The context this should be drawn in
	 */
	public void draw(Graphics2D g);
}
