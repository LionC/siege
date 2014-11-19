package graphics;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import ui.Board;

public class Sprite implements Drawable {
	static private Board defaultBoard;
	static private boolean defaultVisibility = true;
	
	/**
	 * Sets the default Board that is used by all Sprites per default
	 * @param board The default board
	 */
	static public void setDefaultBoard(Board board) {
		defaultBoard = board;
	}
	
	/**
	 * Sets the default visibility for all Sprites
	 * @param visibility True for visible, False for hidden
	 */
	static public void setDefaultVisibility(boolean visibility) {
		defaultVisibility = visibility;
	}
	
	protected Image image;
	private boolean visibility;
	private int x;
	private int y;
	
	/**
	 * Standard constructor using the default Board
	 * @param file Resource file path to the image
	 * @param x X Position
	 * @param y Y Position
	 * @throws Exception Could throw that the file is not found or that the default Board is not initialized
	 */
	public Sprite(String file, int x, int y) throws Exception {
        this(file,x,y,defaultBoard);
	}
	
	/**
	 * Constructor not using the default Board
	 * @param file Resource file path to the image
	 * @param x X Position
	 * @param y Y Position
	 * @param myBoard The Board this Sprite should be rendered on
	 * @throws Exception Could throw that the file is not found or that the default Board is not initialized
	 */
	public Sprite(String file, int x, int y, Board myBoard) {
		//TODO: Ist das die endgueltige Loesung?
		try {
			ImageIcon theImage = new ImageIcon(file);
			this.image = theImage.getImage();
		}
		catch (NullPointerException npe) {
			System.err.println("File \"" + file + "\" was not found.");
		}
		
		this.x = x;
		this.y = y;
		this.visibility = defaultVisibility;

		myBoard.add(this);
	}

	/**
	 * Moves this Sprite
	 * @param dx The amountof movement on the x-axis
	 * @param dy The amount of movement on the y-axis
	 */
	public void move(int dx, int dy) {
        this.x += dx;
		this.y += dy;
	}
	
	/**
	 * Sets this Sprites position
	 * @param x The new x-position
	 * @param y The new y-position
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return image;
	}
	
	/**
	 * Sets the Visibility of this Sprite
	 * @param visibility True for visible, False for hidden
	 */
	public void setVisibility(boolean visibility) {
		if(visibility)
			this.show();
		else
			this.hide();
	}
	
	/**
	 * Hides this Sprite
	 */
	public void hide() {
		this.visibility = false;
	}
	
	/**
	 * Shows this Sprite
	 */
	public void show() {
		this.visibility = true;
	}

	@Override
	public void draw(Graphics2D g) {
		if(this.visibility)
            g.drawImage(this.image, this.x, this.y, null);
	}
}