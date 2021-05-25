package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitMathAnswer extends StateAdapter{
    private final GameData game;
    private final MathGame math;
    private final Player playerC;
    protected AwaitMathAnswer(GameData game) {
        super(game);
        this.game = game;
        this.math = getGame().getMathGame();
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    }

    @Override
    public IState insertMathAnswer(double answer) {
        if (math.getGameNum() < 5) {
            if (math.getTotal() == answer) { //correct answer
                math.setGameNum(math.getGameNum() + 1);
                math.sortExpression();
                game.addLog("AwaitMathAnswer - Correct Answer, new expression sorted");
                Utils.launchLog("AwaitMathAnswer","Correct Answer, new expression sorted");
                return new AwaitMathAnswer(game);
            }
            game.addLog("AwaitMathAnswer - Wrong Answer, new expression sorted");
            Utils.launchLog("AwaitMathAnswer","Wrong Answer, new expression sorted");
            math.sortExpression();
            return new AwaitMathAnswer(game);
        }
        if (math.getGameNum() == 5) { // wins mini game
            if(math.getStartTime()+math.getSec() >= System.currentTimeMillis()) {
                playerC.setSpecialPiece(playerC.getSpecialPiece() + 1);
                math.setGameNum(1);
                playerC.resetTurn();
                math.setHasWon(true);
                game.addLog("AwaitMathAnswer - Game Won");
                Utils.launchLog("AwaitMathAnswer","Game Won");
            }else {
                game.addLog("AwaitMathAnswer - Game Lost");
                Utils.launchLog("AwaitMathAnswer", "Game Lost");
            }
        }
        math.setHasWon(false);
        playerC.resetTurn();
        game.changeWhoseTurn();
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitMathAnswer;
    }
}
