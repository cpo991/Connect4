package game.logic;

import game.logic.data.*;
import game.logic.memento.ICareTaker;
import game.logic.memento.Memento;
import game.logic.states.AwaitBeginning;
import game.logic.states.IState;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import static game.logic.data.Constants.PLAYER1;
import static game.logic.data.Constants.PLAYER2;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class StateMachine implements ICareTaker {
    private GameData gameData;
    private IState current;
    private Stack<Memento> stackHist = new Stack<>();
    private Stack<Memento> stackRedo = new Stack<>();

    public StateMachine() {
        this.gameData = new GameData();
        current = new AwaitBeginning(gameData);
    }

    private GameData getGameData() {
        return gameData;
    }

    public Situation getCurrentSituation(){ return current.getCurrentSituation(); }

    private Player player1() { return getGameData().getPlayerByNum(1); }
    private Player player2() { return getGameData().getPlayerByNum(2); }
    private Player playerC() { return getGameData().getPlayerByNum(getGameData().getWhoseTurn()); }
    public Boolean getExit() {return gameData.getExit();}
    public String getHowToPlayString(){ return getGameData().getHowToPlayString(getCurrentSituation()); }

    // ------------------------------------------------------------------------------------   AwaitBeginning
    public void startGame(){current = current.startGame();}
    public void chooseReplay(){current = current.chooseReplay();}
    public void pickGame(){current = current.pickGame();}
    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(int option){current = current.chooseGameMode(option);}
    public void previousMenu(){current = current.previousMenu();}

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    public void pickNames(String name1, String name2){ current = current.pickNames(name1, name2); }
    public int getGameMode(){return getGameData().getGameType();}
    // ------------------------------------------------------------------------------------   AwaitDecision
    public void setPiece(int option) { saveMemento(); current = current.setPiece(option); }
    public void chooseRollback() { current = current.chooseRollback(); }
    public void startMiniGame() { current = current.startMiniGame(); }
    public void chooseSpecialPiece() { current = current.chooseSpecialPiece();}
    public void saveGame() { current = current.saveGame();}
    public String getStateGameString(){return getGameData().getStateGame();}
    public String getPlayer1String(){ return PLAYER1 + player1().getPlayerInfoString();}
    public String getPlayer2String(){ return PLAYER2 + player2().getPlayerInfoString();}
    public String getBoardString(){ return getGameData().boardGameString();}
    public Boolean isMiniGame() { return (playerC().getTurn() == 4); }
    public String getPlayerTurnString(){ return playerC().getName();}
    public Boolean isCurrPlayerPerson() { return playerC().getIsPerson(); }
    public int getGameTurn() { return getGameData().getGameTurn(); }
    public boolean hasPlayerSpecialPiece() { return playerC().getSpecialPiece()>0;}
    public String getLogString(){return getGameData().getLogString();}
    public String getGameModeString(){ return getGameData().getGameModeString();}
    public String getP1Name() { return player1().getName();}
    public int getP1Credits() { return player1().getCredits();}
    public int getP1SP() { return player1().getSpecialPiece();}
    public int getP1Turn() { return player1().getTurn();}
    public String getP2Name() { return player2().getName();}
    public int getP2Credits() { return player2().getCredits();}
    public int getP2SP() { return player2().getSpecialPiece();}
    public int getP2Turn() { return player2().getTurn();}
    public boolean hasCredits() {return playerC().getCredits()>0;}
    public int getPiecePosition(int L, int C) { return getGameData().pieceOnPos(L,C); }
    public int getMiniGameTurn(){return getGameData().getMiniGameTurn();}

    // ------------------------------------------------------------------------------------   AwaitGamePicker
    public void  startWordsGame() { current = current.startWordsGame(); }
    public void startMathGame(){ current = current.startMathGame(); }
    public void cancelMiniGame() { current = current.cancelMiniGame();}

    // ------------------------------------------------------------------------------------   AwaitMathAnswer
    public void insertMathAnswer(double answer) { current = current.insertMathAnswer(answer);}
    public String getMathExpression() { return getGameData().getMathExpression();}

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer
    public void insertWordsAnswer(String answer) {current = current.insertWordsAnswer(answer);}
    public String getWords() { return getGameData().getWords();}

    // ------------------------------------------------------------------------------------   AwaitSpecialPiece
    public void setSpecialPiece(int option) { current = current.setSpecialPiece(option); }

    // ------------------------------------------------------------------------------------   EndGame
    public void continuePlaying() { current = current.continuePlaying(); }
    public void exit(){ current = current.exit(); }
    public Boolean isReplay(){return getGameData().getReplay();}
    public String getReplayWinner(){return getGameData().getWinnerName();}
    public Boolean isBoardFull(){ return getGameData().isBoardFull();}

    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    public void startReplay(int option) { current = current.startReplay(option);}
    public String getReplaysTitle(){ return getGameData().getReplaysTitle(); }
    public String getReplaysByNum(int num){ return getGameData().getReplaysByNum(num); }

    // ------------------------------------------------------------------------------------   AwaitReplay
    public void nextStep(){ current = current.nextStep();}
    public String getReplay() {return getGameData().currentReplayState();}
    public String getReplayPlayer1Name(){ return getGameData().getReplayPlayer1Name();}
    public String getReplayPlayer2Name(){ return getGameData().getReplayPlayer2Name();}

    public int getReplayPlayer1Credits(){ return getGameData().getReplayPlayer1Credits();}
    public int getReplayPlayer2Credits(){ return getGameData().getReplayPlayer2Credits();}

    public int getReplayPlayer1SP(){ return getGameData().getReplayPlayer1SP();}
    public int getReplayPlayer2SP(){ return getGameData().getReplayPlayer2SP();}

    public int getReplayPlayer1Turn(){ return getGameData().getReplayPlayer1Turn();}
    public int getReplayPlayer2Turn(){ return getGameData().getReplayPlayer2Turn();}

    public String getReplayGameModeString(){ return getGameData().getReplayGameModeString();}
    public String getReplayWhosPlaying(){ return getGameData().getReplayWhosPlaying();}
    public int getReplayGameMode(){ return getGameData().getReplayGameMode();}

    public int getReplayPiecePosition(int L, int C) { return getGameData().getReplaypieceOnPos(L,C); }

    // ------------------------------------------------------------------------------------   AwaitPickingLoadGame

    public void loadGame(File filename) {current = current.loadGame(filename);}
    public boolean getError() { return getGameData().getError();}

    // ------------------------------------------------------------------------------------   AwaitSaveGameFile

    public void saveGameFile(File filename) {current = current.saveGameFile(filename, stackHist, stackRedo);}

    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    public int getCurrentCredits(){ return playerC().getCredits();}

    public void rollback(int num) {
        if(getCurrentSituation() == Situation.AwaitDecision) {
            if ((getGameData().getGameTurn() - num < 0) || (playerC().getCredits() <= 0) || (playerC().getCredits() < num))
                current = current.rollback(-1);
            if (num == 0)
                current = current.rollback(0);
            else {
                for (int i = 0; i < num; i++) {
                    undo();
                }
                current = current.rollback(num);
            }
        }
    }
    // ------------------------------------------------------------------------------------   Memento
    @Override
    public void saveMemento() {
        stackRedo.clear();
        try{
            stackHist.push(getGameData().getMemento());
        } catch(IOException ex) {
            System.err.println("saveMemento: " + ex);
            stackHist.clear();
            stackRedo.clear();
        }
    }

    @Override
    public void undo() {
        if (stackHist.isEmpty()) {
            return;
        }
        try {
            Memento current = getGameData().getMemento();
            stackRedo.push(current);
            Memento anterior = stackHist.pop();
            getGameData().setMemento(anterior);
        } catch(IOException | ClassNotFoundException ex) {
            System.err.println("undo: " + ex);
            stackHist.clear();
            stackRedo.clear();
        }
    }

    @Override
    public void redo() {
        if (stackRedo.isEmpty()) {
            return;
        }
        try {
            Memento sRedo = stackRedo.pop();
            stackHist.push(getGameData().getMemento());
            getGameData().setMemento(sRedo);
        } catch(IOException | ClassNotFoundException ex) {
            System.err.println("redo: " + ex);
            stackHist.clear();
            stackRedo.clear();
        }
    }

}
