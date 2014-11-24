package ui;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import demo.ClickParticler;
import demo.DogFace;
import demo.FrameCounter;
import graphics.AnimatedSprite;
import graphics.Sprite;

import javax.sound.sampled.*;
import javax.swing.*;

import model.Game;
import sound.Sound;
import ui.elements.Box;
import ui.elements.Button;
import ui.elements.Label;


@SuppressWarnings({ "serial", "unused" })
public class Main {
	private static int FPS = 30;
    private static int FPS_WAIT = 1000 / FPS;

    private static int WINDOW_WIDTH = 640;
	private static int WINDOW_HEIGHT = 480;
	
    private Game game;
	
	public Main() {
        this.game = new Game();
	}
	
	public Board getBoard() {
		return this.game.getBoard();
	}
	
	public void waitBusy(long milis) {
		if(milis > 0) {
			long start = System.currentTimeMillis();
		
			while(System.currentTimeMillis() < (start + milis))
				Thread.yield();
		}
	}
	
	public static void main(String[] args) {
        Game game = new Game();

        try {
            game.getBoard().add(new AnimatedSprite("src/demo/img/particle.gif",20,20,game.getBoard()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        for(int i = 0; i < 5; i++) {

            DogFace franker = new DogFace(game.getBoard(), game);

            franker.setMovX(Math.random() * 20 - 10);
            franker.setMovY(Math.random() * 20 - 10);
            franker.setPosition((int) (Math.random() * 800), (int) (Math.random() * 600));
            game.addActor(franker);
            game.getBoard().add(franker);
            game.getCollisionChecker().add(franker, "Dogs");
        }

        game.getBoard().add(new FrameCounter(600, 45));

        game.addActor(new ClickParticler(game));

        game.getCollisionChecker().addCategoryPair("Dogs","Dogs");
        game.getCollisionChecker().addCategoryPair("Particle","Particle");

        Button but = new Button(200,20,50,35,"Test", game.getBoard());
        but.setAction( () -> System.out.println("Lalala") );
        game.addActor(but);
        game.getBoard().add(but);

        game.run();
	}
}
