package ui.elements;

import graphics.Drawable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LionC on 17.07.2014.
 */
public class DrawableGroup implements Drawable {
    List<Drawable> drawables = new LinkedList<>();

    /**
     * Adds a given Drawable to this DrawableGroup
     * @param aDrawable
     */
    public void add(Drawable aDrawable) {
        this.drawables.add(aDrawable);
    }

    /**
     * Removes a given Drawable from this DrawableGroup
     * @param aDrawable
     */
    public void remove(Drawable aDrawable) {
        this.drawables.remove(aDrawable);
    }

    /**
     * Get all Drawables in this DrawableGroup
     * @return all Drawables in this DrawableGroup
     */
    public List<Drawable> getAll() {
        return this.drawables;
    }

    @Override
    public void draw(Graphics2D g) {
        this.drawables.forEach(drawable -> drawable.draw(g));
    }
}
