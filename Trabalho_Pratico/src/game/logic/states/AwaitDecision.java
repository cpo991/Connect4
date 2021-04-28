package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Piece;
import game.logic.data.Player;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitDecision extends StateAdapter {
    GameData game = getGame();
    Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    protected AwaitDecision(GameData game) {
        super(game);
    }

    @Override
    public IState setPiece(int option) {
        int result;
        if(playerC.getIsPerson())
            result = setPlayerPiece(playerC,option);
        else {
            int column = Utils.randNum(1,7);
            result = setPlayerPiece(playerC, column);
        }
        if(result == 0) return new EndGame(game);
        return new AwaitDecision(game);
    }

    private int setPlayerPiece(Player player, int column){
        int result;
        result = game.setPlayerPiece(column, player);
        //error adding piece
        if(result == -1) return -1;
        //has won
        if(game.hasWon(player)) return 0;
        //had piece to pieces list
        game.setPieceToList(new Piece(game.getGameTurn(),
                player.getPiece(), result, column-1, 'A'));
        if(player.getIsPerson()) {
            if (player.getTurn() == 5) //didnt choosed mini game
                player.resetTurn();
            player.addTurn(); //increase turn
        }
        game.setGameTurn(game.getGameTurn()+1);
        game.changeWhoseTurn(); //change player to play
        return 1;
    }


    @Override
    public IState chooseRollback() {
        return new AwaitPickingRollback(game);
    }

    @Override
    public IState startMiniGame() {
        return new AwaitGamePicker(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitDecision;
    }
}
