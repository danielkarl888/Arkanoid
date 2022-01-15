package gamecomponents;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Win screen class shows an animation when the user pauses the game when pressing the letter p and returns
 * to the game when the user presses the space key..
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param keyboardSensor is the sensor of the keyboard.
     */
    public PauseScreen(KeyboardSensor keyboardSensor) {
        this.keyboard = keyboardSensor;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "                paused -- press space to continue", 32);
     }
     @Override
    public boolean shouldStop() {
        return false;
    }
}
