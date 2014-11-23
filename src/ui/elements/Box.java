package ui.elements;

import java.awt.Graphics2D;

import java.awt.Color;

import graphics.Drawable;
import model.PositionedDrawable;

/**
 * 
 * @author LionC
 */
public class Box implements PositionedDrawable {
	protected float x, y, height, width;
	protected int border = 1;
	protected boolean filled = false;
	protected Color color = Color.WHITE;
	
	/**
	 * Constructs a new Box, which is by default not filled with a 1-pixel-border
	 * @param aX The x-position of the Box
	 * @param aY The y-position of the Box
	 * @param aWidth The width of the Box
	 * @param aHeight The height of the Box
	 */
	public Box(int aX, int aY, int aWidth, int aHeight) {
		this.x = aX;
		this.y = aY;
		this.width = aWidth;
		this.height = aHeight;
	}
	
	@Override
	public void draw(Graphics2D g) {
		Color old = g.getColor();
		g.setColor(this.color);
		
		if(filled) {
			g.fillRect((int)this.x, (int)this.y, (int)this.width, (int)this.height);
		}
		else {
			for(int i = 0; i < border; i++) {
				g.drawRect((int)this.x + i, (int)this.y + i, (int)this.width - 2*i, (int)this.height - 2*i);
			}
		}
		
		g.setColor(old);
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

    public float getHeight() { return  this.height; }

    public float getWidth() { return  this.width; }
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color aColor) {
		this.color = aColor;
	}

	public void setPosition(float aX, float aY) {
		this.x = aX;
		this.y = aY;
	}

    @Override
    public void setX(float aX) {
        this.x = aX;
    }

    @Override
    public void setY(float aY) {
        this.y = aY;
    }

    public void move(float dX, float dY) {
		this.x += dX;
		this.y += dY;
	}
	
}
