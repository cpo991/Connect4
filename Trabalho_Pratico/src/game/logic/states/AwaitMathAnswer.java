package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;

public class AwaitMathAnswer extends StateAdapter{
    GameData game = getGame();
    private final MathGame math = getGame().getMathGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    protected AwaitMathAnswer(GameData game) { super(game); }

    @Override
    public IState insertMathAnswer(double answer) {
        if (math.getGameNum() < 5) {
            if(math.getTotal() == answer){
                math.setGameNum(math.getGameNum()+1);
                math.sortExpression();
                return new AwaitMathAnswer(game);
            }
            return new AwaitMathAnswer(game);
        }
        if(math.getGameNum() == 5) {
            playerC.setSpecialPiece(playerC.getSpecialPiece() + 1);
            math.setGameNum(1);
        }
        playerC.resetTurn();
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitMathAsnwer;
    }
}
