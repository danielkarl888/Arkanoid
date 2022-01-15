// ID: 318324563
package gamecomponents;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.KeyboardSensor;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * This is a class which initializes the objects in the game and runs them.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private LevelInformation levelInfo;
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int PAD_MAX_X_CORD = 750;
    static final int PAD_Y_CORD = 570;
    static final int PAD_HEIGHT = 10;
    static final int NORMAL_DISTANCE = 25;
    static final int BONUS = 100;
    static final int EXTRA_LENGTH = 25;
    static final int LOCATION_TEXT = 550;

    /**
     * constructor.
     * create lists of Sprites and Collidables and assigns them to the object's fields.
     * @param levelInfo is the information of the current level.
     * @param keyboardSensor of the game.
     * @param runner of the game. (the loop).
     * @param score of the user.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner runner, Counter score) {
        this.runner = runner;
        List<Sprite> listOfSprites = new ArrayList<>();
        List<Collidable> listOfCollidables = new ArrayList<>();
        this.sprites = new SpriteCollection(listOfSprites);
        this.environment = new GameEnvironment(listOfCollidables);
        this.numOfBlocks = new Counter(0);
        this.numOfBalls = new Counter(0);
        this.score = score;
        this.running = true;
        this.keyboard = keyboardSensor;
        this.levelInfo = levelInfo;
    }
    /**
     * adding collidable to the game environment.
     * @param c is a collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adding sprite to the list of the sprites.
     * @param s is a sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and the Balls and a Paddle when
     * adding them to the game.
     */
    public void initialize() {
        // create 2 lists of listeners. the first to the blocks and the second for the edge blocks and the paddle.
        List<HitListener> listeners = new ArrayList<>();
        List<HitListener> listeners2 = new ArrayList<>();
        List<HitListener> listeners3 = new ArrayList<>();
        Counter counterBlocks = new Counter(0);
        Counter counterBalls = new Counter(0);
        this.numOfBlocks = counterBlocks;
        this.numOfBalls = counterBalls;
        // create block remover object to add to the listeners.
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        listeners.add(blockRemover);
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(new Counter(0));
        listeners.add(scoreTrack);
        BallRemover ballRemover = new BallRemover(this, counterBalls);
        listeners3.add(ballRemover);
        // create a background block to the screen and add it to the game.
        Block background = this.levelInfo.getBackground();
        background.addToGame(this);
        List<Ball> listOfBalls = this.levelInfo.listOfBallsGetter();
        List<Velocity> listOfVelocities = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < listOfBalls.size(); i++) {
            listOfBalls.get(i).addToGame(this);
            listOfBalls.get(i).setGameEnv(this.environment);
            listOfBalls.get(i).setVelocity(listOfVelocities.get(i));
        }
        counterBalls.increase(this.levelInfo.numberOfBalls());
        // create a movable paddle to the game
        Paddle pad = new Paddle(this.runner.getGui().getKeyboardSensor(),
                new Rectangle(new Point((PAD_MAX_X_CORD - this.levelInfo.paddleWidth()) / 2.0 + EXTRA_LENGTH,
                    PAD_Y_CORD), this.levelInfo.paddleWidth(), PAD_HEIGHT), Color.YELLOW, this.levelInfo.paddleSpeed());
        pad.addToGame(this);
        // create 4 blocks to the edges of the screen and add them to the game.
        Block upperBlock = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, NORMAL_DISTANCE),
            Color.gray, listeners2);
        Block bottomBlock = new Block(new Rectangle(new Point(0, 599), FRAME_WIDTH, NORMAL_DISTANCE),
            Color.gray, listeners3);
        Block leftBlock = new Block(new Rectangle(new Point(0, 0), NORMAL_DISTANCE, FRAME_HEIGHT),
            Color.gray, listeners2);
        Block rightBlock = new Block(new Rectangle(new Point(FRAME_WIDTH - NORMAL_DISTANCE, 0),
            NORMAL_DISTANCE, FRAME_HEIGHT), Color.gray, listeners2);
        upperBlock.addToGame(this);
        bottomBlock.addToGame(this);
        leftBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
        List<Block> listOfBlocks = this.levelInfo.listOfBlocksGetter();
        // add all blocks from the infoLevel into the game and set listeners to them
        for (Block block : listOfBlocks) {
            block.addToGame(this);
            block.setHitListeners(listeners);
        }
        counterBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreIndicator);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        listeners.add(scoreTrackingListener);
        this.keyboard = this.runner.getGui().getKeyboardSensor();
    }
    /**
     * running the game loop, using the sprites collection.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }
    /**
     * removing collidable from the GameEnvironment.
     * @param c is a collidable to remove from the GameEnvironment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removing a sprite from the SpriteCollection.
     * @param s is a sprite to remove from the SpriteCollection.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.drawText(LOCATION_TEXT, 17, "Level Name: " + this.levelInfo.levelName(), 20);
        // check if all required number blocks in the level have removed
        if (!this.hasMoreBlocksToRemove()) {
           // add 100 points to the score when a level is completed.
            this.score.increase(BONUS);
            this.running = false;
        } else if (this.numOfBalls.getValue() == 0) {
            this.running = false;
        }
        // if the user presses 'p' the game is resumed.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return if there is no ball remained
     */
    public boolean noMoreBalls() {
        return numOfBalls.getValue() == 0;
    }

    /**
     *
     * @return if there is more blocks to remove in the game or not.
     */
    public boolean hasMoreBlocksToRemove() {
        return this.levelInfo.blocks().size() - this.levelInfo.numberOfBlocksToRemove() < numOfBlocks.getValue();
    }

}
