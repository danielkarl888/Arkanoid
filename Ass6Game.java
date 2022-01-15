// ID: 318324563
import biuoop.GUI;
import gamecomponents.AnimationRunner;
import gamecomponents.GameFlow;
import gamecomponents.LevelFour;
import gamecomponents.LevelInformation;
import gamecomponents.LevelOne;
import gamecomponents.LevelThree;
import gamecomponents.LevelTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of running a game when the class creates a game object, initializes and runs it.
 */
public class Ass6Game {
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int LAST_LEVEL = 4;

    /**
     * main method to creates a game object, initializes and runs it.
     * @param args ignored.
     */
    public static void main(String[] args) {
        int level;
        int count = 0;
        List<LevelInformation> l = new ArrayList<>();
        // check if no arguments were inserted.
        if (args.length == 0) {
            count++;
            // add the normal game sequence to the level list.
            l.add(new LevelOne());
            l.add(new LevelTwo());
            l.add(new LevelThree());
            l.add(new LevelFour());
        } else {
            for (String arg : args) {
                try {
                    level = Integer.parseInt(arg);
                    // check if the number of level is not valid
                    if (level <= 0 || level > LAST_LEVEL) {
                        continue;
                    } else {
                        if (level == 1) {
                            count++;
                            l.add(new LevelOne());
                        } else if (level == 2) {
                            l.add(new LevelTwo());
                            count++;
                        } else if (level == 3) {
                            l.add(new LevelThree());
                            count++;
                        } else {
                            l.add(new LevelFour());
                            count++;
                        }
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        if (count == 0) {
            l.add(new LevelOne());
            l.add(new LevelTwo());
            l.add(new LevelThree());
            l.add(new LevelFour());
        }
        // CREATE RUNNER TO RUN THE GAME
        AnimationRunner runner = new AnimationRunner(new GUI("Arkanoid", FRAME_WIDTH, FRAME_HEIGHT));
        // create gameFlow object with the runner.
        GameFlow game = new GameFlow(runner, runner.getGui().getKeyboardSensor());
        // run the levels
        game.runLevels(l);
    }
}
