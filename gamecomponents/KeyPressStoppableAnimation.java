package gamecomponents;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation decorator-class wraps an existing animation and adds a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor of the keyboard
     * @param key to stop the animation
     * @param animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        // check if the given key is pressed and if the key is already pressed
        if (this.keyboardSensor.isPressed(key) && !this.isAlreadyPressed) {
            // we ignore the key press for a while.
            this.stop = true;
        } else {
            // when the key is not pressed we know that there was a time point after the animation started in which
            // the key was not pressed
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
