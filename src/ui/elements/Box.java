package ui.elements;

import java.awt.Graphics2D;

import java.awt.Color;

import graphics.Drawable;

/**
 * 
 * @author LionC
 */
public class Box implements Drawable {
	private int x, y, height, width;
	private int border = 1;
	private boolean filled = false;
	private Color color = Color.WHITE;
	
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
			g.fillRect(this.x, this.y, this.width, this.height);
		}
		else {
			for(int i = 0; i < border; i++) {
				g.drawRect(this.x + i, this.y + i, this.width - 2*i, this.height - 2*i);
			}
		}
		
		g.setColor(old);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color aColor) {
		this.color = aColor;
	}

	public void setPosition(int aX, int aY) {
		this.x = aX;
		this.y = aY;
	}
	
	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;
	}
	
}
