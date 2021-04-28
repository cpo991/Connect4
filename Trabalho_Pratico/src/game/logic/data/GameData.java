package game.logic.data;

import java.io.*;
import java.util.*;

import static game.logic.data.Constants.*;
import static game.utils.Utils.randNum;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class GameData {
    private List<Replay> replays;
    private Character [][]boardGame;
    private Player Player1, Player2;
    private int gameType; //1,2,3
    private List<Piece> pieces;
    private int playerTurn,gameTurn;
    private final MathGame mathGame;
    private final WordGame wordGame;


    public GameData(){
        this.replays = new ArrayList<>();
        this.boardGame = new Character[Constants.LINE_NUM][COLUMN_NUM];
        this.gameTurn = 0;
        this.gameType = -1;
        this.pieces = new ArrayList<>();
        this.mathGame = new MathGame();
        this.wordGame = new WordGame();
    }

    public WordGame getWordGame() {return wordGame;}
    public MathGame getMathGame() { return mathGame; }

    public int getWhoseTurn() { return playerTurn; }
    public void changeWhoseTurn(){ if( playerTurn == 1) this.playerTurn = 2; else this.playerTurn = 1;}
    public void flipCoin(){
        Random random = new Random();
        this.playerTurn = random.nextInt(2)+1;
    }

    public void initPlayers(){
        setPlayer1(new Player("Player 1", false, 'Y'));
        setPlayer2(new Player("Player 2", false, 'R'));
    }
    public void setPlayer1(Player player) { Player1 = player;}
    public void setPlayer2(Player player) { Player2 = player; }

    public void setGameTurn(int num) {this.gameTurn = num;}
    public int getGameTurn() {return gameTurn;}
    
    public Player getPlayerByNum(int num){ if (num == 1) return Player1; else return Player2;}
    
    public int getGameType(){ return gameType; }

    public void setGameType(int type){ this.gameType = type;}

    public Character[][] getBoardGame() { return boardGame; }

    /**
     * Initializes the board with empty spaces
     *
     */
    public void initBoardGame(){
        for (int L = 0; L < LINE_NUM; L++) {
            for (int C = 0; C < COLUMN_NUM; C++) {
                boardGame[L][C] = ' ';
            }
        }
    }
    /**
     * Goes to the selected column and finds the first line that doesn't have a piece
     *
     * @param C the column selected by the player1 to insert a piece
     */
    public int setPlayerPiece(int C, Player player){
        for (int L = LINE_NUM-1; L >= 0 ; L--) {
            if(boardGame[L][C-1] == ' '){
                this.boardGame[L][C-1] = player.getPiece();
                return L;
            }
        }
        return -1;
    }

    /**
     * Checks if the current player has a sequency of 4 vertical pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequency of 4 vertical pieces
     */
    public Boolean isVertical(Player player){
        Character piece = player.getPiece();
        for (int L = 0; L < LINE_NUM-3; L++) {
            for (int C = 0; C < COLUMN_NUM; C++) {
                if ((boardGame[L][C] == piece) && (boardGame[L+1][C] == piece) &&
                        (boardGame[L+2][C] == piece) && (boardGame[L+3][C] == piece))
                    return true;
            }
        }
        return false;
    }
    /**
     * Checks if the current player has a sequency of 4 horizontal pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequency of 4 horizontal pieces
     */
    public Boolean isHorizontal(Player player){
        Character piece = player.getPiece();
        for (int L = 0; L < LINE_NUM; L++) {
            for (int C = 0; C < COLUMN_NUM-3; C++) {
                if ((boardGame[L][C] == piece) && (boardGame[L][C+1] == piece) &&
                        (boardGame[L][C+2] == piece) && (boardGame[L][C+3] == piece))
                    return true;
            }
        }
        return false;
    }
    /**
     * Checks if the current player has a sequency of 4 diagonal positive pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequency of 4 diagonal positive pieces
     */
    public Boolean isDiagonalPositive(Player player){
        Character piece = player.getPiece();
        for (int L = 3; L < LINE_NUM; L++) {
            for (int C = 0; C < COLUMN_NUM-3; C++) {
                if ((boardGame[L][C] == piece) && (boardGame[L-1][C+1] == piece) &&
                        (boardGame[L-2][C+2] == piece) && (boardGame[L-3][C+3] == piece))
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current player has a sequency of 4 diagonal negative pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequency of 4 diagonal negative pieces
     */
    public Boolean isDiagonalNegative(Player player){
        Character piece = player.getPiece();
        for (int L = 0; L < LINE_NUM-3; L++) {
            for (int C = 0; C < COLUMN_NUM-3; C++) {
                if ((boardGame[L][C] == piece) && (boardGame[L+1][C+1] == piece) &&
                        (boardGame[L+2][C+2] == piece) && (boardGame[L+3][C+3] == piece))
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current player has any sequence of 4 pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequence of 4 pieces, winning the game
     */
    public boolean hasWon(Player player){ // Possibilities: |  -  /   \
        return isVertical(player) || isHorizontal(player) || isDiagonalPositive(player) || isDiagonalNegative(player);
    }

    public void setPieceToList(Piece piece){
        pieces.add(piece);
    }

    public void deleteFromBoard(int L, int C){
        this.boardGame[L][C] = ' ';
    }

    public void removePieces(int num){
        Collections.reverse(pieces);
        for (int i = 0; i < num; i++) {
            deleteFromBoard(pieces.get(i).getL(), pieces.get(i).getC());
            pieces.get(i).setState('R');
        }
        Collections.reverse(pieces);
    }

    //set timer
    //set special piece
}
