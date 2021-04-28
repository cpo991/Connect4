package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;
import game.logic.data.WordGame;
import game.utils.Utils;

import java.io.IOException;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGamePicker extends StateAdapter{
    GameData game = getGame();
    private final MathGame math = getGame().getMathGame();
    private final WordGame word = getGame().getWordGame();

    protected AwaitGamePicker(GameData game) {
        super(game);
    }

    @Override
    public IState startMathGame() {
        math.sortExpression();
        math.setGameNum(1);
        math.setSecond(1800);
        return new AwaitMathAnswer(game);
    }

    @Override
    public IState startWordsGame() {
        word.add5Words();
        word.setSec();
        return new AwaitWordsAnswer(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGamePicker;
    }
}
