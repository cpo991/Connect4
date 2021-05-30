package game.ui.gui.model;

import game.logic.StateMachine;
import game.logic.states.IState;

import java.beans.PropertyChangeSupport;
import java.io.File;

public class GameObserver extends PropertyChangeSupport {
    private StateMachine game;

    public GameObserver(StateMachine game) {
        super(game);
        this.game = game;
    }

    public IState getCurrentState(){
        return game.getState();
    }

    public void loadGame(File file) {
        game.loadGame(file);
    }

    // ------------------------------------------------------------------------------------   AwaitBeginning
    public void startGame(){
        game.startGame();
        firePropertyChange(null, false, true);
    }

    public void chooseReplay(){
        game.chooseReplay();
        firePropertyChange(null, false, true);
    }

    public void pickGame(){
        game.pickGame();
        firePropertyChange(null, false, true);
    }

    public void exit(){
        game.exit();
        firePropertyChange(null, false, true);
    }

    public String getLogString(){
        return game.getLogString();
    }
    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(int i) {
        game.chooseGameMode(i);
        firePropertyChange(null, false, true);
    }

    public void previousMenu(){
        game.previousMenu();
        firePropertyChange(null, false, true);
    }
    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    public void startReplay(int i) {
        game.startReplay(i);
        firePropertyChange(null, false, true);
    }

    public String getReplaysByNum(int num){ return game.getReplaysByNum(num); }

    // ------------------------------------------------------------------------------------   AwaitSaveGameFile

    public void saveGameFile(File file) {
        game.saveGameFile(file);
        firePropertyChange(null, false, true);
    }

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    public int getGameMode(){ return game.getGameMode(); }
    public void pickNames(String name1, String name2) {
        game.pickNames(name1, name2);
        firePropertyChange(null, false, true);
    }
}
