package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.utils.Utils;
import jdk.jshell.execution.Util;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingRollback extends StateAdapter{
    private final GameData game;
    private final Player playerC;
    protected AwaitPickingRollback(GameData game) {
        super(game);
        this.game = game;
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    }


   /* @Override
    public IState rollback(int num) {
        if(num == -1) {
            game.addLog("AwaitPickingRollback - [ERROR] Insufficient credits or game turn to small");
            Utils.launchLog("AwaitPickingRollback","[ERROR] Insufficient credits or game turn to small");
            return this;
        }
        if(num == 0) {
            return new AwaitDecision(game);
        }
        else {
            int rollback = num;
            game.setRollbackMade(num);
            while (rollback >= 0) {
                game.setPlayerRollBackSP(playerC.getSpecialPiece());
                game.setPlayerRollBack(game.getWhoseTurn());
                rollback--;
            }
            game.addLog("AwaitPickingRollback - "+num+" rollbacks applied");
            Utils.launchLog("AwaitPickingRollback",num+" rollbacks applied");
            game.addSnapShot();
        }
        return new AwaitDecision(game);
    }*/

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitPickingRollback; }
}
