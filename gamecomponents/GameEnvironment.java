// ID: 318324563
package gamecomponents;
import geometryprimitives.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class about a collection of objects that the ball can collide with during the game.
 */
public class GameEnvironment {
    private List<Collidable> listOfCollidables;

    /**
     * constructor.
     * @param listOfCollidables is a list of collidable objects that can collide with the ball during the game.
     */
    public GameEnvironment(List<Collidable> listOfCollidables) {
        this.listOfCollidables = listOfCollidables;
    }

    /**
     * add the given collidable to the environment.
     * @param c is a Collidable object
     */
    public void addCollidable(Collidable c) {
        this.listOfCollidables.add(c);
    }

    /**
     * Get the closest collision information that the object is going to collide with.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory of the object
     * @return the information about the closest collision that is going to occur. If this object will
     *         not collide with any of the collidables in this collection, return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // create a list of the potential collisions the ball can collide with in his trajectory.
        List<CollisionInfo> potentialCollisions = new ArrayList<>();
        // pass through the collidables to get a list of closets points that the ball collide with them.
        for (Collidable c :this.listOfCollidables) {
            // check if the ball does not collide with the current collidable.
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) == null) {
                continue;
            } else {
                // save the closest collision between the ball and the current collidable.
                potentialCollisions.add(new CollisionInfo(
                    trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()), c));
            }
        }
        // check if the ball doesn't collide with any collidables. if so, return null
        if (potentialCollisions.isEmpty()) {
            return null;
        } else {
            // return the closest Collision info that the ball is collided with
            return trajectory.closestCollisionInfoToStartOfLine(potentialCollisions);
        }
    }
    /**
     * add the given collidable to the environment.
     * @param c is a Collidable object
     */
    public void removeCollidable(Collidable c) {
        this.listOfCollidables.remove(c);
    }
}