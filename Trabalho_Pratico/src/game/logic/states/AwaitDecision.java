package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.MathGame;
import game.logic.data.Player;
import game.logic.data.WordGame;
import game.logic.memento.Memento;
import game.utils.Utils;

import java.io.File;
import java.util.Stack;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitDecision extends StateAdapter {
    private final GameData game;
    private final Player playerC;
    private final WordGame word;
    private final MathGame math;
    protected AwaitDecision(GameData game) {
        super(game);
        this.game = game;
        this.playerC = getGame().getPlayerByNum(getGame().getWhoseTurn());
        this.word = getGame().getWordGame();
        this.math = getGame().getMathGame();
    }

    @Override
    public IState setPiece(int option) {
        if(game.isBoardFull()) {
            game.addLog("AwaitDecision - No one won, board full");
            Utils.launchLog("AwaitDecision","No one won, board full");
            return new EndGame(game);
        }
        if(playerC.getIsPerson()) {
            if (!game.setPlayerPiece(playerC, option)) {
                game.addLog("AwaitDecision - Error adding piece on column "+option);
                Utils.launchLog("AwaitDecision","Error adding piece on column "+option);
                return this;
            }
        }
        else {
            int column = Utils.randNum(1,7);
            while(!game.setPlayerPiece(playerC, column)){
                column = Utils.randNum(1,7);
            }
        }
        if(game.hasWon(playerC)) {
            game.addLog("AwaitDecision - "+playerC.getName()+" put a piece on the column "+option);
            game.addLog("AwaitDecision - "+playerC.getName()+" has won");
            Utils.launchLog("AwaitDecision",playerC.getName()+" put a piece on the column "+option);
            Utils.launchLog("AwaitDecision",playerC.getName()+" has won");
            return new EndGame(game);
        }
        game.addLog("AwaitDecision - "+playerC.getName()+" put a piece on the column "+option);
        Utils.launchLog("AwaitDecision",playerC.getName()+" put a piece on the column "+option);
        game.addSnapShot();
        return new AwaitDecision(game);
    }

    @Override
    public IState saveGameFile(File filename, Stack<Memento> stackHist, Stack<Memento> stackRedo) {
        if(!game.validFile(filename)){
            game.setError(true);
            game.addLog("AwaitSaveGameFile - Filename invalid or already exists");
            Utils.launchLog("AwaitSaveGameFile","Filename invalid or already exists");
            return this;
        }
        game.setError(false);
        game.saveGame(filename, stackHist, stackRedo);
        game.addLog("AwaitSaveGameFile - Game Saved");
        Utils.launchLog("AwaitSaveGameFile","Game Saved");
        return new AwaitBeginning(game);
    }

    @Override
    public IState chooseSpecialPiece() {
        if(playerC.getSpecialPiece() > 0)
            return new AwaitSpecialPiece(game);
        return this;
    }

    @Override
    public IState rollback(int num) {
        if(num == -1) {
            game.addLog("AwaitPickingRollback - [ERROR] Insufficient credits or game turn to small");
            Utils.launchLog("AwaitPickingRollback","[ERROR] Insufficient credits or game turn to small");
            return this;
        }
        if(num == 0) {
            return new AwaitDecision(game);
        }
        else {
            int rollback = num;
            game.setRollbackMade(num);
            while (rollback >= 0) {
                game.setPlayerRollBackSP(playerC.getSpecialPiece());
                game.setPlayerRollBack(game.getWhoseTurn());
                rollback--;
            }
            game.addLog("AwaitPickingRollback - "+num+" rollbacks applied");
            Utils.launchLog("AwaitPickingRollback",num+" rollbacks applied");
            game.addSnapShot();
        }
        return new AwaitDecision(game);
    }

    @Override
    public IState startMiniGame() {

        if(playerC.getTurn() == 4) {
            if (game.getMiniGameTurn() == 1) {
                game.startMathGame();
                game.addLog("AwaitDecision - Math mini game will start");
                Utils.launchLog("AwaitDecision", "Math mini game will start");
                game.addLog("AwaitMiniGameAnswer - " + math.getExpression()+ " = " + math.getTotal());
                Utils.launchLog("AwaitMiniGameAnswer", math.getExpression()+ " = " + math.getTotal());
            } else {
                game.startWordsGame();
                game.addLog("AwaitDecision - Words mini game will start");
                Utils.launchLog("AwaitDecision", "Words mini game will start");
                game.addLog("AwaitMiniGameAnswer - " + word.getWordsString());
                Utils.launchLog("AwaitMiniGameAnswer",  word.getWordsString());
            }
            return new AwaitMiniGameAnswer(game);
        }
        return this;
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitDecision; }
}
