package gamecomponents;

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * this class is responsible for the flow of the game. the class can runs levels in the game and show the score
 * of the user in case of death/ winning the game.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * constructor.
     * @param ar is an animation runner for the loop run game
     * @param ks is the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
    }

    /**
     * this method runs levels in the game and ends when the user wins or loses the game.
     * @param levels is a list of levels the user has to pass in order to get the win.
     */
    public void runLevels(List<LevelInformation> levels) {
        // create a variable to count how many levels the user succeed.
        int counter = 0;
        for (LevelInformation levelInfo : levels) {
            counter++;
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);

            level.initialize();
            // run the when the level has more blocks and balls.
            while (!level.noMoreBalls() && level.hasMoreBlocksToRemove()) {
                level.run();
            }
            // stop the game when no more balls left in the current level.
            if (level.noMoreBalls()) {
                counter--;
                break;
            }
        }
        // check if the user didn't succeed all levels
        if (counter < levels.size()) {
            // run a lose screen while showing his final score.
            this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, new LoseScreen(this.keyboardSensor, this.score)));
        } else {
            //  run a winning screen while showing his final score.
            this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, new WinScreen(this.keyboardSensor, this.score)));
        }
        // closing the gui
        this.animationRunner.getGui().close();
    }
}
