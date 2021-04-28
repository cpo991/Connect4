package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingRollback extends StateAdapter{
    GameData game = getGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    private final Player player1 = getGame().getPlayerByNum(1);
    private final Player player2 = getGame().getPlayerByNum(2);

    protected AwaitPickingRollback(GameData game) {
        super(game);
    }


    @Override
    public IState rollback(int num) {
        //aplly rollback
        game.removePieces(num);
        playerC.removeCredits(num);
        playerC.setSpecialPiece(0);
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitRollback;
    }
}
