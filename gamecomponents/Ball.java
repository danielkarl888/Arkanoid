// ID: 318324563
package gamecomponents;
import biuoop.DrawSurface;
import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import java.awt.Color;

/**
 * A Ball class which has a center point, radius, color, velocity
 * and list of collidables that the ball can collide with.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity vel;
    private GameEnvironment gameEnv;

    /**
     * constructor.
     * @param center point of the ball.
     * @param r is the radius of the ball.
     * @param color of the ball.
     * @param gameEnv is the game environment of the ball (list of collidables)
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnv) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnv = gameEnv;
    }

     /**
     * constructor.
      * @param x the X param of the center point of the ball.
      * @param y the Y param of the center point of the ball.
      * @param radius of the ball.
      * @param color of the ball.
      * @param gameEnv the game environment of the ball (list of collidables)
      */
    public Ball(int x, int y, int radius, Color color, GameEnvironment gameEnv) {
        this(new Point(x, y), radius, color, gameEnv);
    }

    /**
     * @return the X param of the center point of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the Y param of the center point of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the game environment (list of collidables) of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnv;
    }

    /**
     * drawOn method draws the ball on the screen.
     * @param surface draws the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set velocity to the ball.
     * @param v is the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * set velocity to the ball using dx and dy (changes in X and Y axes separately).
     * @param dx the change of the ball in position on the X axis.
     * @param dy the change of the ball in position on the Y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball
     */
    public  Velocity getVelocity() {
        return this.vel;
    }

    /**
     * move the ball when changing his center point to other place.
     */
    public void oldMoveOneStep() {
        // calls for a static method in Velocity class named applyToPoint to change the center point of the ball.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moving the ball when changing his center point to other place and making sure so that
     * the ball does not go outside of the screen -when it hits the border to the left or to the right,
     * it should change its horizontal direction, and when it hits the border on the top or the bottom,
     * it should change its vertical direction.
     * @param height of the GUI screen.
     * @param width of the GUI screen.
     */
    public void oldMoveOneStep(int height, int width) {
        /*
        Check if the ball hits the border  to the left or to the right.
        if so, the ball's velocity needs to change to -dx to change its horizontal direction.
         */
        if (this.center.getX() + this.radius > width
            || (this.center.getX() - this.radius <= 0 && this.center.getY() != 0)) {
            this.setVelocity(-this.vel.getDX(), this.vel.getDY());
        }
        /*
        Check if the ball hits  the border on the top or the bottom.
        if so, the ball's velocity needs to change to -dy to change its vertical direction.
         */
        if (this.center.getY() + this.radius > height
            || (this.center.getY() - this.radius <= 0 && this.center.getX() != 0)) {
            this.setVelocity(this.vel.getDX(), -this.vel.getDY());
        }
        // calls for a static method in Velocity class named applyToPoint to change the center point of the ball.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moving the ball when changing his center point to other place and making sure so that
     * the ball does not go outside of the screen -when it hits the border to the left or to the right,
     * it should change its horizontal direction, and when it hits the border on the top or the bottom,
     * it should change its vertical direction.
     * the method makes sure the ball is in the frame of (x1,y1) and (x2,y2)
     * @param x1 the x coordinate of the first argument of the frame
     * @param y1 the y coordinate of the first argument of the frame
     * @param x2 the x coordinate of the second argument of the frame
     * @param y2 the y coordinate of the second argument of the frame
     */
    public void moveOneStepInFrame(int x1, int y1, int x2, int y2) {
        /*
        Check if the ball hits the border to the left or to the right.
        if so, the ball's velocity needs to change to -dx to change its horizontal direction.
         */
        if (this.center.getX() - this.radius <= x1 || this.center.getX() + this.radius >= x2) {
            this.setVelocity(-this.vel.getDX(), this.vel.getDY());
        }
        /*
        Check if the ball hits  the border on the top or the bottom.
        if so, the ball's velocity needs to change to -dy to change its vertical direction..
         */
            if (this.center.getY() - this.radius <= y1 || this.center.getY() + this.radius >= y2) {
            this.setVelocity(this.vel.getDX(), -this.vel.getDY());
        }
        // calls for a static method in Velocity class named applyToPoint to change the center point of the ball.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moving the ball by changing his center point to other place with relating to potential collision
     * and changing his velocity if needed (when a collision is going to happen).
     */
    public void moveOneStep() {
        // compute the ball trajectory - how the ball will move
        // without any obstacles. (it's a line starting at current location, and
        // ending where the velocity will take the ball if no collisions will occur)
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
            this.center.getX() + this.getVelocity().getDX(), this.center.getY() + this.getVelocity().getDY());
        CollisionInfo collisionInfo = this.gameEnv.getClosestCollision(trajectory);
        // Check (using the game environment) if moving on this trajectory will not hit anything
        if (collisionInfo == null) {
            // when there is no hit, a normal move according to the velocity occurs.
            this.center = this.getVelocity().applyToPoint(this.center);
        } else  if (this.hitEdgesOfPaddle(collisionInfo)) {
            // when the ball hit the right/left edge of the paddle we change the horizontal direction.
            this.setVelocity(new Velocity(-this.vel.getDX(), this.vel.getDY()));
            // moving the ball to slightly before the hit point.
            this.center = new Point(collisionInfo.collisionPoint().getX() + this.getVelocity().getDX(),
                collisionInfo.collisionPoint().getY() - 0.5 * this.getVelocity().getDY());
        } else {
            // there is a normal hit at one one collidables, we move the ball to "almost" the hit point,
            // but just slightly before it
            this.center = new Point(collisionInfo.collisionPoint().getX() - 0.5 * this.getVelocity().getDX(),
                collisionInfo.collisionPoint().getY() - 0.5 * this.getVelocity().getDY());
            // update the velocity to the new velocity returned by the hit() method.
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.vel));
        }
    }

    /**
     * identify if the ball hits one of the right/left edges of the paddle or not.
     * @param collisionInfo of the hit.
     * @return if the if the ball hits one of the right/left edges of the paddle or not.
     */
    public boolean hitEdgesOfPaddle(CollisionInfo collisionInfo) {
        Point collisionPnt = collisionInfo.collisionPoint();
        Rectangle rect = collisionInfo.collisionObject().getCollisionRectangle();
        // check if the collision object is a paddle
        if (!collisionInfo.collisionObject().getClass().getName().equals("Paddle")) {
            return false;
        } else {
            return (collisionPnt.getX() == rect.getUpperLeft().getX()
                || collisionPnt.getX() == rect.getUpperLeft().getX() + rect.getWidth())
                && collisionPnt.getY() > rect.getUpperLeft().getY()
                && collisionPnt.getY() < rect.getUpperLeft().getY() + rect.getHeight();
            // returns if the hit occurred in the right/left edges of the paddle.
        }
    }

        /**
         * adding the ball object the game sprites.
         * @param g a game object
         */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removing ball from the game.
     * @param game object to remove the ball from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * set game Environment.
     * @param gameEnv1 is the game environment to set
     */
    public void setGameEnv(GameEnvironment gameEnv1) {
        this.gameEnv = gameEnv1;
    }
}