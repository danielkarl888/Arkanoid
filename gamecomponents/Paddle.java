// ID: 318324563
package gamecomponents;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;

/**
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 * The Paddle knows how to move to the left and to the right.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private Color color;
    private int speed;

    /**
     * constructor.
     * @param keyboard        is a key pressed from the user
     * @param paddleRectangle is the rectangle the paddle is created from
     * @param color           of the paddle
     * @param speed           of the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddleRectangle, Color color, int speed) {
        this.keyboard = keyboard;
        this.paddleRectangle = paddleRectangle;
        this.color = color;
        this.speed = speed;
    }

    /**
     * move the paddle a little bit left when the user entered the left arrow key.
     */
    public void moveLeft() {
        // move the paddle when the paddle is on the range of the screen
        if (this.paddleRectangle.getUpperLeft().getX() >= 20) {
            this.paddleRectangle = new Rectangle(new Point(this.paddleRectangle.getUpperLeft().getX() - this.speed,
                this.paddleRectangle.getUpperLeft().getY()), this.paddleRectangle.getWidth(),
                this.paddleRectangle.getHeight());
        }
    }

    /**
     * move the paddle a little bit right when the user entered the right arrow key.
     */
    public void moveRight() {
        // move the paddle when the paddle is on the range of the screen
        if (this.paddleRectangle.getUpperLeft().getX() + this.paddleRectangle.getWidth()  <= 780) {
            this.paddleRectangle = new Rectangle(new Point(this.paddleRectangle.getUpperLeft().getX() + this.speed,
                this.paddleRectangle.getUpperLeft().getY()), this.paddleRectangle.getWidth(),
                this.paddleRectangle.getHeight());
        }
    }

    /**
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * updating the velocity after hitting the collidable.
     *
     * @param hitter
     * @param collisionPoint  that an object collided with.
     * @param currentVelocity of the object that hits the collidable.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // check if the collision point is on the top/bottom border of the block.
        // if so, a change in the vertical direction should be done.
        if (this.paddleRectangle.getUpperLeft().getY() == collisionPoint.getY()
            || this.paddleRectangle.getUpperLeft().getY() + this.paddleRectangle.getHeight()
            == collisionPoint.getY()) {
            // check if the collision occurs in the 1st fifth of the paddle
            if (collisionPoint.getX() >= this.paddleRectangle.getUpperLeft().getX()
                && collisionPoint.getX() <= (this.paddleRectangle.getUpperLeft().getX()
                + 0.2 * this.paddleRectangle.getWidth())) {
                // when the collision occurs in the 1st fifth of the paddle, the ball
                // should bounce back with an angle of 300 degrees (-60)
                return (Velocity.fromAngleAndSpeed(-60, Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
                    + Math.pow(currentVelocity.getDY(), 2))));
            } else if ((collisionPoint.getX() > this.paddleRectangle.getUpperLeft().getX()
                + 0.2 * this.paddleRectangle.getWidth())
                && collisionPoint.getX() <= this.paddleRectangle.getUpperLeft().getX()
                + 0.4 * this.paddleRectangle.getWidth()) {
                // when the collision occurs in the 2nd fifth of the paddle, the ball
                // should bounce back with an angle of 330 degrees (-30)
                return (Velocity.fromAngleAndSpeed(-30, Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
                    + Math.pow(currentVelocity.getDY(), 2))));
            } else if (collisionPoint.getX() > this.paddleRectangle.getUpperLeft().getX()
                + 0.4 * this.paddleRectangle.getWidth()
                && collisionPoint.getX() <= this.paddleRectangle.getUpperLeft().getX()
                + 0.6 * this.paddleRectangle.getWidth()) {
                // when the collision occurs in the 3rd fifth of the paddle, it should keep its horizontal direction
                // and only change its vertical one
                return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
            } else if (collisionPoint.getX() > this.paddleRectangle.getUpperLeft().getX()
                + 0.6 * this.paddleRectangle.getWidth()
                && collisionPoint.getX() <= this.paddleRectangle.getUpperLeft().getX()
                + 0.8 * this.paddleRectangle.getWidth()) {
                // when the collision occurs in the 4th fifth of the paddle, the ball
                // should bounce back with an angle of 30 degrees
                return (Velocity.fromAngleAndSpeed(30, (Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
                    + Math.pow(currentVelocity.getDY(), 2)))));
            } else {
                // when the collision occurs in the last fifth of the paddle, the ball
                // should bounce back with an angle of 60 degrees
                return (Velocity.fromAngleAndSpeed(60, Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
                        + Math.pow(currentVelocity.getDY(), 2))));
                }
            } else {
            // when the hit is being made in right/left border a change the horizontal direction should be done.
            return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
        }

    }
    /**
     * draw the paddle to the screen.
     * @param surface is a drawer.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // set a color and fill the shape of the rectangle
        surface.setColor(this.color);
        surface.fillRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
            (int) this.paddleRectangle.getUpperLeft().getY(), (int) this.paddleRectangle.getWidth(),
            (int) this.paddleRectangle.getHeight());
        // set a color and draw the edges of the rectangle
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
            (int) this.paddleRectangle.getUpperLeft().getY(), (int) this.paddleRectangle.getWidth(),
            (int) this.paddleRectangle.getHeight());
    }

    /**
     *  check which key the user entered and move the paddle accordingly.
     */
    @Override
    public void timePassed() {
        // check if the user entered the left arrow key
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
          // check if the user entered the right arrow key
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * adding the paddle object to the game collidables and to the game sprites.
     * @param g a game object
     */
    public void addToGame(GameLevel g) {
        // the Paddle is both collidable and sprite
        g.addCollidable(this);
        g.addSprite(this);
    }
}