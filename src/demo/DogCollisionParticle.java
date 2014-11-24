package demo;

import graphics.Drawable;
import model.Actor;
import model.Collidable;
import model.Game;
import model.Hitbox;

import java.awt.*;
import java.util.Random;

/**
 * Created by LionC on 07.11.2014.
 */
public class DogCollisionParticle implements Actor, Drawable {


    int x, y;
    double movX, movY;

    double size = 0;
    Game game;

    int ttl;
    boolean create = false;

    public DogCollisionParticle(Game game, int x, int y) {
        Random random = new Random();
        this.game = game;
        this.x = x + (int) (random.nextDouble() * 40 - 20);
        this.y = y + (int) (random.nextDouble() * 40 - 20);
        this.movX = random.nextDouble() * 5 - 2.5;
        this.movY = random.nextDouble() * 5 - 2.5;
        this.ttl = (int) (Math.abs(random.nextGaussian()) * 20);
        this.size = Math.abs(random.nextGaussian() * 2);

    }

    @Override
    public void update() {
        movY += size / 2;
        ttl--;
        if(ttl < 0) {
            game.removeActor(this);
            game.getBoard().remove(this);
        }


        if(x < -49) {
            x = 800;
        }
        if(x > 849) {
            x = -49;
        }
        if(y > 550) {
            y = 550 - (y - 550);
            movY = - movY * 0.6;
        }

        if(create) {
            create = false;
            for(int i = 0; i < Math.random() * 1; i++) {
                DogCollisionParticle particle = new DogCollisionParticle(this.game, x, y);
                game.addActor(particle);
                game.getBoard().add(particle);
            }
        }



        x = (int) ((double) x + movX);
        y = (int) ((double) y + movY);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval(x,y, 1 + (int) size, 1 + (int) size);
    }

}
