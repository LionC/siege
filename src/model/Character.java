package model;

import graphics.Sprite;

/**
 * A Character
 * @author LionC
 *
 */
public class Character {
	private final int JUMPSPEED = 5;
	
	@SuppressWarnings("unused")
	private Hitbox hitbox;
	private int health;
	private int moveSpeed;
	private int jumpHeight;
	private Sprite sprite;
	private boolean jumping = false;
	private boolean falling = false;
	private int jumpMaximum = 0;
	
	public Character(String file, int height, int width, int move, int jump) throws Exception {
		this.sprite = new Sprite(file, -100, -100);
		this.moveSpeed = move;
		this.jumpHeight = jump;
		this.hitbox = new Hitbox(-100, -100, width, height);
	}
	
	public void update() {
		if(this.jumping) {
			this.move(0,JUMPSPEED);
			
			if(this.sprite.getY() >= this.jumpMaximum) {
				this.jumping = false;
				this.falling = true;
			}
		}
		else if(this.falling) {
			this.move(0,-JUMPSPEED);
			
			if(this.sprite.getY() >= this.jumpMaximum) {
				this.jumping = false;
				this.falling = true;
			}
		}
	}
	
	public void move(int dx, int dy) {
		this.sprite.move(dx, dy);
		this.hitbox.move(dx, dy);
	}
	
	public void stepLeft() {
		this.move(-this.moveSpeed, 0);
	}
	
	public void stepRight() {
		this.move(this.moveSpeed, 0);
	}
	
	public void jump() {
		if(this.jumping || this.falling) {
			return;
		}
		
		this.jumping = true;
		this.jumpMaximum = this.sprite.getY() + this.jumpHeight;
	}
	
	public void setPosition(int x, int y) {
		this.sprite.setPosition(x, y);
		this.hitbox.setPosition(x, y);
	}
	
	public Hitbox getHitbox() {
		return this.hitbox;
	}
}
