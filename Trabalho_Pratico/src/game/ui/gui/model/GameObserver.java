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


    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(int i) {
        game.chooseGameMode(i);
        firePropertyChange(null, false, true);
    }
}
