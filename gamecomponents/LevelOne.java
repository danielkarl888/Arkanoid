package gamecomponents;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the information for level one in the game
 * the level information is being initialized in "GameLevel" Class.
 */
public class LevelOne implements LevelInformation {
    private List<Ball> listOfBalls;
    private List<Block> listOfBlocks;
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int BLOCK_X_CORD = 400;
    static final int BLOCK_Y_CORD = 100;
    static final int BLOCK_WIDTH = 15;
    static final int BLOCK_HEIGHT = 15;
    static final int BALL_X_CORD = 405;
    static final int BALL_Y_CORD = 250;
    static final int BALL_RADIUS = 3;

    /**
     * CONSTRUCTOR.
     * create number of blocks and balls to their lists.
     */
    public LevelOne() {
        this.listOfBalls = new ArrayList<>();
        this.listOfBlocks = new ArrayList<>();
        this.listOfBlocks.add(new Block(new Rectangle(new Point(BLOCK_X_CORD, BLOCK_Y_CORD), BLOCK_WIDTH, BLOCK_HEIGHT),
            Color.RED, null));
        this.listOfBalls.add(new Ball(new Point(BALL_X_CORD, BALL_Y_CORD), BALL_RADIUS, Color.white, null));
    }
    /**
     * @return the number of balls at the current level.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<>();
        listOfVelocities.add(new Velocity(0, 5));
        return listOfVelocities;
    }

    /**
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * @return the level name that will be displayed at the top of the screen
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Block getBackground() {
        return new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, FRAME_HEIGHT), Color.BLACK, new ArrayList<>());
    }

    /**
     * @return a list of the blocks in the level.
     */
    @Override
    public List<Block> blocks() {
        return this.listOfBlocks;
    }

    /**
     * @return number of blocks that should be removed before the level is considered to be "cleared".
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * getter of the list of the balls.
     * @return list Of Balls.
     */
    public List<Ball> listOfBallsGetter() {
        return this.listOfBalls;
    }

    /**
     * getter of the list of the blocks.
     * @return list Of Blocks.
     */
    public List<Block> listOfBlocksGetter() {
        return this.listOfBlocks;
    }
}
