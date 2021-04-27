package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

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
        game.initBoardGame();
        game.initPlayers();
        game.flipCoin();
        return new AwaitGameMode(game);
    }

    @Override
    public IState chooseReplay() {
        return new AwaitReplay(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitBeginning;
    }
}
