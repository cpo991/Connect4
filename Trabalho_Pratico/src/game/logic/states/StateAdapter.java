package game.logic.states;

import game.logic.data.GameData;

import java.util.logging.Logger;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public abstract class StateAdapter implements IState {
    private GameData game;
    Logger logger = Logger.getLogger("Console");

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
    public IState pickNames(String name) { return this; }

    // ------------------------------------------------------------------------------------   AwaitDecision
    @Override
    public IState setPiece(int option) { return this; }
    // ------------------------------------------------------------------------------------   AwaitGamePicker
    @Override
    public IState startMathGame() { return this; }

    @Override
    public IState startWordsGame() { return this; }

    // ------------------------------------------------------------------------------------   AwaitMathAnswer

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer


    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    @Override
    public IState rollback(int num) { return this; }

    // ------------------------------------------------------------------------------------   EndGame
    @Override
    public IState continuePlaying() { return this; }

    // ------------------------------------------------------------------------------------   AwaitPickingReplay


    // ------------------------------------------------------------------------------------   AwaitReplay

}
