package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitSpecialPiece extends StateAdapter{
    private final GameData game;
    private final Player playerC;
    protected AwaitSpecialPiece(GameData game) {
        super(game);
        this.game = game;
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    }

    @Override
    public IState setSpecialPiece(int option) {
        if(option >= 1 && option <= 7){
            if(playerC.getSpecialPiece()>0) {
                game.setPlayerSpecialPiece(option - 1);
                playerC.setSpecialPiece(playerC.getSpecialPiece() - 1);
                game.setGameTurn(game.getGameTurn() + 1);
                playerC.addTurn();
                game.changeWhoseTurn();
                game.addLog("AwaitSpecialPiece - " + playerC.getName() + "put special piece on column " + option);
                Utils.launchLog("AwaitSpecialPiece", playerC.getName() + "put special piece on column " + option);
                game.addSnapShot();
            }
        }
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitSpecialPiece;
    }
}
