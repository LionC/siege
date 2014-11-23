package model;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * Represents a Hitbox of any kind, implementing basic collision and moving
 * @author LionC
 *
 */
public class Hitbox implements Positioned {
	private Rectangle2D.Float theBox = new Rectangle2D.Float();

    //TODO: remove rectangle, replace with easy primtivies

	/**
	 * Creates a new Hitbox
	 * @param x X-Position
	 * @param y Y-Position
	 * @param width Width
	 * @param height Height
	 */
	public Hitbox (float x, float y, float width, float height) {
		this(new Rectangle2D.Float(x, y, width, height));
	}

    public Hitbox(Rectangle2D aRect) {
        this.theBox.setRect(aRect);
    }

    public float getHeight() {
        return (float) this.theBox.getHeight();
    }

    public float getWidth() {
        return (float) this.theBox.getWidth();
    }

    public void setHeight(float aHeight) {
        this.theBox.setRect(this.theBox.getX(), this.theBox.getY(), this.theBox.getWidth(), aHeight);
    }

    public void setWidth(int aWidth) {
        this.theBox.setRect(this.theBox.getX(), this.theBox.getY(), aWidth, this.theBox.getHeight());
    }

	public boolean collides(Hitbox otherBox) {
		return this.theBox.intersects(otherBox.theBox);
	}

    @Override
    public float getX() {
        return (float) this.theBox.getX();
    }

    @Override
    public float getY() {
        return (float) this.theBox.getY();
    }

    /**
	 * Sets the position of this Hitbox
	 * @param x The new x Position
	 * @param y The new y Position
	 */
    @Override
	public void setPosition(float x, float y) {
		this.theBox.setRect(x, y, this.theBox.getWidth(), this.theBox.getHeight());
	}

    @Override
    public void setX(float aX) {
        this.setPosition(aX, this.getY());
    }

    @Override
    public void setY(float aY) {
        this.setPosition(this.getX(), aY);
    }
}
