package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.logic.data.WordGame;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitWordsAnswer extends StateAdapter{
    GameData game = getGame();
    private final WordGame word = getGame().getWordGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());

    protected AwaitWordsAnswer(GameData game) { super(game); }

    @Override
    public IState insertWordsAnswer(String answer) {
        if(answer.equals(word.getWordsString())){
            if(word.getStartTime()+word.getSec() >= System.currentTimeMillis()) {//won
                playerC.setSpecialPiece(playerC.getSpecialPiece() + 1);
                word.setHasWon(true);
                word.clearWords();
                playerC.resetTurn();
                game.setPlay(game);
                return new AwaitDecision(game);
            } //lost
            word.setHasWon(false);
            word.clearWords();
            game.changeWhoseTurn();
            playerC.resetTurn();
            game.setPlay(game);
            return new AwaitDecision(game);
        } //try again
        return new AwaitWordsAnswer(game);
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitWordsAnswer; }
}
