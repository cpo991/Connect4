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
    private final GameData game;
    private final Player playerC;
    protected AwaitDecision(GameData game) {
        super(game);
        this.game = game;
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    }

    @Override
    public IState setPiece(int option) {
        if(game.isBoardFull()) {
            game.addLog("AwaitDecision - No one won, board full");
            Utils.launchLog("AwaitDecision","No one won, board full");
            return new EndGame(game);
        }
        if(playerC.getIsPerson()) {
            if (!game.setPlayerPiece(playerC, option)) {
                game.addLog("AwaitDecision - Error adding piece on column "+option);
                Utils.launchLog("AwaitDecision","Error adding piece on column "+option);
                return new AwaitDecision(game);
            }
        }
        else {
            int column = Utils.randNum(1,7);
            while(!game.setPlayerPiece(playerC, column)){
                column = Utils.randNum(1,7);
            }
        }
        if(game.hasWon(playerC)) {
            game.addLog("AwaitDecision - "+playerC.getName()+" put a piece on the column "+option);
            game.addLog("AwaitDecision - "+playerC.getName()+" has won");
            Utils.launchLog("AwaitDecision",playerC.getName()+" put a piece on the column "+option);
            Utils.launchLog("AwaitDecision",playerC.getName()+" has won");
            return new EndGame(game);
        }
        game.addLog("AwaitDecision - "+playerC.getName()+" put a piece on the column "+option);
        Utils.launchLog("AwaitDecision",playerC.getName()+" put a piece on the column "+option);
        game.addSnapShot();
        return new AwaitDecision(game);
    }

    @Override
    public IState saveGame() { return new AwaitSaveGameFile(game); }

    @Override
    public IState chooseSpecialPiece() { return new AwaitSpecialPiece(game); }

    @Override
    public IState chooseRollback() { return new AwaitPickingRollback(game); }

    @Override
    public IState startMiniGame() { return new AwaitPickingGame(game); }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitDecision; }
}
