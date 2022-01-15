package gamecomponents;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Win screen class shows an animation right after the user wins the game.
 */
public class WinScreen implements Animation {
    private Counter score;
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * constructor.
     * @param sensor of the keyboard
     * @param score of the user.
     */
    public WinScreen(KeyboardSensor sensor, Counter score) {
        this.stop = false;
        this.score = score;
        this.keyboardSensor = sensor;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
            "                You Win! \nYou have " + this.score.getValue() + " points!", 32);
        }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
