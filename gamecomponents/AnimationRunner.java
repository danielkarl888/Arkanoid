package gamecomponents;

import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * AnimationRunner class for the loops in the game.
 */
public class AnimationRunner {
//    static final int FRAMES_PER_SECOND = 60;
    static final int MILLISECONDS = 1000;
    static final int FRAMES_PER_SECOND = 60;
    private GUI gui;
    private int framesPerSecond;

    /**
     * constructor.
     * @param gui is the object to make the draw on.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAMES_PER_SECOND;
    }

    /**
     * getter.
     * @return the gui of the runner.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * method which responsible for the game loop in the game.
     * @param animation determines the game logic and the stopping condition for the current loop.
     */
    public void run(Animation animation) {
        // create a sleeper
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // set the framePerSeconds number to 60
        // (we want a smooth animations that displays 60 different frames in a second, if possible)
        int millisecondsPerFrame = MILLISECONDS / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing the start time of the loop
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            // using the sleeper only when there is still time left from the work done in the beginning of the loop
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
