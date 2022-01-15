// ID: 318324563
package gamecomponents;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * This is a class that represents Blocks in the game that are Collidable.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     * @param collisionRectangle of the block.
     * @param color of the block.
     * @param hitListeners list of listeners
     */
    public Block(Rectangle collisionRectangle, Color color, List<HitListener> hitListeners) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.hitListeners = hitListeners;
    }
    /**
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * updating the velocity after hitting the collidable.
     * @param collisionPoint  that an object collided with.
     * @param currentVelocity of the object that hits the collidable.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // check if the collision point is on the top/bottom border of the block.
        // if so, a change in the vertical direction should be done.
        if (this.collisionRectangle.getUpperLeft().getY() == collisionPoint.getY()
        || this.collisionRectangle.getUpperLeft().getY() + this.collisionRectangle.getHeight()
            == collisionPoint.getY()) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
        } else {
            // call for notify hit to call all listeners
            this.notifyHit(hitter);
            // when the hit is being made in right/left border a change the horizontal direction should be done.
            return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
        }
    }

    /**
     * drawOn method draws a block on the screen.
     * @param surface draws the ball
     */
    public void drawOn(DrawSurface surface) {
        // set a color and fill the shape of the rectangle
        surface.setColor(this.color);
        surface.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
            (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
            (int) this.collisionRectangle.getHeight());
        // set a color and draw the edges of the rectangle
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
            (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
            (int) this.collisionRectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * adding the block object to the game collidables and to the game sprites.
     * @param g  is a game object
     */
    public void addToGame(GameLevel g) {
        // the Block is both  collidable and sprite
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * removing the block object from the game collidables and from the game sprites.
     * @param game  is a game object
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl is hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl is hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event to make its methods.
     * @param hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * set listeners to a block.
     * @param hitListeners1 is a list of listeners
     */
    public void setHitListeners(List<HitListener> hitListeners1) {
        this.hitListeners = hitListeners1;
    }
}
