package ui;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import graphics.Drawable;
import graphics.Sprite;
import model.Drawer;

@SuppressWarnings("serial")
public class Board {
	InputManager inputManager;
	MouseManager mouseManager;

    JPanel panel;
    JFrame frame;

    Drawer drawer = new Drawer();
	
	//The default drawing color
	Color color = Color.WHITE;
	
	List<Drawable> sprites = new ArrayList<>();
	
	public Board() {
		this.panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(color);

                drawer.render(g2d);

                Toolkit.getDefaultToolkit().sync();
                g.dispose();
            }
        };

        this.panel.setBackground(Color.BLACK);
		this.panel.setDoubleBuffered(true);
		this.panel.setFocusable(true);
		this.inputManager = new InputManager();
		this.mouseManager = new MouseManager();
		this.panel.addKeyListener(this.inputManager);
		this.panel.addMouseListener(this.mouseManager);

        this.frame = new JFrame();
        this.frame.setContentPane(this.panel);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(800,600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setTitle("FrankerZ");
        this.frame.setResizable(false);
        this.frame.setVisible(true);
	}

    public void update() {
        this.panel.repaint();

        this.inputManager.update();
        this.mouseManager.update();
    }
	
	/**
	 * Sets the background-color of this {@link Board}
	 * @param color The new background-color
	 */
	public void setBackgroundColor(Color color) {
		this.panel.setBackground(color);
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

        //The parts of the array where the differnet information (key is down, key was pressed, key was released)
        private static final int DOWN_INDEX = 0;
        private static final int PRESSED_INDEX = MAX_KEY_INDEX;
        private static final int RELEASED_INDEX = MAX_KEY_INDEX*2;

        private int lastKeys = 0;
        private int actKeys = 1;

        private boolean keys[][] = new boolean[2][MAX_KEY_INDEX*3];

		public InputManager() {
			//Initialize everything with false
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < keys[i].length; j++) {
                    keys[i][j] = false;
                }
            }
		}

        @Override
		public void keyPressed(KeyEvent e) {
            //Check if the key is down to prevent repeat presses from the OS
            if(this.keys[lastKeys][DOWN_INDEX + e.getKeyCode()])
                return;

            this.keys[lastKeys][DOWN_INDEX + e.getKeyCode()] = true;
            this.keys[lastKeys][PRESSED_INDEX + e.getKeyCode()] = true;
		}

        @Override
		public void keyReleased(KeyEvent e) {
            this.keys[lastKeys][DOWN_INDEX + e.getKeyCode()] = false;
            this.keys[lastKeys][RELEASED_INDEX + e.getKeyCode()] = true;
		}

        public boolean keyDown(int keyIndex) {
            return keys[actKeys][DOWN_INDEX + keyIndex];
        }

        public boolean keyUp(int keyIndex) {
            return !keyDown(keyIndex);
        }

        public boolean keyPressed(int keyIndex) {
            return keys[actKeys][PRESSED_INDEX + keyIndex];
        }

        public boolean keyReleased(int keyIndex) {
            return keys[actKeys][RELEASED_INDEX + keyIndex];
        }

		public void update() {
            //"Copy" lastkeys to actKeys
            int temp = actKeys;
            actKeys = lastKeys;
            lastKeys = temp;

            //Reset released and pressed arrays
            for(int i = 0; i < MAX_KEY_INDEX; i++) {
                keys[lastKeys][DOWN_INDEX + i] = keys[actKeys][DOWN_INDEX + i];
                keys[lastKeys][PRESSED_INDEX + i] = false;
                keys[lastKeys][RELEASED_INDEX + i] = false;
            }
		}

        public void printDownKeys() {
            for(int i = 0; i < MAX_KEY_INDEX; i++) {
                if(keys[actKeys][DOWN_INDEX + i]) {
                    System.out.print(i + " ");
                }
            }

            System.out.println();
        }
	}
	
	//Private class to handle Mouse inputs
	private class MouseManager extends MouseAdapter {
        private static final int MAX_KEY_INDEX = 3;

        //The parts of the array where the differnet information (key is down, key was pressed, key was released)
        private static final int DOWN_INDEX = 0;
        private static final int PRESSED_INDEX = MAX_KEY_INDEX;
        private static final int RELEASED_INDEX = MAX_KEY_INDEX*2;

        private int lastKeys = 0;
        private int actKeys = 1;

        private boolean keys[][] = new boolean[2][MAX_KEY_INDEX*3];

        public MouseManager() {
            //Initialize everything with false
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < keys[i].length; j++) {
                    keys[i][j] = false;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.keys[lastKeys][DOWN_INDEX + e.getButton() - 1] = true;
            this.keys[lastKeys][PRESSED_INDEX + e.getButton()  - 1] = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.keys[lastKeys][DOWN_INDEX + e.getButton() - 1] = false;
            this.keys[lastKeys][RELEASED_INDEX + e.getButton() - 1] = true;
        }

        public boolean mouseKeyDown(int keyIndex) {
            return keys[actKeys][DOWN_INDEX + keyIndex - 1];
        }

        public boolean mouseKeyUp(int keyIndex) {
            return !mouseKeyDown(keyIndex);
        }

        public boolean mouseKeyPressed(int keyIndex) {
            return keys[actKeys][PRESSED_INDEX + keyIndex - 1];
        }

        public boolean mouseKeyReleased(int keyIndex) {
            return keys[actKeys][RELEASED_INDEX + keyIndex - 1];
        }

        public void update() {
            //"Copy" lastkeys to actKeys
            int temp = actKeys;
            actKeys = lastKeys;
            lastKeys = temp;

            //Reset released and pressed arrays
            for(int i = 0; i < MAX_KEY_INDEX; i++) {
                keys[lastKeys][DOWN_INDEX + i] = keys[actKeys][DOWN_INDEX + i];
                keys[lastKeys][PRESSED_INDEX + i] = false;
                keys[lastKeys][RELEASED_INDEX + i] = false;
            }
        }
	}
	
	/**
	 * Adds a {@link Drawable} to this {@link Board}
	 * @param drawable The {@link Drawable} to be added
	 */
	public void add(Drawable drawable) {
		this.drawer.add(drawable);
	}
	
	/**
	 * Removes a {@link Drawable} from this {@link Board}
	 * @param drawable
	 */
	public void remove(Drawable drawable) {
		this.drawer.remove(drawable);
	}

    /**
     * Remove all Drawables from this Board
     */
    public void clear() {
        this.drawer.clear();
    }

	/**
	 * Returns whether a given key is down or not
	 * @param keyId The Id of the key, for a reference of the Ids look for the class KeyEvent
	 * @return True if key is down, false if not
	 */
	public boolean keyDown(int keyId) {
		return this.inputManager.keyDown(keyId);
	}

    /**
     * Returns whether a given key is up or not
     * @param keyId The Id of the key, for a reference of the Ids look for the class KeyEvent
     * @return True if key is up, false if not
     */
    public boolean keyUp(int keyId) { return this.inputManager.keyDown(keyId); }

	/**
	 * Returns whether a given key has just been pressed or not
	 * @param keyId The Id of the key, for a reference of the Ids look for the class KeyEvent
	 * @return True if key has just been pressed, false if not
	 */
	public boolean keyPressed(int keyId) {
		return this.inputManager.keyPressed(keyId);
	}

    /**
     * Returns whether a given key has just been released or not
     * @param keyId The Id of the key, for a reference of the Ids look for the class KeyEvent
     * @return True if key has just been released, false if not
     */
    public boolean keyReleased(int keyId) {
        return this.inputManager.keyReleased(keyId);
    }

    public void printDownKeys() { this.inputManager.printDownKeys(); }

    /**
	 * Returns whether a given mouse-key is down or not
	 * @param keyId The Id of the mouse-key that should be checked, 1 and 2 are normally LMB AND RMB
	 * @return True if the mouse-key is down, false if not
	 */
	public boolean mouseKeyDown(int keyId) {
		return this.mouseManager.mouseKeyDown(keyId);
	}

    /**
     * Returns whether a given mouse-key is up or not
     * @param keyId The Id of the mouse-key that should be checked, 1 and 2 are normally LMB AND RMB
     * @return True if the mouse-key is up, false if not
     */
    public boolean mouseKeyUp(int keyId) {
        return this.mouseManager.mouseKeyUp(keyId);
    }
	
	/**
	 * Returns whether a given mouse-key has just been pressed or not
	 * @param keyId The Id of the mouse-key that should be checked, 1 and 2 are normally LMB AND RMB
	 * @return True if the mouse-key has just been pressed, false if not
	 */
	public boolean mouseKeyPressed(int keyId) { return this.mouseManager.mouseKeyPressed(keyId); }

    /**
     * Returns whether a given mouse-key has just been released or not
     * @param keyId The Id of the mouse-key that should be checked, 1 and 2 are normally LMB AND RMB
     * @return True if the mouse-key has just been released, false if not
     */
    public boolean mouseKeyReleased(int keyId) { return this.mouseManager.mouseKeyReleased(keyId); }

    /**
	 * Gets the x-coordinate of the mouse (relative to the window)
	 * @return The x-coordinate of the mouse pointer, -1 if the mouse pointer is out of the window
	 */
	public int getMouseX() {
		Point pos = this.panel.getMousePosition();

        if(pos != null)
            return pos.x;
        else
            return -1;
	}
	
	/**
	 * Gets the y-coordinate of the mouse (relative to the window)
	 * @return The y-coordinate of the mouse pointer, -1 if the mouse pointer is out of the window
	 */
	public int getMouseY() {
        Point pos = this.panel.getMousePosition();

        if(pos != null)
            return pos.y;
        else
            return -1;
	}
}
