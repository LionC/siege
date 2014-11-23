package model;

import ui.elements.DrawableGroup;

import java.awt.*;

public class PositionedDrawableGroup implements PositionedDrawable {
    protected PositionedGroup positionedGroup;
    protected DrawableGroup drawableGroup = new DrawableGroup();

    public PositionedDrawableGroup(float aX, float aY) {
        positionedGroup = new PositionedGroup(aX, aY);
    }

    public PositionedDrawableGroup() {
        this(0,0);
    }

    public void add(PositionedDrawable aPositionedDrawable) {
        this.positionedGroup.add(aPositionedDrawable);
        this.drawableGroup.add(aPositionedDrawable);
    }

    public void remove(PositionedDrawable aPositionedDrawable) {
        this.positionedGroup.remove(aPositionedDrawable);
        this.drawableGroup.remove(aPositionedDrawable);
    }

    @Override
    public void draw(Graphics2D g) {
        this.drawableGroup.draw(g);
    }

    @Override
    public float getX() {
        return this.positionedGroup.getX();
    }

    @Override
    public float getY() {
        return this.positionedGroup.getY();
    }

    @Override
    public void setPosition(float aX, float aY) {
        this.positionedGroup.setPosition(aX,aY);
    }

    @Override
    public void setX(float aX) {
        this.positionedGroup.setX(aX);
    }

    @Override
    public void setY(float aY) {
        this.positionedGroup.setY(aY);
    }

    @Override
    public void move(float dx, float dy) {
        this.positionedGroup.move(dx,dy);
    }
}
