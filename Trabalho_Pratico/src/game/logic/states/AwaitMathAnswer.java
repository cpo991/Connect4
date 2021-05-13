package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitMathAnswer extends StateAdapter{
    GameData game = getGame();
    private final MathGame math = getGame().getMathGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    protected AwaitMathAnswer(GameData game) { super(game); }

    @Override
    public IState insertMathAnswer(double answer) {
        if (math.getGameNum() < 5) {
            if (math.getTotal() == answer) { //correct answer
                math.setGameNum(math.getGameNum() + 1);
                math.sortExpression();
                return new AwaitMathAnswer(game);
            }
            math.sortExpression();
            return new AwaitMathAnswer(game);
        }
        if (math.getGameNum() == 5) { // wins mini game
            if(math.getStartTime()+math.getSec() >= System.currentTimeMillis()) {
                playerC.setSpecialPiece(playerC.getSpecialPiece() + 1);
                math.setGameNum(1);
                playerC.resetTurn();
                math.setHasWon(true);
            }
        }
        math.setHasWon(false);
        playerC.resetTurn();
        game.changeWhoseTurn();
        game.setPlay(game);
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitMathAnswer;
    }
}
