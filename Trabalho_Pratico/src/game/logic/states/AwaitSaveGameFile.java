package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.utils.Utils;

import java.io.File;

public class AwaitSaveGameFile extends StateAdapter{
    private final GameData game;
    protected AwaitSaveGameFile(GameData game) {
        super(game);
        this.game = game;
    }

    @Override
    public IState saveGameFile(File filename) {
        if(!game.validFile(filename)){
            game.setError(true);
            game.addLog("AwaitSaveGameFile - Filename invalid or already exists");
            Utils.launchLog("AwaitSaveGameFile","Filename invalid or already exists");
            return new AwaitSaveGameFile(game);
        }
        game.setError(false);
        game.saveGame(filename);
        game.addLog("AwaitSaveGameFile - Game Saved");
        Utils.launchLog("AwaitSaveGameFile","Game Saved");
        return new AwaitBeginning(game);
    }

    @Override
    public IState previousMenu() { return new AwaitDecision(game); }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitSaveGameFile; }
}
