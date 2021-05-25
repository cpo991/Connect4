package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitReplay extends StateAdapter{
    private final GameData game;

    protected AwaitReplay(GameData game) {
        super(game);
        this.game = game;
    }

    @Override
    public IState endReplay() {
        game.setReplayEnd(true);
        return new EndGame(game);
    }

    @Override
    public IState nextStep() {
        if(game.getReplayEnd()) {
            game.addLog("AwaitReplay - Replay Ended");
            Utils.launchLog("AwaitReplay", "Replay Ended");
            return new EndGame(game);
        }
        else{
            game.setReplayTurn(game.getReplayTurn()+1);
            game.addLog("AwaitReplay - Next replay...");
            Utils.launchLog("AwaitReplay","Next replay...");
        }
        return new AwaitReplay(game);
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitReplay; }
}
