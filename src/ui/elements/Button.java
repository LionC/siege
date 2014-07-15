package ui.elements;

import java.awt.*;

/**
 * Created by LionC on 15.07.2014.
 */
public class Button extends Box {
    Label label;
    boolean labelNeedsToBeCentered = true;

    public Button(int aX, int aY, int aWidth, int aHeight, String aLabel) {
        super(aX, aY, aWidth, aHeight);

        label = new Label(aLabel);
    }

    protected void centerLabel(Graphics2D g) {
        int posX = 0, posY = 0;

        int textHeight = g.getFontMetrics().getAscent();
        posY = this.getY() + (textHeight + this.getHeight()) / 2;

        int textWidth = g.getFontMetrics().stringWidth(this.label.getText());
        posX = this.getX() + (textWidth + this.getWidth()) / 2;

        this.label.setPosition(posX, posY);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        this.label.draw(g);
    }
}
