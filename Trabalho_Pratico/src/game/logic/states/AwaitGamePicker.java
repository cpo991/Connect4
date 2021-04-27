package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

import java.io.IOException;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGamePicker extends StateAdapter{
    GameData game = getGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhosTurn());
    private final Player player1 = getGame().getPlayerByNum(1);
    private final Player player2 = getGame().getPlayerByNum(2);
    protected AwaitGamePicker(GameData game) {
        super(game);
    }

    @Override
    public IState startMathGame() {


        return new AwaitMathAnswer(game);
    }

    @Override
    public IState startWordsGame() {
        game.add5Words();
        game.setWordsTime();

        return new AwaitWordsAnswer(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGamePicker;
    }
}
