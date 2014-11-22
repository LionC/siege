package model;
import java.awt.Rectangle;

/**
 * Represents a Hitbox of any kind, implementing basic collision and moving
 * @author LionC
 *
 */
public class Hitbox implements Positioned {
	private Rectangle theBox;

    //TODO: remove rectangle, replace with easy primtivies

	/**
	 * Creates a new Hitbox
	 * @param x X-Position
	 * @param y Y-Position
	 * @param width Width
	 * @param height Height
	 */
	public Hitbox (int x, int y, int width, int height) {
		this(new Rectangle(x,y,width,height));
	}

    /**
     * Creates a new Hitbox
     * @param aBox The Hitbox bounds
     */
    public Hitbox (Rectangle aBox) {
        this.theBox = new Rectangle(aBox);
    }

    public int getHeight() {
        return (int)this.theBox.getHeight();
    }

    public int getWidth() {
        return (int)this.theBox.getWidth();
    }

    public void setHeight(int aHeight) {
        this.theBox.setBounds((int)this.theBox.getX(), (int)this.theBox.getY(), (int)this.theBox.getWidth(), aHeight);
    }

    public void setWidth(int aWidth) {
        this.theBox.setBounds((int)this.theBox.getX(), (int)this.theBox.getY(), aWidth, (int)this.theBox.getHeight());
    }

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

    @Override
    public int getX() {
        return (int)this.theBox.getX();
    }

    @Override
    public int getY() {
        return (int)this.theBox.getY();
    }

    /**
	 * Sets the position of this Hitbox
	 * @param x The new x Position
	 * @param y The new y Position
	 */
    @Override
	public void setPosition(int x, int y) {
		this.theBox.setLocation(x, y);
	}

    @Override
    public void setX(int aX) {
        this.theBox.setLocation(aX, (int)this.theBox.getY());
    }

    @Override
    public void setY(int aY) {
        this.theBox.setLocation((int)this.theBox.getX(), aY);
    }
}
