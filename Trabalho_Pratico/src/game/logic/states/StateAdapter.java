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

    @Override
    public IState pickNames() { return this; }

    @Override
    public IState chooseMenu() { return this; }

    @Override
    public IState startMathGame() { return this; }

    @Override
    public IState startWordsGame() { return this; }

    @Override
    public IState chooseGameMode() {
        return this;
    }

    @Override
    public IState chooseReplay() {
        return this;
    }

    @Override
    public IState chooseNames() {
        return this;
    }

    @Override
    public IState startGame() {
        return this;
    }

    @Override
    public IState setPiece(int option) { return this; }
}
