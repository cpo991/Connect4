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
    protected AwaitDecision(GameData game) {
        super(game);
    }

    @Override
    public IState setPiece(int option) {
        int result;
        int player = game.getWhosTurn();
        if(game.getPlayerByNum(player).getIsPerson())
            result = setPlayerPiece(game.getPlayerByNum(player),option);
        else {
            int column = Utils.randNum(1,7);
            result = setPlayerPiece(game.getPlayerByNum(player), column);
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
        if(game.fourInLine(player)) return 0;
        //had piece to pieces list
        game.setPieceToList(new Piece(game.getGamePlayNum(),
                player.getPiece(), result, column-1, 'A'));
        if(player.getIsPerson()) {
            if (player.getTurn() == 5) //didnt choosed mini game
                player.resetTurn();
            player.addTurn(); //increase turn
        }
        game.changeWhosTurn(); //change player to play
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
