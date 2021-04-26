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
    public void startGame(){current = current.startGame();}
    public void chooseReplay(){current = current.chooseReplay();}
    // ------------------------------------------------------------------------------------   AwaitGameMode
    public void chooseGameMode(int option){current = current.chooseGameMode(option);}
    public void previousMenu(){current = current.previousMenu();}

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    public void pickNames(String name){ current = current.pickNames(name); }

    // ------------------------------------------------------------------------------------   AwaitDecision
    public int getGameMode() { return getGameData().getGameType();}
    public String getPlayer1Name(){ return getGameData().getPlayer1Name();}
    public String getPlayer2Name(){ return getGameData().getPlayer2Name();}
    public Character getPlayer1Piece(){ return getGameData().getPlayer1Piece();}
    public Character getPlayer2Piece(){ return getGameData().getPlayer2Piece();}
    public int getPlayer1Credits(){ return getGameData().getPlayer1Credits();}
    public int getPlayer2Credits(){ return getGameData().getPlayer2Credits();}
    public int getPlayer1Turn() { return getGameData().getPlayerByNum(1).getTurn();}
    public int getPlayer2Turn() { return getGameData().getPlayerByNum(2).getTurn();}
    public Character[][] getBoard(){ return getGameData().getBoardGame();}
    public void setPiece(int option) { current = current.setPiece(option); }
    public void chooseRollback() {current = current.chooseRollback();}
    public Boolean isMinigame() {
        if(getGameData().getWhosTurn() == 1)
            return getPlayer1Turn()==4;
        else
            return getPlayer2Turn()==4;
    }
    public int getPlayerTurn() { return getGameData().getWhosTurn(); }
    public void startMiniGame() { current = current.startMiniGame(); }
    public Boolean isCurrPlayerPerson() { return getGameData().getPlayerByNum(getPlayerTurn()).getIsPerson(); }

    // ------------------------------------------------------------------------------------   AwaitGamePicker
    public void  startWordsGame(){ current = current.startWordsGame(); }
    public void startMathGame(){ current = current.startMathGame(); }

    // ------------------------------------------------------------------------------------   AwaitMathAnswer

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer



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
