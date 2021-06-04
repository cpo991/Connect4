package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class EndGame extends StateAdapter{
    private GameData game;

    protected EndGame(GameData game) {
        super(game);
        this.game = game;
    }

    @Override
    public IState exit() {
        if(!game.getReplay()){
            game.addSnapShot();
            game.addNewReplay();
            game.saveOnFileHistory();
            game.setExit(true);
            game.addLog("EndGame - New Replay was saved to history file, program will end");
            Utils.launchLog("EndGame","New Replay was saved to history file, program will end");
        }
        else{
            game.addLog("EndGame - Replay Ended");
            Utils.launchLog("EndGame","Replay Ended");
        }
        return this;
    }

    @Override
    public IState continuePlaying() {
        if(!game.getReplay()) {
            game.addSnapShot();
            game.addNewReplay();
            game.saveOnFileHistory();
            game.addLog("EndGame - New Replay was saved to history file");
            Utils.launchLog("EndGame", "New Replay was saved to history file");
        }
        else{
            game.addLog("EndGame - Replay Ended");
            Utils.launchLog("EndGame", "Replay Ended");
        }
        game.resetGame();
        return new AwaitBeginning(game);
    }


    @Override
    public Situation getCurrentSituation() {
        return Situation.EndGame;
    }
}
