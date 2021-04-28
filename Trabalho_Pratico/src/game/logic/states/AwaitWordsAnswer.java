package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.logic.data.WordGame;

public class AwaitWordsAnswer extends StateAdapter{
    GameData game = getGame();
    private final WordGame word = getGame().getWordGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());

    protected AwaitWordsAnswer(GameData game) { super(game); }

    @Override
    public IState insertWordsAnswer(String answer) {
        if(answer.equals(word.getWordsString()))
            playerC.setSpecialPiece(playerC.getSpecialPiece()+1);
        word.clearWords();
        playerC.resetTurn();
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitWordsAnswer; }
}
