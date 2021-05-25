package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitBeginning extends StateAdapter{
    private final GameData game;
    public AwaitBeginning(GameData game){
        super(game);
        this.game = game;
    }

    @Override
    public IState exit() {
        game.setExit(true);
        game.addLog("AwaitBeginning - Game will exit");
        Utils.launchLog("AwaitBeginning","Game will exit");
        return new AwaitBeginning(game);
    }

    @Override
    public IState startGame() {
        game.getFileHistory();
        game.newGame();
        game.flipCoin();
        game.addLog("AwaitBeginning - New game was initiated\n\t\t\tPlayer 1 and Player 2 were created.\n" +
                "\t\t\tThe player who will start playing is the Player "+game.getWhoseTurn());
        Utils.launchLog("AwaitBeginning","New game was initiated\nPlayer 1 and Player 2 were created" +
                "\nThe player who will start playing is the Player "+ game.getWhoseTurn());
        return new AwaitPickingGameMode(game);
    }

    @Override
    public IState chooseReplay() {
        game.getFileHistory();
        return new AwaitPickingReplay(game);
    }

    @Override
    public IState pickGame() {
        game.setError(false);
        return new AwaitPickingLoadGame(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitBeginning;
    }
}
