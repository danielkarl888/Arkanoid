package gamecomponents;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the information for level three in the game
 * the level information is being initialized in "GameLevel" Class.
 */
public class LevelThree implements LevelInformation {
    static final int NORMAL_DISTANCE = 20;
    static final int START_POINT_X = 725;
    static final int START_POINT_Y = 150;
    static final int DIFFERENCE_BETWEEN_BLOCKS_X = 50;
    static final int DIFFERENCE_BETWEEN_BLOCKS_Y = 25;
    static final int NORMAL_BLOCK_WIDTH = 50;
    static final int NORMAL_BLOCK_HEIGHT = 25;
    static final int NUM_OF_LINES_BLOCKS = 9;
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int BALL_X_CORD = 375;
    static final int BALL_Y_CORD = 400;
    static final int BALL_RADIUS = 7;
    private List<Ball> listOfBalls;
    private List<Block> listOfBlocks;
    /**
     * CONSTRUCTOR.
     * create number of blocks and balls to their lists.
     */
    public LevelThree() {
        this.listOfBalls = new ArrayList<>();
        this.listOfBlocks = new ArrayList<>();
        int startPointX = START_POINT_X;
            int startPointY = START_POINT_Y;
        // create a nice number of blocks in different colors and add them to the game.
        for (int i = NUM_OF_LINES_BLOCKS; i > 4; i--) {
            Color randColor = new Color((int) (Math.random() * 0x1000000));
            for (int j = i + 1; j > 0; j--) {
                // set a random color
                this.listOfBlocks.add(new Block(new Rectangle(new Point(startPointX, startPointY), NORMAL_BLOCK_WIDTH,
                    NORMAL_BLOCK_HEIGHT),
                    randColor, null));
                if (i == NUM_OF_LINES_BLOCKS) {
                    this.listOfBlocks.add(new Block(new Rectangle(new Point(startPointX, startPointY),
                        NORMAL_BLOCK_WIDTH, NORMAL_BLOCK_HEIGHT), randColor, null));
                }
                startPointX -= DIFFERENCE_BETWEEN_BLOCKS_X;
            }
            startPointY += DIFFERENCE_BETWEEN_BLOCKS_Y;
            startPointX = START_POINT_X;
        }
        this.listOfBalls.add(new Ball(new Point(BALL_X_CORD, BALL_Y_CORD), BALL_RADIUS, Color.WHITE, null));
        this.listOfBalls.add(new Ball(new Point(BALL_X_CORD, BALL_Y_CORD), BALL_RADIUS, Color.WHITE, null));
    }

    /**
     * @return the number of balls at the current level.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            listOfVelocities.add(new Velocity(Math.pow(-1, i) * 3,  -8));
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
        return "Green 3";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Block getBackground() {
        return new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, FRAME_HEIGHT), Color.green, new ArrayList<>());
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
        return 50;
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
