package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGameMode extends StateAdapter{
    GameData game = getGame();

    protected AwaitGameMode(GameData game) { super(game); }

    @Override
    public IState chooseGameMode(int option) {
        game.setGameType(option);
        switch (option) {
            case 1, 2 -> {
                return new AwaitPickingNames(game);
            }
            case 3 -> {
                return new AwaitDecision(game);
            }
        }
        return new AwaitGameMode(game);
    }


    @Override
    public IState previousMenu() { return new AwaitBeginning(game); }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGameMode;
    }
}
