package gamecomponents;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * ScoreIndicator is a class which will be in charge of displaying the current score in the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     * @param score is the score Counter object
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d is a drawer.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        // draw the score text in the game.
        d.drawText(350, 17, "Score: " + this.score.getValue(), 20);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
