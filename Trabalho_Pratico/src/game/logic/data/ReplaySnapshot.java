package game.logic.data;

import java.io.Serializable;

import static game.logic.data.Constants.COLUMN_NUM;
import static game.logic.data.Constants.LINE_NUM;

public class ReplaySnapshot implements Serializable {
    private Player Player1, Player2;
    private Character [][]boardGame = new Character[LINE_NUM][COLUMN_NUM];
    private final int playerTurn;

    public ReplaySnapshot(Player p1, Player p2, Character[][] boardGame, int playerTurn){
        this.Player1 = new Player(p1.getName(),p1.getIsPerson(),p1.getPiece());
        setPlayer1(p1);
        this.Player2 = new Player(p2.getName(),p2.getIsPerson(),p2.getPiece());
        setPlayer2(p2);
        initBoardGame(boardGame);
        this.playerTurn = playerTurn;
    }

    public void initBoardGame(Character[][] board){
        for (int L = 0; L < LINE_NUM; L++) {
            System.arraycopy(board[L], 0, this.boardGame[L], 0, COLUMN_NUM);
        }
    }

    public void setPlayer1(Player player){
        this.Player1.setName(player.getName());
        this.Player1.setSpecialPiece(player.getSpecialPiece());
        this.Player1.setCredits(player.getCredits());
        this.Player1.setNumRollBack(player.getNumRollBack());
        this.Player1.setIsPerson(player.getIsPerson());
        this.Player1.setTurn(player.getTurn());
    }
    public void setPlayer2(Player player){
        this.Player2.setName(player.getName());
        this.Player2.setSpecialPiece(player.getSpecialPiece());
        this.Player2.setCredits(player.getCredits());
        this.Player2.setNumRollBack(player.getNumRollBack());
        this.Player2.setIsPerson(player.getIsPerson());
        this.Player2.setTurn(player.getTurn());
    }

    public Character[][] getBoardGameReplay() { return boardGame; }

    public Player getPlayer2Replay() { return Player2; }

    public Player getPlayer1Replay() { return Player1; }

    public String getWhosPlayingString(){
        if(playerTurn == 1)
            return Player1.getName();
        else
            return Player2.getName();
    }

    public int getReplaypieceOnPos(int L, int C) {
        if(boardGame[L][C] == ' ')
            return 0;
        if(boardGame[L][C] == 'Y')
            return 1;
        else
            return 2;
    }
}
