package game.logic.data;

import game.logic.memento.IMementoOriginator;
import game.logic.memento.Memento;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static game.logic.data.Constants.*;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class GameData implements Serializable, IMementoOriginator {
    private List<Replay> replays;
    private Character [][]boardGame;
    private Player Player1, Player2;
    private int gameType; //1,2,3
    private List<Piece> pieces;
    private List<Piece> piecesReplay;
    private int playerTurn,gameTurn;
    private final MathGame mathGame;
    private final WordGame wordGame;
    private GameData play;


    public GameData(){
        this.replays = new ArrayList<>();
        this.boardGame = new Character[Constants.LINE_NUM][COLUMN_NUM];
        this.gameTurn = 0;
        this.gameType = -1;
        this.pieces = new ArrayList<>();
        this.mathGame = new MathGame();
        this.wordGame = new WordGame();
    }

    public void setPlay(GameData play) { this.play = play; }
    public GameData getPlay() { return play; }

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
     * Deletes a piece from board putting an empty space
     * @param C column of the piece
     * @param L line of the piece
     */
    public void deleteFromBoard(int L, int C){
        this.boardGame[L][C] = ' ';
    }

    /**
     * Goes to the selected column and finds the first line that doesn't have a piece
     *
     * @param player the player who's currently playing
     * @param column the column where to put the piece
     */
    public int setPlayerPiece(Player player, int column){
        int result;
        result = setPieceOnBoard(player,column-1);
        if(result == -1) return -1; //error adding piece to board
        if(hasWon(player)) return 0;
        //had piece to pieces list
        setPieceToList(new Piece(gameTurn,player.getPiece(), result, column-1, 'A'), player);
        if(player.getIsPerson()) {
            if (player.getTurn() == 5) //didnt choosed mini game
                player.resetTurn();
            player.addTurn(); //increase turn
        }
        gameTurn++;
        changeWhoseTurn(); //change player to play
        return 1;
    }

    /**
     * Goes to the selected column and finds the first line that doesn't have a piece
     *
     * @param C the column selected by the player1 to insert a piece
     */
    public int setPieceOnBoard(Player player, int C){
        for (int L = LINE_NUM-1; L >= 0 ; L--) {
            if(boardGame[L][C] == ' '){
                this.boardGame[L][C] = player.getPiece();
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

    public void setPieceToList(Piece piece, Player player){
        for(Piece p : pieces){ //if exists
            if (p.getC() == piece.getC() && piece.getL() == p.getL()) {
                p.setState('A');
                p.setPiece(player.getPiece());
                return;
            }
        }
        pieces.add(piece);
    }

    /*public void removePieces(int num, Player player){
        int count = 0;
        Collections.reverse(pieces);
        List<Piece> aux = new ArrayList<>(pieces);
        int turn = pieces.get(0).getOrder();
        for(Piece p : pieces) {
            if(turn != p.getOrder()) count++;
            if (count <= num) {
                if (p.getState() == 'A') {
                    deleteFromBoard(p.getL(), p.getC());
                    aux.remove(p);
                    //Add new piece with R for replay
                    count++;
                }
                if (p.getState() == 'D') {
                    setPieceOnBoard(player, p.getC());
                    setPieceToList(p,player);
                    //Add new piece with R for replay
                }
            }
            turn = p.getOrder();
        }
        pieces = aux;
        Collections.reverse(pieces);
    }*/

    /**
     * Goes to the selected column and deletes all the pieces in that column
     *
     * @param C the column selected by the player1 to insert a piece
     */
    public void setPlayerSpecialPiece(int C){
        for (int L = 0; L < LINE_NUM; L++) {
            boardGame[L][C] = ' ';
        }
    }

    public void updatePieceList(int C,Player player){
        List<Piece> aux = new ArrayList<>(piecesReplay);
        for(Piece p: piecesReplay){
            if(p.getC() == C && p.getState() == 'A'){ //Add piece to pieces list for replay
                aux.add(new Piece(gameTurn, player.getPiece(), p.getL(), p.getC(), 'D'));
            }
        }
        setPlayerSpecialPiece(C);
        piecesReplay = aux;
    }



    // -------------------------------------------------------------------------------------------------- Memento
    @Override
    public Memento getMemento() throws IOException {
        Memento m = new Memento(play);
        return m;
    }

    @Override
    public void setMemento(Memento m) throws IOException, ClassNotFoundException {
        play = (GameData) m.getSnapshot();
    }


    // ------------------------------------------------------------------------------------------- History Games
    public List<Replay> getReplays() {
        return replays;
    }

    public void addReplay(Replay replay){
        replays.add(replay);
    }

    public void addReplay() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(piecesReplay.size()>=5){
            //remove first
        }
        addReplay(new Replay("<"+dtf.format(now)+">",piecesReplay,Player1,Player2));
        //save on file
    }
}
