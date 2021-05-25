package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.logic.data.WordGame;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitWordsAnswer extends StateAdapter{
    private final GameData game;
    private final WordGame word;
    private final Player playerC;

    protected AwaitWordsAnswer(GameData game) {
        super(game);
        this.game = game;
        this.word = getGame().getWordGame();
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
    }

    @Override
    public IState insertWordsAnswer(String answer) {
        if(answer.equals(word.getWordsString())){
            if(word.getStartTime()+word.getSec() >= System.currentTimeMillis()) {//won
                playerC.setSpecialPiece(playerC.getSpecialPiece() + 1);
                word.setHasWon(true);
                word.clearWords();
                playerC.resetTurn();
                game.addLog("AwaitWordsAnswer - Correct Answer, game Won");
                Utils.launchLog("AwaitWordsAnswer","Correct Answer, game Won");
                return new AwaitDecision(game);
            } //lost
            word.setHasWon(false);
            word.clearWords();
            game.changeWhoseTurn();
            playerC.resetTurn();
            game.addLog("AwaitWordsAnswer - Correct Answer, time expired -> game lost");
            Utils.launchLog("AwaitWordsAnswer","Correct Answer, time expired -> game lost");
            return new AwaitDecision(game);
        } //try again
        if(word.getStartTime()+word.getSec() >= System.currentTimeMillis()) {
            game.addLog("AwaitWordsAnswer - Wrong Answer, try again");
            Utils.launchLog("AwaitWordsAnswer","Wrong Answer, try again");
            return new AwaitWordsAnswer(game);
        }
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitWordsAnswer; }
}
