package demo;

import com.sun.javafx.geom.Vec2d;
import graphics.Drawable;
import graphics.Sprite;
import model.Actor;
import model.Collidable;
import model.Game;
import model.Hitbox;
import ui.Board;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DogFace implements Actor, Collidable, Drawable {
    Board input;
    Sprite sprite;
    Hitbox hitbox;
    Game game;

    int direction = 1;
    int frame = 1;

    double movX;
    double movY;
    int collisions = 0;

    public DogFace(Board board, Game game) {
        this.game = game;
        input = board;
        try {
            sprite = new Sprite("src/demo/img/frankerz.png",200,200, board);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.movX = 0;
        this.movY = 0;

        this.hitbox = new Hitbox(200,200,50,50);

        this.direction = (int)(Math.random() * 4) + 1;
    }

    public void move(int dx, int dy) {
        this.sprite.move(dx,dy);
        this.hitbox.move(dx,dy);
    }

    public void setPosition(int x, int y) {
        this.sprite.setPosition(x,y);
        this.hitbox.setPosition(x,y);
    }

    @Override
    public void update() {
        frame = (frame % 30) + 1;

        movY += 1;
        if(frame == 30)
            this.direction = (int)(Math.random() * 4) + 1;

        int x = this.sprite.getX();
        int y = this.sprite.getY();


        if(x < -49) {
            x = 800;
        }
        if(x > 849) {
            x = -49;
        }
        if(y > 550) {
            y = 550;
            movY = - movY * (Math.random() * 0.2 + 0.8);
        }

        x = (int) ( (double) x + movX);
        y = (int) ( (double) y + movY);

        this.setPosition(x, y);

    }

    public double getMovX() {
        return movX;
    }

    public void setMovX(double movX) {
        this.movX = movX;
    }

    public double getMovY() {
        return movY;
    }

    public void setMovY(double movY) {
        this.movY = movY;
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    @Override
    public void onCollision(Collidable partner, String partnerCategory) {
        if(partnerCategory.equals("Dogs")) {
            collisions++;
            for(int i = 0; i < Math.random() * 10; i++) {
                DogCollisionParticle particle = new DogCollisionParticle(this.game, this.sprite.getX(), this.sprite.getY());
                game.addActor(particle);
                game.getBoard().add(particle);
                game.getCollisionChecker().add(particle, "Particle");
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawString("" + collisions, this.sprite.getX(), this.sprite.getY());
    }
}
