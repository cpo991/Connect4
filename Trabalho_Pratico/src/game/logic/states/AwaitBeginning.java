package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

import java.util.Random;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitBeginning extends StateAdapter{
    GameData game;
    public AwaitBeginning(GameData game){
        super(game);
        this.game = game;
    }

    @Override
    public IState startGame() {
        getGame().initBoardGame();
        getGame().flipCoin();
        return new AwaitGameMode(getGame());
    }

    @Override
    public IState chooseReplay() {
        return new AwaitReplay(getGame());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitBeginning;
    }
}
