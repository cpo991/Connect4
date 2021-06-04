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
public class AwaitMiniGameAnswer extends StateAdapter{
    private final GameData game;
    private final WordGame word;
    private final MathGame math;
    private final Player playerC;

    protected AwaitMiniGameAnswer(GameData game) {
        super(game);
        this.game = game;
        this.word = getGame().getWordGame();
        this.math = getGame().getMathGame();
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
                game.addLog("AwaitMiniGameAnswer - Correct Answer, game Won");
                Utils.launchLog("AwaitMiniGameAnswer","Correct Answer, game Won");
                game.changeMiniGameTurn();
                game.changeWhoseTurn();
                return new AwaitDecision(game);
            } //lost
            word.setHasWon(false);
            word.clearWords();
            playerC.resetTurn();
            game.addLog("AwaitMiniGameAnswer - Correct Answer, time expired -> game lost");
            Utils.launchLog("AwaitMiniGameAnswer","Correct Answer, time expired -> game lost");
            game.changeMiniGameTurn();
            game.changeWhoseTurn();
            return new AwaitDecision(game);
        } //try again
        if(word.getStartTime()+word.getSec() >= System.currentTimeMillis()) {
            game.addLog("AwaitMiniGameAnswer - Wrong Answer, try again");
            Utils.launchLog("AwaitMiniGameAnswer","Wrong Answer, try again");
            game.addLog("AwaitMiniGameAnswer - "+word.getWordsString());
            Utils.launchLog("AwaitMiniGameAnswer",word.getWordsString());
            return new AwaitMiniGameAnswer(game);
        }
        return new AwaitDecision(game);
    }

    @Override
    public IState insertMathAnswer(double answer) {
        if (math.getGameNum() < 5) {
            if (math.getTotal() == answer) { //correct answer
                math.setGameNum(math.getGameNum() + 1);
                math.sortExpression();
                game.addLog("AwaitMiniGameAnswer - Correct Answer, new expression sorted");
                Utils.launchLog("AwaitMiniGameAnswer","Correct Answer, new expression sorted");
                game.addLog("AwaitMiniGameAnswer - " + math.getExpression()+ " = " + math.getTotal());
                Utils.launchLog("AwaitMiniGameAnswer", math.getExpression()+ " = " + math.getTotal());
                return new AwaitMiniGameAnswer(game);
            }
            game.addLog("AwaitMiniGameAnswer - Wrong Answer, new expression sorted");
            Utils.launchLog("AwaitMiniGameAnswer","Wrong Answer, new expression sorted");
            game.addLog("AwaitMiniGameAnswer - " + math.getExpression()+ " = " + math.getTotal());
            Utils.launchLog("AwaitMiniGameAnswer", math.getExpression()+ " = " + math.getTotal());
            math.sortExpression();
            return new AwaitMiniGameAnswer(game);
        }
        if (math.getGameNum() == 5) { // wins mini game
            if(math.getStartTime()+math.getSec() >= System.currentTimeMillis()) {
                playerC.setSpecialPiece(playerC.getSpecialPiece() + 1);
                math.setGameNum(1);
                math.setHasWon(true);
                game.addLog("AwaitMiniGameAnswer - Game Won");
                Utils.launchLog("AwaitMiniGameAnswer","Game Won");
            }else {
                game.addLog("AwaitMiniGameAnswer - Game Lost");
                Utils.launchLog("AwaitMiniGameAnswer", "Game Lost");
            }
        }
        math.setHasWon(false);
        playerC.resetTurn();
        game.changeWhoseTurn();
        game.changeMiniGameTurn();
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitMiniGameAnswer; }
}
