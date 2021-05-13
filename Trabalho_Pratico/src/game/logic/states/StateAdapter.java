package game.logic.states;

import game.logic.data.GameData;
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

    @Override
    public IState chooseSpecialPiece() { return this; }

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

    @Override
    public IState cancelSpecialPiece() { return this; }

    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    @Override
    public IState rollback(int num) { return this; }

    // ------------------------------------------------------------------------------------   EndGame
    @Override
    public IState continuePlaying() { return this; }

    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    @Override
    public IState startReplay(int option) { return this; }

    // ------------------------------------------------------------------------------------   AwaitReplay
    @Override
    public IState nextStep() { return null; }

}
