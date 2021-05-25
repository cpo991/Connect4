package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;
import game.logic.data.WordGame;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingGame extends StateAdapter{
    private final GameData game;
    private final MathGame math;
    private final WordGame word;
    private final Player playerC;

    protected AwaitPickingGame(GameData game) {
        super(game);
        this.game = game;
        this.math = getGame().getMathGame();
        this.word = getGame().getWordGame();
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    }

    @Override
    public IState startMathGame() {
        math.setHasWon(false);
        math.sortExpression();
        math.setGameNum(1);
        math.setStartTime(System.currentTimeMillis());
        game.addLog("AwaitGamePicker - Math mini game will start");
        Utils.launchLog("AwaitGamePicker","Math mini game will start");
        return new AwaitMathAnswer(game);
    }

    @Override
    public IState startWordsGame() {
        word.setHasWon(false);
        word.add5Words();
        word.setSec();
        word.setStartTime(System.currentTimeMillis());
        game.addLog("AwaitGamePicker - Words mini game will start");
        Utils.launchLog("AwaitGamePicker","Words mini game will start");
        return new AwaitWordsAnswer(game);
    }

    @Override
    public IState cancelMiniGame() {
        playerC.resetTurn();
        game.addLog("AwaitGamePicker - Mini game canceled");
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGamePicker;
    }
}
