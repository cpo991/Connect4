package game.logic;

import game.logic.data.*;
import game.logic.memento.ICareTaker;
import game.logic.memento.Memento;
import game.logic.states.AwaitBeginning;
import game.logic.states.IState;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

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
    private MathGame math() {return gameData.getMathGame();}
    private WordGame word() {return gameData.getWordGame();}


    // ------------------------------------------------------------------------------------   AwaitBeginning
    public void startGame(){current = current.startGame();}
    public void chooseReplay(){current = current.chooseReplay();}
    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(int option){current = current.chooseGameMode(option);}
    public void previousMenu(){current = current.previousMenu();}

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    public void pickNames(String name){ current = current.pickNames(name); }

    // ------------------------------------------------------------------------------------   AwaitDecision
    public int getGameMode() { return getGameData().getGameType();}
    public String getPlayer1Name(){ return player1().getName();}
    public String getPlayer2Name(){ return player2().getName();}
    public Character getPlayer1Piece(){ return player1().getPiece();}
    public Character getPlayer2Piece(){ return player2().getPiece();}
    public int getPlayer1Credits(){ return player1().getCredits();}
    public int getPlayer2Credits(){ return player2().getCredits();}
    public int getPlayer1Turn() { return player1().getTurn();}
    public int getPlayer2Turn() { return player2().getTurn();}
    public Boolean getsPlayer1Person() { return player1().getIsPerson(); }
    public Boolean getsPlayer2Person() { return player2().getIsPerson(); }
    public Character[][] getBoard(){ return getGameData().getBoardGame();}
    public void setPiece(int option) {
        saveMemento();
        current = current.setPiece(option);
    }
    public void chooseRollback() { current = current.chooseRollback(); }
    public Boolean miniGame() {
        return /*(playerC().getTurn() == 4)*/true;
    }
    public int getPlayerTurn() { return getGameData().getWhoseTurn(); }
    public void startMiniGame() { current = current.startMiniGame(); }
    public Boolean isCurrPlayerPerson() { return playerC().getIsPerson(); }
    public int getPlayer1SP() { return player1().getSpecialPiece();}
    public int getPlayer2SP() { return player2().getSpecialPiece();}
    public int getGameTurn() { return getGameData().getGameTurn(); }
    public void chooseSpecialPiece() { current = current.chooseSpecialPiece();}
    public boolean hasPlayerSpecialPiece() { return playerC().getSpecialPiece()>0;}

    // ------------------------------------------------------------------------------------   AwaitGamePicker
    public void  startWordsGame() { current = current.startWordsGame(); }
    public void startMathGame(){ current = current.startMathGame(); }
    public void cancelMiniGame() { current = current.cancelMiniGame();}

    // ------------------------------------------------------------------------------------   AwaitMathAnswer
    public String getMath() { return math().getExpression();}
    public void insertMathAnswer(double answer) { current = current.insertMathAnswer(answer);}

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer
    public String getWords() { return word().getWordsString();}
    public void insertWordsAnswer(String answer) {current = current.insertWordsAnswer(answer);}

    // ------------------------------------------------------------------------------------   AwaitSpecialPiece
    public void setSpecialPiece(int option) {
        saveMemento();
        current = current.setSpecialPiece(option);
    }
    public void cancelSpecialPiece() { current = current.cancelSpecialPiece();}
    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    public int getCurrentCredits(){
        if(getGameData().getWhoseTurn() == 1)
            return getPlayer1Credits();
        else
            return getPlayer2Credits();
    }

    public void rollback(int num) {
        while (num > 0){
            undo();
            num--;
        }
        current = current.rollback(num);
    }

    // ------------------------------------------------------------------------------------   EndGame
    public void continuePlaying() { current = current.continuePlaying();}


    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    public String getReplaysTitle(){
        List<Replay> replay = getGameData().getReplays();
        String phrase = null;
        int count = 1;
        for(Replay r : replay){
            phrase = count+" - "+r.getTitle()+"\n";
        }
        return phrase;
    }
    public void startReplay(int option) { current = current.startReplay(option);}


    // ------------------------------------------------------------------------------------   AwaitReplay
    public void nextStep(){ current = current.nextStep();}

    // ------------------------------------------------------------------------------------   Memento
    @Override
    public void saveMemento() {
        stackRedo.clear();
        try{
            stackHist.push(getGameData().getMemento());
        } catch(IOException ex) {
            System.err.println("gravaMemento: " + ex);
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
            Memento atual = getGameData().getMemento();
            stackRedo.push(atual);
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
            Memento sredo = stackRedo.pop();
            stackHist.push(getGameData().getMemento());
            getGameData().setMemento(sredo);
        } catch(IOException | ClassNotFoundException ex) {
            System.err.println("redo: " + ex);
            stackHist.clear();
            stackRedo.clear();
        }
    }
}
