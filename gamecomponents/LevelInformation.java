package gamecomponents;
import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * @return the number of balls at the current level.
     */
    int numberOfBalls();

    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * The initial velocity of each ball.
     * @return a list of velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * @return the level name that will be displayed at the top of the screen
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Block getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * @return a list of the blocks in the level.
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * @return number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();

    /**
     * @return the list of the balls in the level
     */
    List<Ball> listOfBallsGetter();
    /**
     * @return the list of the blocks in the level
     */
    List<Block> listOfBlocksGetter();
}
