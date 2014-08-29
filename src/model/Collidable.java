package model;

public interface Collidable {
    /**
     * Get the Hitbox of this Collidable
     * @return Hitbox
     */
    public Hitbox getHitbox();

    /**
     * Method that gets called upon Collision
     * @param partner Object this Collidable collided with
     * @param partnerCategory Category the collision-partner was registered under at the HitboxManager
     */
    public void onCollision(Collidable partner, String partnerCategory);
}
