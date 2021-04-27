package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class EndGame extends StateAdapter{


    protected EndGame(GameData game) {
        super(game);
    }

    @Override
    public IState continuePlaying() {
        return new AwaitBeginning(getGame());
    }


    @Override
    public Situation getCurrentSituation() {
        return Situation.EndGame;
    }
}
