package gamecomponents;
/**
 *  a BallRemover is in charge of removing Balls from the game, as well as keeping count
 *  of the number of Balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game is  a game object to remove the block from
     * @param remainingBalls is the number of remaining Balls in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * while removing this listener from the block
     * that is being removed from the game
     * @param beingHit object.
     * @param hitter   is the ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // remove block from the game
        hitter.removeFromGame(this.game);
        // decrease the number of the remaining Blocks by 1.
        this.remainingBalls.decrease(1);
    }
}
