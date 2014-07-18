package model;

/**
 * Created by LionC on 18.07.2014.
 */
public interface Collidable {
    /**
     * Get the Hitbox of this Collidable
     * @return
     */
    public Hitbox getHitbox();

    /**
     * Method that gets called upon Collision
     * @param partner Object this Collidable collided with
     * @param partnerCategory Category the collision-partner was registered under at the HitboxManager
     */
    public void onCollide(Collidable partner, String partnerCategory);
}
