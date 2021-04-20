package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingReplay extends StateAdapter{

    protected AwaitPickingReplay(GameData game) {
        super(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingReplay;
    }
}
