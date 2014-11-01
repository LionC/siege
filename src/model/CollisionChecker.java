package model;

import utility.Pair;

import java.util.*;

/**
 * Class to provide a Manager to check Collisions
 */
public class CollisionChecker {
    protected HashMap<String, List<Collidable>> collidables = new HashMap<>();
    protected Set<Pair<String,String>> combinations = new LinkedHashSet<>();

    /**
     * Registers a Collidable ina  given category to be checked
     *
     * @param collidable Collidable to be added
     * @param category Category the Collidable should be added to
     */
    public void add(Collidable collidable, String category) {
        if(!this.collidables.containsKey(category))
            this.collidables.put(category, new LinkedList<Collidable>());

        this.collidables.get(category).add(collidable);
    }

    /**
     * Registers a Pair of categories to be checked
     *
     * @param aPair Pair of categories
     */
    public void addCategoryPair(Pair<String, String> aPair) {
        combinations.add(aPair);
    }

    /**
     * Registers a Pair of categories to be checked
     *
     * @param aFirst First category
     * @param aSecond Second category
     */
    public void addCategoryPair(String aFirst, String aSecond) {
        this.addCategoryPair(new Pair<String, String>(aFirst, aSecond));
    }

    /**
     * Removes a pair of categories from the checked pairs of categories
     *
     * @param aPair Par of categories
     */
    public void removeCategoryPair(Pair<String, String> aPair) {
        combinations.remove(aPair);
    }

    /**
     * Removes a pair of categories from the checked pairs of categories
     *
     * @param aFirst First category
     * @param aSecond Second category
     */
    public void removeCategoryPair(String aFirst, String aSecond) {
        this.removeCategoryPair(new Pair<String, String>(aFirst, aSecond));
    }

    public void checkCollisions() {
        String[] keys = (String[]) this.collidables.keySet().toArray();

        for(Pair<String, String> act : this.combinations) {
            for(Collidable first : this.collidables.get(act.getFirst())) {
                for(Collidable second : this.collidables.get(act.getSecond())) {
                    if(first != second && first.getHitbox().collides(second.getHitbox())) {
                        first.onCollision(second, act.getFirst());
                        second.onCollision(first, act.getSecond());
                    }
                }
            }
        }
    }
}
