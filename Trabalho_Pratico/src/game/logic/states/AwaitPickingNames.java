package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingNames extends StateAdapter{


    protected AwaitPickingNames(GameData game) {
        super(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingNames;
    }
}
