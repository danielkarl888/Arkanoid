package gamecomponents;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the information for level four in the game
 * the level information is being initialized in "GameLevel" Class.
 */
public class LevelFour implements LevelInformation {
    private List<Ball> listOfBalls;
    private List<Block> listOfBlocks;
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int BLOCK_WIDTH = 50;
    static final int BLOCK_HEIGHT = 20;
    static final int BALL_X_CORD = 370;
    static final int BALL_Y_CORD = 400;
    static final int BALL_RADIUS = 7;
    static final int DIFF_BETWEEN_BLOCKS_IN_LINE = 50;
    static final int DIFF_BETWEEN_LINE = 20;
    static final int NUM_OF_LINES = 7;
    static final int NUM_OF_BLOCKS_IN_LINE = 15;

    /**
     * CONSTRUCTOR.
     * create number of blocks and balls to their lists.
     */
    public LevelFour() {
        this.listOfBalls = new ArrayList<>();
        this.listOfBlocks = new ArrayList<>();
        int startPointY = 100;
        for (int j = 0; j < NUM_OF_LINES; j++) {
            int startPointX = 25;
            Color randColor = new Color((int) (Math.random() * 0x1000000));
            for (int i = 0; i < NUM_OF_BLOCKS_IN_LINE; i++) {
                this.listOfBlocks.add(new Block(new Rectangle(new Point(startPointX, startPointY),
                    BLOCK_WIDTH, BLOCK_HEIGHT), randColor, null));
                startPointX += DIFF_BETWEEN_BLOCKS_IN_LINE;
            }
            startPointY += DIFF_BETWEEN_LINE;
        }

        this.listOfBalls.add(new Ball(new Point(300, 400), BALL_RADIUS, Color.WHITE, null));
        this.listOfBalls.add(new Ball(new Point(550, 400), BALL_RADIUS, Color.WHITE, null));
        this.listOfBalls.add(new Ball(new Point(425, 380), BALL_RADIUS, Color.WHITE, null));
    }

    /**
     * @return the number of balls at the current level.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            listOfVelocities.add(new Velocity(3, 4));
        }
        return listOfVelocities;
    }

    /**
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 7;
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
        return "Final Four";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Block getBackground() {
        return new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, FRAME_HEIGHT), Color.CYAN, new ArrayList<>());
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
        return 105;
    }

    @Override
    public List<Ball> listOfBallsGetter() {
        return this.listOfBalls;
    }

    @Override
    public List<Block> listOfBlocksGetter() {
        return this.listOfBlocks;
    }
}

