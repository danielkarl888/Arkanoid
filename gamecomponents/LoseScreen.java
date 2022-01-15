package gamecomponents;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Lose screen class shows an animation right after the user wins the game.
 */
public class LoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * constructor.
     * @param sensor of the keyboard
     * @param score of the user.
     */
    public LoseScreen(KeyboardSensor sensor, Counter score) {
        this.keyboard = sensor;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "                You lost.. \nYou have " + this.score.getValue() + " points",
            32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}

