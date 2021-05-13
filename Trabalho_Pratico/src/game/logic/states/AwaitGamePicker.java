package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;
import game.logic.data.WordGame;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGamePicker extends StateAdapter{
    GameData game = getGame();
    private final MathGame math = getGame().getMathGame();
    private final WordGame word = getGame().getWordGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());

    protected AwaitGamePicker(GameData game) {
        super(game);
    }

    @Override
    public IState startMathGame() {
        math.setHasWon(false);
        math.sortExpression();
        math.setGameNum(1);
        math.setStartTime(System.currentTimeMillis());
        return new AwaitMathAnswer(game);
    }

    @Override
    public IState startWordsGame() {
        word.setHasWon(false);
        word.add5Words();
        word.setSec();
        word.setStartTime(System.currentTimeMillis());
        return new AwaitWordsAnswer(game);
    }

    @Override
    public IState cancelMiniGame() {
        playerC.resetTurn();
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGamePicker;
    }
}
