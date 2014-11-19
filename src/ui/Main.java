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

        game.run();

        /*
        Main application = new Main();
		Sprite dogFace = null;
		
		Sound.loadSound("D:/Tools/eclipse/Workspace/FightingEngine/src/sound/wav/affirmative.wav", "affirmative");
		Sound.loadSound("D:/Tools/eclipse/Workspace/FightingEngine/src/sound/wav/gooddaytodie.wav", "die");

		try {
			Sprite.setDefaultBoard(application.getBoard());
			dogFace = new Sprite("src/ui/img/frankerz.jpg",200,200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Label text;
		
		application.getBoard().add(text = new Label("FPS: OVER 9000"));
		application.getBoard().add(new Box(150,150,200,200));

		int speed = 2;
		long lastRender = 0;
		long cur = 0;
		long lastFrame = 0;
		
		int xpos = 0;

		application.getBoard().update();
		lastRender = System.currentTimeMillis();
		
		
		for(;;) {
			if(application.getBoard().keyDown(KeyEvent.VK_UP))
				dogFace.move(0, -speed);
			if(application.getBoard().keyDown(KeyEvent.VK_DOWN))
				dogFace.move(0, speed);
			if(application.getBoard().keyDown(KeyEvent.VK_LEFT))
				dogFace.move(-speed, 0);
			if(application.getBoard().keyDown(KeyEvent.VK_RIGHT))
				dogFace.move(speed, 0);
			if(application.getBoard().keyDown(KeyEvent.VK_W))
				text.move(0, -speed);
			if(application.getBoard().keyDown(KeyEvent.VK_S))
				text.move(0, speed);
			if(application.getBoard().keyDown(KeyEvent.VK_A))
				text.move(-speed, 0);
			if(application.getBoard().keyDown(KeyEvent.VK_D))
				text.move(speed, 0);

			if(application.getBoard().mouseKeyPressed(1))
				System.out.println(text.getX() + "\t" + text.getY());

            if(application.getBoard().keyPressed(KeyEvent.VK_1)) {
                Sound.play("affirmative");
            }

            if(application.getBoard().keyPressed(KeyEvent.VK_2))
                Sound.play("die");
			
			application.getBoard().update();
			
			cur = System.currentTimeMillis();
			application.waitBusy(FPS_WAIT - (cur - lastRender));
			lastRender = cur;
		}

         */
	}
}
