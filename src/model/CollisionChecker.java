package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by LionC on 18.07.2014.
 */
public class CollisionChecker {
    protected HashMap<String, List<Collidable>> collidables = new HashMap<>();

    public void add(Collidable collidable, String category) {
        if(!this.collidables.containsKey(category))
            this.collidables.put(category, new LinkedList<Collidable>());

        this.collidables.get(category).add(collidable);
    }

    public void checkCollisions() {
        String[] keys = (String[]) this.collidables.keySet().toArray();

        //For each category i...
        for(int i = 0; i < keys.length - 1; i++) {
            //Check all categories j with a higher index...
            for(int j = i + 1; i < keys.length; i++) {
                //...for collisions between i and j and trigger the onCollide-Methods accordingly
                for(Collidable first : this.collidables.get(keys[i])) {
                    for(Collidable second : this.collidables.get(keys[j])) {
                        if(first.getHitbox().collides(second.getHitbox()))
                            first.onCollide(second, keys[j]);
                            second.onCollide(first, keys[i]);
                    }
                }
            }
        }
    }
}
