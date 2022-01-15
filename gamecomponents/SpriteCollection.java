// ID: 318324563
package gamecomponents;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A SpriteCollection will hold a collection of sprites to implement common method of all the sprites.
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites;

    /**
     * constructor.
     * @param listOfSprites is a collection of sprites
     */
    public SpriteCollection(List<Sprite> listOfSprites) {
        this.listOfSprites = listOfSprites;
    }

    /**
     * adding to the sprite collection.
     * @param s is a Sprite object to add to the collection.
     */
    public void addSprite(Sprite s) {
        // add the s sprite to the list
        listOfSprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copyListOfSprites  = new ArrayList<>(this.listOfSprites);
        // using "time passed" method to all sprites in the list.
        for (Sprite s: copyListOfSprites) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d is the drawer of the sprite
     */
    public void drawAllOn(DrawSurface d) {
        // using "drawOn" method to all sprites in the list.
        for (Sprite s: this.listOfSprites) {
            s.drawOn(d);
        }
    }
    /**
     * removing a sprite from the sprite collection.
     * @param s is a Sprite object to add to the collection.
     */
    public void removeSprite(Sprite s) {
        // remove the s sprite to the list
        listOfSprites.remove(s);
    }
    }
