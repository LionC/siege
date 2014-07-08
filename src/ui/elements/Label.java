package ui.elements;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.Drawable;

/**
 * A text that is shown on the screen
 * @author LionC
 */
public class Label implements Drawable {
	private String text = "";
	private int x = 0, y = 0;
	private Color color = Color.WHITE;
	
	/**
	 * Constructs a new ScrenText at the default position 0/0
	 * @param aText The text that the Label should show
	 */
	public Label(String aText) {
		this.text = aText;
	}
	
	/**
	 * Constructs a new ScrenText at a given position
	 * @param aText The text that the Label should show
	 * @param aX The x-position for the Label
	 * @param aY The y-position for the Label
	 */
	public Label(String aText, int aX, int aY) {
		this.text = aText;
		this.x = aX;
		this.y = aY;
	}
	
	@Override
	public void draw(Graphics2D g) {
		Color old = g.getColor();
		g.setColor(this.color);
		
		g.drawString(this.text, this.x, g.getFontMetrics().getAscent() + this.y);
		
		g.setColor(old);
	}

	public String getText() {
		return this.text;
	}

	public void setText(String aText) {
		this.text = aText;
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
