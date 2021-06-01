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

    // ------------------------------------------------------------------------------------   AwaitDecision
    public String getGameModeString(){ return game.getGameModeString();}
    public String getPlayerTurnString(){ return game.getPlayerTurnString();}
    public String getP1Name() { return game.getP1Name();}
    public int getP1Credits() { return game.getP1Credits();}
    public int getP1SP() { return game.getP1SP();}
    public int getP1Turn() { return game.getP1Turn();}
    public String getP2Name() { return game.getP2Name();}
    public int getP2Credits() { return game.getP2Credits();}
    public int getP2SP() { return game.getP2SP();}
    public int getP2Turn() { return game.getP2Turn();}
    public boolean hasPlayerSP() { return game.hasPlayerSpecialPiece();}
    public boolean isMiniGame() { return game.isMiniGame();}
    public boolean hasCredits(){ return game.hasCredits();}
    public void setPiece(int num){
        game.setPiece(num);
        firePropertyChange(null, false, true);
    }
    public int getPiecePosition(int L, int C){ return game.getPiecePosition(L,C); }
}
