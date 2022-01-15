package gamecomponents;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the information for level two in the game
 * the level information is being initialized in "GameLevel" Class.
 */
public class LevelTwo implements LevelInformation {
    private List<Ball> listOfBalls;
    private List<Block> listOfBlocks;
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int BLOCK_Y_CORD = 200;
    static final int BLOCK_WIDTH = 50;
    static final int BLOCK_HEIGHT = 20;
    static final int BALL_X_CORD = 370;
    static final int BALL_Y_CORD = 400;
    static final int BALL_RADIUS = 7;
    static final int DIFF_BETWEEN_BLOCKS = 50;
    static final int NUM_OF_BALLS = 10;

    /**
     * CONSTRUCTOR.
     * create number of blocks and balls to their lists.
     */
    public LevelTwo() {
        this.listOfBalls = new ArrayList<>();
        this.listOfBlocks = new ArrayList<>();
        int startPointX = 25;
        for (int i = 0; i < 7; i++) {
            Color randColor = new Color((int) (Math.random() * 0x1000000));
            this.listOfBlocks.add(new Block(new Rectangle(new Point(startPointX, BLOCK_Y_CORD),
                BLOCK_WIDTH, BLOCK_HEIGHT),
                randColor, null));
            startPointX += DIFF_BETWEEN_BLOCKS;
            this.listOfBlocks.add(new Block(new Rectangle(new Point(startPointX, BLOCK_Y_CORD),
                BLOCK_WIDTH, BLOCK_HEIGHT),
                randColor, null));
            if (i == 3) {
                startPointX += DIFF_BETWEEN_BLOCKS;
                this.listOfBlocks.add(new Block(new Rectangle(new Point(startPointX, BLOCK_Y_CORD),
                    BLOCK_WIDTH, BLOCK_HEIGHT),
                    randColor, null));
            }
            startPointX += DIFF_BETWEEN_BLOCKS;
        }
        for (int i = 1; i <= NUM_OF_BALLS; i++) {
                this.listOfBalls.add(new Ball(new Point(BALL_X_CORD, BALL_Y_CORD), BALL_RADIUS, Color.red, null));
            }
        }

    /**
     * @return the number of balls at the current level.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 2; j++) {
                listOfVelocities.add(Velocity.fromAngleAndSpeed(Math.pow(-1, j) * 150, 2 + i));
            }
        }
        return listOfVelocities;
    }

    /**
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 500;
    }

    /**
     * @return the level name that will be displayed at the top of the screen
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Block getBackground() {
        return new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, FRAME_HEIGHT), Color.WHITE, new ArrayList<>());
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
        return 15;
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