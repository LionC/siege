package ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import graphics.Drawable;
import graphics.Sprite;

@SuppressWarnings("serial")
public class Board extends JPanel {
	InputManager inputManager;
	MouseManager mouseManager;
	
	//The default drawing color
	Color color = Color.WHITE;
	
	List<Drawable> sprites = new ArrayList<>();
	
	public Board() {
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.inputManager = new InputManager();
		this.mouseManager = new MouseManager();
		this.addKeyListener(this.inputManager);
		this.addMouseListener(this.mouseManager);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(color);
		
		for(Drawable act : sprites) {
			act.draw(g2d);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	/**
	 * Waits for a given amount of milliseconds.
	 * @param millis The amount of milliseconds to be waited
	 */
	public void sleep(long millis) {
		if(millis > 0) {
			long start = System.currentTimeMillis();
			
			while(System.currentTimeMillis() < start + millis)
				Thread.yield();
		}
		else {
			throw new IllegalArgumentException("Tried to wait for a negative or zero amount of milliseconds.");
		}
	}
	
	/**
	 * Sets the background-color of this {@link Board}
	 * @param color The new background-color
	 */
	public void setBackgroundColor(Color color) {
		this.setBackground(color);
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets the default drawing {@link Color}
	 * @param aColor The new default drawing color
	 */
	public void setColor(Color aColor) {
		this.color = aColor;
	}

	//Private class to handle KeyBoard inputs
	private class InputManager extends KeyAdapter {
		private static final int MAX_KEY_INDEX = 228;
		
		private boolean lastKeys[] = new boolean[MAX_KEY_INDEX];
		private boolean keys[] = new boolean[MAX_KEY_INDEX];
		
		public InputManager() {
			for(int i = 0; i < MAX_KEY_INDEX; i++) {
				lastKeys[i] = false;
				keys[i] = false;
			}
		}
		
		public void keyPressed(KeyEvent e) {
			this.keys[e.getKeyCode()] = true;
		}
		
		public void keyReleased(KeyEvent e) {
			this.keys[e.getKeyCode()] = false;
		}
		
		public void update() {
			for(int i = 0; i < MAX_KEY_INDEX; i++) {
				lastKeys[i] = keys[i];
			}
		}
	}
	
	//Private class to handle Mouse inputs
	private class MouseManager extends MouseAdapter {
		private boolean lastKeys[] = new boolean[3];
		private boolean keys[] = new boolean[3];
		
		public MouseManager() {
			for(int i = 0; i < 3; i++) {
				lastKeys[i] = false;
				keys[i] = false;
			}
		}
		
		public void mousePressed(MouseEvent e) {
			this.keys[e.getButton()-1] = true;
		}
		
		public void mouseReleased(MouseEvent e) {
			this.keys[e.getButton()-1] = false;
		}
		
		public void update() {
			for(int i = 0; i < 3; i++) {
				lastKeys[i] = keys[i];
			}
		}
	}
	
	/**
	 * Adds a {@link Drawable} to this {@link Board}
	 * @param drawable The {@link Drawable} to be added
	 */
	public void addDrawable(Drawable drawable) {
		this.sprites.add(drawable);
	}
	
	/**
	 * Removes a {@link Drawable} from this {@link Board}
	 * @param drawable
	 */
	public void removeDrawable(Drawable drawable) {
		this.sprites.remove(drawable);
	}
	
	/**
	 * Adds a {@link Sprite} to this {@link Board}
	 * @param sprite The {@link Sprite} to be added
	 */
	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
	}
	
	/**
	 * Removes a {@link Sprite} from this {@link Board}
	 * @param sprite The {@link Sprite} to be removed
	 */
	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}
	
	/**
	 * Returns whether a given key is down or not
	 * @param keyId The Id of the key, for a reference of the Ids look for the class KeyEvent
	 * @return True if key is down, false if not
	 */
	public boolean keyDown(int keyId) {
		return this.inputManager.keys[keyId];
	}
	
	/**
	 * Returns whether a given key has just pressed or not
	 * @param keyId The Id of the key, for a reference of the Ids look for the class KeyEvent
	 * @return True if key has just been pressed, false if not
	 */
	public boolean keyPressed(int keyId) {
		return this.inputManager.keys[keyId] && !this.inputManager.lastKeys[keyId];
	}
	
	/**
	 * Returns whether a given mouse-key was just pressed or not
	 * @param keyId The Id of the mouse-key that should be checked, 1 and 2 are normally LMB AND RMB
	 * @return True if the mouse-key is down, false if not
	 */
	public boolean mouseKeyDown(int keyId) {
		return this.mouseManager.keys[keyId-1];
	}
	
	/**
	 * Returns whether a given mouse-key has just been pressed or not
	 * @param keyId The Id of the mouse-key that should be checked, 1 and 2 are normally LMB AND RMB
	 * @return True if the mouse-key has just been pressed, false if not
	 */
	public boolean mouseKeyPressed(int keyId) {
		return this.mouseManager.keys[keyId-1] && !this.mouseManager.lastKeys[keyId -1];
	}
	
	/**
	 * Gets the x-coordinate of the mouse (relative to the window)
	 * @return The x-coordinate of the mouse pointer
	 */
	public int getMouseX() {
		return getMousePosition().x;
	}
	
	/**
	 * Gets the y-coordinate of the mouse (relative to the window)
	 * @return The y-coordinate of the mouse pointer
	 */
	public int getMouseY() {
		return getMousePosition().y;
	}
}
