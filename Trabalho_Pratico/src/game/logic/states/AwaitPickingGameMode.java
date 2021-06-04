package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingGameMode extends StateAdapter{
    private final GameData game;

    protected AwaitPickingGameMode(GameData game) {
        super(game);
        this.game = game;
    }

    @Override
    public IState chooseGameMode(int option) {
        game.setGameType(option);
        game.addLog("AwaitGamePicker - Game Mode was set to: "+game.getGameModeString());
        Utils.launchLog("AwaitGameMode","Game Mode was set to: "+game.getGameModeString());
        switch (option) {
            case 1, 2 -> {
                return new AwaitPickingNames(game);
            }
            case 3 -> {
                return new AwaitDecision(game);
            }
        }
        return this;
    }


    @Override
    public IState previousMenu() { return new AwaitBeginning(game); }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitPickingGameMode; }
}
