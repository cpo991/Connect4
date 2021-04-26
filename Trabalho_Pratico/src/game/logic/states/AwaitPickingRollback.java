package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingRollback extends StateAdapter{


    protected AwaitPickingRollback(GameData game) {
        super(game);
    }


    @Override
    public IState rollback(int num) {
        //aplly rollback
        if(getGame().getWhosTurn() == 1){
            getGame().removePieces(num);
            getGame().removePlayer1Credits(num);
            getGame().setPlayer1SP(0);
        }else{
            getGame().removePieces(num);
            getGame().removePlayer2Credits(num);

        }
        return new AwaitDecision(getGame());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitRollback;
    }
}
