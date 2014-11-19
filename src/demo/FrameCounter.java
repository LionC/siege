package demo;

import ui.elements.Label;

import java.awt.*;

/**
 * Created by LionC on 07.11.2014.
 */
public class FrameCounter extends Label {
    int frame = 1;

    public FrameCounter(int x, int y) {
        super("" + 1, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        this.frame = (frame % 30) + 1;
        this.setText("" + frame);
    }
}
