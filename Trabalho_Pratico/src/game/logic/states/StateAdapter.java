package game.logic.states;

import game.logic.data.GameData;
import game.logic.memento.Memento;

import java.io.File;
import java.util.Stack;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public abstract class StateAdapter implements IState {
    private GameData game;

    protected StateAdapter (GameData game) { this.game = game; }

    public GameData getGame() {
        return game;
    }

    // ------------------------------------------------------------------------------------   AwaitBeginning
    @Override
    public IState startGame() {
        return this;
    }

    @Override
    public IState chooseReplay() {
        return this;
    }

    @Override
    public IState pickGame() { return this; }

    // ------------------------------------------------------------------------------------   AwaitGameMode
    @Override
    public IState chooseGameMode(int option) {
        return this;
    }

    @Override
    public IState previousMenu() { return this; }

    @Override
    public IState chooseRollback() { return this; }

    @Override
    public IState startMiniGame() { return this; }

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    @Override
    public IState pickNames(String name1, String name2) { return this; }

    // ------------------------------------------------------------------------------------   AwaitDecision
    @Override
    public IState setPiece(int option) { return this; }

    @Override
    public IState chooseSpecialPiece() { return this; }
    @Override
    public IState saveGame() { return this; }


    // ------------------------------------------------------------------------------------   AwaitGamePicker
    @Override
    public IState startMathGame() { return this; }

    @Override
    public IState startWordsGame() { return this; }

    @Override
    public IState cancelMiniGame() { return this; }

    // ------------------------------------------------------------------------------------   AwaitMathAnswer
    @Override
    public IState insertMathAnswer(double answer) {
        return this;
    }

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer
    @Override
    public IState insertWordsAnswer(String answer) {
        return this;
    }

    // ------------------------------------------------------------------------------------   AwaitSpecialPiece
    @Override
    public IState setSpecialPiece(int option) { return this; }

    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    @Override
    public IState rollback(int num) { return this; }

    // ------------------------------------------------------------------------------------   EndGame
    @Override
    public IState continuePlaying() { return this; }
    @Override
    public IState exit() { return this; }
    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    @Override
    public IState startReplay(int option) { return this; }

    // ------------------------------------------------------------------------------------   AwaitReplay
    @Override
    public IState nextStep() { return this; }
    @Override
    public IState endReplay() { return this; }
    // ------------------------------------------------------------------------------------   AwaitLoadGame

    @Override
    public IState loadGame(File filename) { return this; }
    // ------------------------------------------------------------------------------------   AwaitSaveGameFile

    @Override
    public IState saveGameFile(File filename, Stack<Memento> stackHist, Stack<Memento> stackRedo) {
        return this;
    }
}
