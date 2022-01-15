// ID: 318324563
package gamecomponents;

import biuoop.DrawSurface;
/**
 * a Sprite is a game object that can be drawn to the screen.
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc)
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d is a drawer.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
