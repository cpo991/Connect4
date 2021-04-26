package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGameMode extends StateAdapter{
    protected AwaitGameMode(GameData game) { super(game); }

    // criar jogador com default name


    @Override
    public IState chooseGameMode(int option) {
        getGame().setGameType(option);
        switch (option) {
            case 1, 2 -> {
                return new AwaitPickingNames(getGame());
            }
            case 3 -> {
                return new AwaitDecision(getGame());
            }
        }
        return new AwaitGameMode(getGame());
    }

    @Override
    public IState previousMenu() { return new AwaitBeginning(getGame()); }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGameMode;
    }
}
