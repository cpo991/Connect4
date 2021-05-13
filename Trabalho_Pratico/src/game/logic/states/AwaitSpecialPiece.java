package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitSpecialPiece extends StateAdapter{
    GameData game = getGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    protected AwaitSpecialPiece(GameData game) {
        super(game);
    }

    @Override
    public IState setSpecialPiece(int option) {
        game.updatePieceList(option-1,playerC );
        playerC.setSpecialPiece(playerC.getSpecialPiece()-1);
        //Makes sense?
        game.setGameTurn(game.getGameTurn()+1);
        playerC.addTurn();
        game.changeWhoseTurn(); //change player to play
        game.setPlay(game);
        return new AwaitDecision(game);
    }

    @Override
    public IState cancelSpecialPiece() {
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitSpecialPiece;
    }
}
