package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingReplay extends StateAdapter{
    private final GameData game;
    protected AwaitPickingReplay(GameData game) {
        super(game);
        this.game = game;
    }

    @Override
    public IState startReplay(int option) {
        game.setGameHistory(option);
        game.setReplayEnd(false);
        game.setReplay(true);
        game.addLog("AwaitPickingReplay - Replay " + option +" chosen");
        Utils.launchLog("AwaitPickingReplay", "Replay " + option +" chosen");
        return new AwaitReplay(getGame());
    }

    @Override
    public IState previousMenu() { return new AwaitBeginning(game); }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingReplay;
    }
}
