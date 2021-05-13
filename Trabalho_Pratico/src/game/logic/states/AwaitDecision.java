package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitDecision extends StateAdapter {
    private final GameData game = getGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    protected AwaitDecision(GameData game) {
        super(game);
    }

    @Override
    public IState setPiece(int option) {
        int result;
        if(playerC.getIsPerson())
            result = game.setPlayerPiece(playerC, option);
        else {
            int column = Utils.randNum(1,7);
            result = game.setPlayerPiece(playerC, column);
        }
        if(result == 0){
            game.addReplay();
            game.setPlay(game);
            return new EndGame(game);
        }
        game.setPlay(game);
        return new AwaitDecision(game);
    }


    @Override
    public IState chooseSpecialPiece() { return new AwaitSpecialPiece(game); }

    @Override
    public IState chooseRollback() { return new AwaitPickingRollback(game); }

    @Override
    public IState startMiniGame() { return new AwaitGamePicker(game); }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitDecision; }
}
