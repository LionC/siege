package model;

public interface Positioned {
    float getX();
    float getY();
    default void setPosition(float aX, float aY) {
        this.setX(aX);
        this.setY(aY);
    }
    void setX(float aX);
    void setY(float aY);
    default void move(float dx, float dy) {
        this.setPosition(this.getX() + dx, this.getY() + dy);
    }
}
