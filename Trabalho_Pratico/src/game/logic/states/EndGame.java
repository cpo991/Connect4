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
        getGame().setPlayer1(new Player(1,"Player 1", false, 'Y'));
        getGame().setPlayer2(new Player(2,"Player 2", false, 'R'));
        return new AwaitBeginning(getGame());
    }


    @Override
    public Situation getCurrentSituation() {
        return Situation.EndGame;
    }
}
