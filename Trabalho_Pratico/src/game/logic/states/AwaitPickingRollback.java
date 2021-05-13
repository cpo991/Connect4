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

    protected AwaitPickingRollback(GameData game) {
        super(game);
    }


    @Override
    public IState rollback(int num) {


        //game.removePieces(num, playerC);
        playerC.removeCredits(num);
        playerC.setSpecialPiece(0);
        //game.setPlay(game);
        game.setPlay(game);
        return new AwaitDecision(game);
    }


    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitRollback;
    }
}
