package demo;

import model.Game;
import ui.elements.Label;

import java.awt.*;

public class FrameCounter extends Label {
    int frame = 1;
    long last = System.currentTimeMillis();
    Game game;

    public FrameCounter(int x, int y, Game aGame) {
        super("" + 1, x, y);

        game = aGame;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        this.frame = (frame % this.game.getFps()) + 1;
        this.setText("Frame: " + frame);
    }
}
