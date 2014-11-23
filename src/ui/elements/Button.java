package ui.elements;

import model.Actor;
import ui.Board;

import java.awt.*;
import java.util.Optional;

public class Button extends Box implements Actor {
    Label label;
    Runnable action = null;
    Board board = null;

    boolean labelNeedsToBeCentered = true;

    public Button(int aX, int aY, int aWidth, int aHeight, String aLabel) {
        super(aX, aY, aWidth, aHeight);

        this.label = new Label(aLabel);
    }

    protected boolean wasClicked() {
        if(this.board != null) {
            int mX = this.board.getMouseX(), mY = this.board.getMouseY();

            return board.mouseKeyPressed(1) && mX >= this.getX() && mX <= this.getX()+this.getWidth() && mY >= this.getY() && mY <= this.getY()+this.getHeight();
        }
        else {
            return false;
        }
    }

    protected void onClick() {
        if(this.action != null)
            if(this.wasClicked())
                this.action.run();
    }

    protected void centerLabel(Graphics2D g) {
        float posX = 0, posY = 0;

        int textHeight = g.getFontMetrics().getAscent();
        posY = this.getY() + (textHeight + this.getHeight()) / 2;

        int textWidth = g.getFontMetrics().stringWidth(this.label.getText());
        posX = this.getX() + (textWidth + this.getWidth()) / 2;

        this.label.setPosition(posX, posY);
    }

    public void setAction(Runnable aAction) {
        this.action = aAction;
    }

    public void setBoard(Board aBoard) {
        this.board = aBoard;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        this.label.draw(g);
    }

    @Override
    public void update() {
        this.onClick();
    }
}
