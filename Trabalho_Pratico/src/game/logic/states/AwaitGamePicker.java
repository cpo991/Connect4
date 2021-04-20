package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGamePicker extends StateAdapter{

    protected AwaitGamePicker(GameData game) {
        super(game);
    }

    @Override
    public IState chooseMenu() {
        return new AwaitBeginning(getGame());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGamePicker;
    }
}
