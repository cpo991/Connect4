package game.logic;

import game.logic.data.GameData;
import game.logic.data.Player;
import game.logic.states.AwaitBeginning;
import game.logic.states.IState;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class StateMachine {
    private final GameData gameData;
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

    public Player player1() { return getGameData().getPlayerByNum(1); }
    public Player player2() { return getGameData().getPlayerByNum(2); }
    public Player playerC() { return getGameData().getPlayerByNum(getGameData().getWhosTurn()); }

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
    public Character[][] getBoard(){ return getGameData().getBoardGame();}
    public void setPiece(int option) { current = current.setPiece(option); }
    public void chooseRollback() {current = current.chooseRollback();}
    public Boolean miniGame() {
        if(getGameData().getWhosTurn() == 1)
            return getPlayer1Turn()==4;
        else
            return getPlayer2Turn()==4;
    }
    public int getPlayerTurn() { return getGameData().getWhosTurn(); }
    public void startMiniGame() { current = current.startMiniGame(); }
    public Boolean isCurrPlayerPerson() { return playerC().getIsPerson(); }
    public int getPlayer1SP() { return player1().getSpecialPiece();}
    public int getPlayer2SP() { return player2().getSpecialPiece();}
    public int getGameTurn() { return getGameData().getGameTurn(); }

    // ------------------------------------------------------------------------------------   AwaitGamePicker
    public void  startWordsGame() { current = current.startWordsGame(); }
    public void startMathGame(){ current = current.startMathGame(); }

    // ------------------------------------------------------------------------------------   AwaitMathAnswer

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer
    public String getWords() { return getGameData().getWordsString();}
    public void insertAnswer() {current = current.insertAnswer();}

    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    public int getCurrentCredits(){
        if(getGameData().getWhosTurn() == 1)
            return getPlayer1Credits();
        else
            return getPlayer2Credits();
    }

    public void rollback(int num) { current = current.rollback(num);}


    // ------------------------------------------------------------------------------------   EndGame
    public void continuePlaying() { current = current.continuePlaying();}




    // ------------------------------------------------------------------------------------   AwaitPickingReplay



    // ------------------------------------------------------------------------------------   AwaitReplay


}
