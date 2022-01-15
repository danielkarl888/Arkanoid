package gamecomponents;
import biuoop.DrawSurface;

/**
 * Animation interface has template-methods which represents a game-specific logic and stopping conditions.
 */
public interface Animation {
    /**
     * the logic of a game.
     * @param d is the draw surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * stopping condition in a loop game.
     * @return if the game should stop (true) or not (false).
     */
    boolean shouldStop();
}
