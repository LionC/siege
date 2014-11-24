package model;

import graphics.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Drawer {
    protected List<Drawable> drawables = new ArrayList<>();

    /**
     * Adds a Drawable to this Manager to be rendered by it
     *
     * @param aDrawable Drawable to be added
     */
    public void add(Drawable aDrawable) {
        this.drawables.add(aDrawable);
    }

    /**
     * Removes a Drawable from this Manager
     *
     * @param aDrawable Drawable to be removed
     */
    public void remove(Drawable aDrawable) {
        this.drawables.remove(aDrawable);
    }

    /**
     * Removes all Drawables from this Drawers
     */
    public void clear() {
        this.drawables.clear();
    }

    /**
     * Renders all managed Drawables on the given Graphics-Context
     * @param g Graphics Context to render on
     */
    public void render(Graphics2D g) {
        for(Drawable act : this.drawables) {
            act.draw(g);
        }
    }
}
