package gamecomponents;

/**
 *  a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 *  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game is  a game object to remove the block from
     * @param remainingBlocks is the number of remaining Blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
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
        beingHit.removeFromGame(this.game);
        // decrease the number of the remaining Blocks by 1.
        this.remainingBlocks.decrease(1);
    }
}
