package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGameMode extends StateAdapter{
    protected AwaitGameMode(GameData game) { super(game); }

    // criar jogador com default name

    @Override
    public IState chooseMenu(){
        return new AwaitBeginning(getGame());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGameMode;
    }
}
