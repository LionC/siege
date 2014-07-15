package ui;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import graphics.AnimatedSprite;
import graphics.Sprite;

import javax.sound.sampled.*;
import javax.swing.JFrame;

import sound.Sound;
import ui.elements.Box;
import ui.elements.Label;


@SuppressWarnings({ "serial", "unused" })
public class Main extends JFrame {
	private static int FPS = 40;
    private static int FPS_WAIT = 1000 / FPS;

    private static int WINDOW_WIDTH = 640;
	private static int WINDOW_HEIGHT = 480;
	
	private Board board;
	
	public Main() {
		this.board = new Board();
		this.add(board);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setTitle("FrankerZ");
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void waitBusy(long milis) {
		if(milis > 0) {
			long start = System.currentTimeMillis();
		
			while(System.currentTimeMillis() < (start + milis))
				Thread.yield();
		}
	}
	
	public static void main(String[] args) {
        Main application = new Main();
		Sprite dogFace = null;
		
		Sound.loadSound("D:/Tools/eclipse/Workspace/FightingEngine/src/sound/wav/affirmative.wav", "affirmative");
		Sound.loadSound("D:/Tools/eclipse/Workspace/FightingEngine/src/sound/wav/gooddaytodie.wav", "die");

		try {
			Sprite.setDefaultBoard(application.board);
			dogFace = new Sprite("src/ui/img/frankerz.jpg",200,200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Label text;
		
		application.board.addDrawable(text = new Label("FPS: OVER 9000"));
		application.board.addDrawable(new Box(150,150,200,200));

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
	}
}
