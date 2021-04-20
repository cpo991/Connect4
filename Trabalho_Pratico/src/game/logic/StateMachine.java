package game.logic;

import game.logic.data.GameData;
import game.logic.states.AwaitBeginning;
import game.logic.states.IState;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class StateMachine {
    private GameData gameData;
    private IState current;

    public StateMachine() {
        this.gameData = new GameData();
        current = new AwaitBeginning(gameData);
    }

    public GameData getGameData() {
        return gameData;
    }

    public IState getState() {
        return current;
    }

    public void setState(IState state){
        this.current = state;
    }

    public Situation getCurrentSituation(){ return current.getCurrentSituation(); }

    // ------------------------------------------------------------------------------------   AwaitBeginning
    public void chooseMenu(){current = current.chooseMenu();}

    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(){current = current.chooseGameMode();}

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    public void pickNames(){ current = current.pickNames(); }

    // ------------------------------------------------------------------------------------   AwaitDecision
    public void setPiece(int option) { }

    // ------------------------------------------------------------------------------------   AwaitPiecePlacement

    // ------------------------------------------------------------------------------------   AwaitGamePicker
    public void  startWordsGame(){ current = current.startWordsGame(); }

    public void startMathGame(){ current = current.startMathGame(); }

    // ------------------------------------------------------------------------------------   EndGame

    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    public void chooseReplay(){current = current.chooseReplay();}

    // ------------------------------------------------------------------------------------   AwaitReplay


}
