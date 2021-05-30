package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

import java.io.File;

public class AwaitPickingLoadGame extends StateAdapter{
    private final GameData game;
    protected AwaitPickingLoadGame(GameData game) {
        super(game);
        this.game = game;
    }

    /*@Override
    public IState loadGame(File file){
        if(!game.fileExists(file.getName())){
            game.setError(true);
            game.addLog("AwaitPickingLoadGame - Filename invalid or doesn't exist");
            Utils.launchLog("AwaitPickingLoadGame","Filename invalid or doesn't exist");
            return new AwaitPickingLoadGame(game);
        }
        game.setError(false);
        game.loadGame(file);
        game.addLog("AwaitPickingLoadGame - Game loaded");
        Utils.launchLog("AwaitPickingLoadGame","Game loaded");
        return new AwaitDecision(game);
    }*/


    @Override
    public IState previousMenu() { return new AwaitBeginning(game); }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingLoadGame;
    }
}
