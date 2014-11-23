package model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

/**
 * Group of Positioned objects, which is itself Positioned. Changing the Group's position will cause all of the Group's members to change their position accordingly
 */
public class PositionedGroup implements Positioned {
    List<Positioned> positioneds = new ArrayList<Positioned>();
    private float x;
    private float y;

    /**
     * Creates a new PositionedGroup, setting its location to (0,0).
     * Note that only the numeric changes in the position matter, the starting position does not matter at all.
     */
    public PositionedGroup() {
        this(0,0);
    }

    /**
     * Creates a new PositionedGroup, setting its location to the given coordinates.
     * Note that only the numeric changes in the position matter, the starting position does not matter at all.
     *
     * @param aX x-coordinate
     * @param aY y-coordinate
     */
    public PositionedGroup(float aX, float aY) {
        this.x = aX;
        this.y = aY;
    }

    /**
     * Add a Positioned object to this Group
     *
     * @param aPositioned Positioned object to be added
     */
    public void add(Positioned aPositioned) {
        this.positioneds.add(aPositioned);
    }

    /**
     * Remove a Positioned object from this Group
     *
     * @param aPositioned Positioned object to be removed
     */
    public void remove(Positioned aPositioned) {
        this.positioneds.remove(aPositioned);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setPosition(float aX, float aY) {
        this.move(aX - this.getX(), aY - this.getY());
    }

    @Override
    public void setX(float aX) {
        this.move(aX - this.getX(), 0);
    }

    @Override
    public void setY(float aY) {
        this.move(0, aY - this.getY());
    }

    @Override
    public void move(float dx, float dy) {
        for(Positioned act : this.positioneds) {
            act.move(dx, dy);
        }

        this.x += dx;
        this.y += dy;
    }
}
