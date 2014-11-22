package model;

public interface Positioned {
    int getX();
    int getY();
    default void setPosition(int aX, int aY) {
        this.setX(aX);
        this.setY(aY);
    }
    void setX(int aX);
    void setY(int aY);
    default void move(int dx, int dy) {
        this.setPosition(this.getX() + dx, this.getY() + dy);
    }
}
