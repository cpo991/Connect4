package game.logic;

import game.logic.Situation;
import game.logic.StateMachine;
import game.logic.states.IState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import static game.ui.gui.IGUIConstants.PROPERTY_GAME;

public class GameObserver{
    private StateMachine game;
    private final PropertyChangeSupport propertyChangeSupport;

    public GameObserver(StateMachine game) {
        this.game = game;
        propertyChangeSupport = new PropertyChangeSupport(game);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public Situation getCurrentSituation(){ return game.getCurrentSituation(); }

    public void loadGame(File file) {
        game.loadGame(file);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public String getHowToPlayString(){ return game.getHowToPlayString();}

    // ------------------------------------------------------------------------------------   AwaitBeginning
    public void startGame(){
        game.startGame();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public void chooseReplay(){
        game.chooseReplay();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public void pickGame(){
        game.pickGame();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public void exit(){
        game.exit();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public String getLogString(){
        return game.getLogString();
    }
    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(int i) {
        game.chooseGameMode(i);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public void previousMenu(){
        game.previousMenu();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    public void pickNames(String name1, String name2) {
        game.pickNames(name1, name2);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public int getGameMode(){ return game.getGameMode(); }


    // ------------------------------------------------------------------------------------   AwaitDecision
    public void setPiece(int num){
        game.setPiece(num);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public void rollback(int num) {
        game.rollback(num);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public void chooseRollback(){
        game.chooseRollback();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public void startMiniGame() {
        game.startMiniGame();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public void chooseSpecialPiece() {
        game.chooseSpecialPiece();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
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
    public int getPiecePosition(int L, int C){ return game.getPiecePosition(L,C); }
    public int getCurrentCredits(){return game.getCurrentCredits();}
    public int getGameTurn(){return game.getGameTurn();}
    public int getMiniGameTurn() {return game.getMiniGameTurn();}
    // ------------------------------------------------------------------------------------   AwaitMathAnswer
    public void insertMathAnswer(double answer) {
        game.insertMathAnswer(answer);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public String getMathString(){ return game.getMathExpression();}

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer
    public void insertWordsAnswer(String answer) {
        game.insertWordsAnswer(answer);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public String getWordsString(){ return game.getWords();}

    // ------------------------------------------------------------------------------------   AwaitSpecialPiece
    public void setSpecialPiece(int option) {
        game.setSpecialPiece(option);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    // ------------------------------------------------------------------------------------   EndGame
    public void continuePlaying() {
        game.continuePlaying();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }
    public boolean isBoardFull(){ return game.isBoardFull();}
    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    public void startReplay(int i) {
        game.startReplay(i);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    public String getReplaysByNum(int num){ return game.getReplaysByNum(num); }
    public String getReplayPlayer1Name(){ return game.getReplayPlayer1Name();}
    public String getReplayPlayer2Name(){ return game.getReplayPlayer2Name();}

    public int getReplayPlayer1Credits(){ return game.getReplayPlayer1Credits();}
    public int getReplayPlayer2Credits(){ return game.getReplayPlayer2Credits();}

    public int getReplayPlayer1SP(){ return game.getReplayPlayer1SP();}
    public int getReplayPlayer2SP(){ return game.getReplayPlayer2SP();}

    public int getReplayPlayer1Turn(){ return game.getReplayPlayer1Turn();}
    public int getReplayPlayer2Turn(){ return game.getReplayPlayer2Turn();}

    public int getReplayGameMode(){ return game.getReplayGameMode();}
    public String getReplayGameModeString(){ return game.getReplayGameModeString();}
    public String getReplayWhosPlaying(){ return game.getReplayWhosPlaying();}

    public int getReplayPiecePosition(int L, int C) { return game.getReplayPiecePosition(L,C); }
    // ------------------------------------------------------------------------------------   AwaitReplay
    public void nextStep(){
        game.nextStep();
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }

    // ------------------------------------------------------------------------------------   AwaitSaveGameFile

    public void saveGameFile(File file) {
        game.saveGameFile(file);
        propertyChangeSupport.firePropertyChange(PROPERTY_GAME, null, null);
    }



}
