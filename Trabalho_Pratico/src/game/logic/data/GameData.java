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
    private Boolean exit, error, isReplay;
    private Character [][]boardGame;
    private Player Player1, Player2;
    private MathGame mathGame;
    private WordGame wordGame;
    private int gameType, playerTurn, gameTurn,
            playerRollBack, playerRollBackSP, rollbackMade, replayTurn;
    private Boolean replayEnd;
    private List<Replay> replays;
    private Replay currentReplay, gameReplay;
    private List<ReplaySnapshot> replaysCurrGame;
    private static final String Filename = "history.dat";
    private List<String> log;

    public GameData(){
        this.boardGame = new Character[LINE_NUM][COLUMN_NUM];
        this.log = new ArrayList<>();
        this.exit = false;
        this.gameTurn = 0;
        this.gameType = -1;
        this.mathGame = new MathGame();
        this.wordGame = new WordGame();
        this.playerRollBack = 0;
        this.playerRollBackSP = 0;
        this.rollbackMade = 0;
        this.replayTurn = 0;
        this.replays = new ArrayList<>();
        this.replaysCurrGame = new ArrayList<>();
        this.gameReplay = new Replay("",gameType);
        this.error = false;
        initPlayers();
        this.isReplay = false;
    }

    public void newGame(){
        initBoardGame();
        this.exit = false;
        this.gameTurn = 0;
        this.gameType = -1;
        this.playerRollBack = 0;
        this.playerRollBackSP = 0;
        this.rollbackMade = 0;
        this.replayTurn = 0;
        this.replaysCurrGame = new ArrayList<>();
        this.gameReplay = new Replay("",gameType);
        this.error = false;
        this.isReplay = false;
        initPlayers();
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

    public String boardGameString(){
        return boardString(this.boardGame);
    }

    public String getStateGame(){
        String name;
        if(playerTurn == 1) name = Player1.getName();
        else name = Player2.getName();
        return GAME_MODE + getGameModeString() + NOW_PLAYING + name;
    }

    public String getGameModeString() {
        return switch (gameType) {
            case 1 -> GAME_TYPE1;
            case 2 -> GAME_TYPE2;
            case 3 -> GAME_TYPE3;
            default -> "Not Defined";
        };
    }

    public void setPlayerRollBackSP(int PlayerRollBackSP) {
        this.playerRollBackSP = PlayerRollBackSP;
    }

    public void setPlayerRollBack(int playerRollBack) {
        this.playerRollBack = playerRollBack;
    }

    public void setRollbackMade(int rollbackMade) {
        this.rollbackMade = rollbackMade;
    }

    public Boolean getReplayEnd() {
        return replayEnd;
    }

    public void setReplayEnd(Boolean replayEnd) {
        this.replayEnd = replayEnd;
    }

    public Boolean getExit() {
        return exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }

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

    public int pieceOnPos(int L, int C){
        if(boardGame[L][C] == ' ')
            return 0;
        if(boardGame[L][C] == 'Y')
            return 1;
        else
            return 2;
    }

    /**
     * Goes to the selected column and finds the first line that doesn't have a piece
     *
     * @param player the player who's currently playing
     * @param column the column where to put the piece
     */
    public Boolean setPlayerPiece(Player player, int column){
        int result;
        result = setPieceOnBoard(player,column-1);
        if(hasWon(player)) {
            return true;
        }
        if(result == -1) return false; //error adding piece to board
        //had piece to pieces list
        if(player.getIsPerson()) {
            if (player.getTurn() == 5) //didnt chose mini game
                player.resetTurn();
            player.addTurn(); //increase turn
        }
        gameTurn++;
        changeWhoseTurn(); //change player to play
        return true;
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

    public Boolean isBoardFull(){
        for (int L = 0; L < LINE_NUM; L++) {
            for (int C = 0; C < COLUMN_NUM; C++) {
                if(boardGame[L][C] == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the current player has a sequence of 4 vertical pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequence of 4 vertical pieces
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
     * Checks if the current player has a sequence of 4 horizontal pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequence of 4 horizontal pieces
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
     * Checks if the current player has a sequence of 4 diagonal positive pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequence of 4 diagonal positive pieces
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
     * Checks if the current player has a sequence of 4 diagonal negative pieces
     *
     * @param player the player that inserted the piece
     * @returns true if the player has a sequence of 4 diagonal negative pieces
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

    // -------------------------------------------------------------------------------------------------- Memento
    @Override
    public Memento getMemento() throws IOException {
        Memento m;
        m = new Memento(this);
        return m;
    }

    @Override
    public void setMemento(Memento m) throws IOException, ClassNotFoundException {
        GameData play = (GameData) m.getSnapshot();
        this.boardGame = play.boardGame;
        this.Player1 = play.Player1;
        this.Player2 = play.Player2;
        this.playerTurn = play.playerTurn;
        this.gameTurn = play.gameTurn;
        if(this.playerRollBack == 1){
            if(this.playerRollBackSP > play.Player1.getSpecialPiece())
                this.Player1.setSpecialPiece(Player1.getSpecialPiece()-1);
            this.Player1.resetTurn();
            this.Player1.removeCredits(this.rollbackMade);
        }else{
            if(this.playerRollBackSP > play.Player2.getSpecialPiece())
                this.Player2.setSpecialPiece(Player2.getSpecialPiece()-1);
            this.Player2.resetTurn();
            this.Player2.removeCredits(this.rollbackMade);
        }
    }

    // ------------------------------------------------------------------------------------------- History Games
    public List<Replay> getReplays() {
        return replays;
    }

    public void setReplay(Replay replay) { this.replays.add(replay);}

    public String getReplaysByNum(int num){
        return replays.get(num-1).getTitle();
    }

    public void addNewReplay() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(replays.size()>=MAX_REPLAYS)
            replays.remove(0);
        gameReplay.setTitle("<"+dtf.format(now)+">");
        gameReplay.setGameType(gameType);
        setReplay(gameReplay);
    }

    public void saveOnFileHistory(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Filename));
            oos.writeObject(this.replays);
            oos.close();
        } catch(Exception ignored){

        }
    }

    public void getFileHistory(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
            replays = (List<Replay>) ois.readObject();
            ois.close();
        } catch(Exception ignored){

        }
    }

    public void setGameHistory(int option){
        currentReplay = this.replays.get(option-1);
    }

    public String boardGameReplayString(Character[][]board){
        return boardString(board);
    }

    private String boardString(Character[][] board) {
        StringBuilder Board = new StringBuilder("\n");
        for (int L = 0; L < Constants.LINE_NUM; L++) {
            Board.append("|");
            for (int C = 0; C < Constants.COLUMN_NUM ; C++) {
                Board.append(" ").append(board[L][C]).append(" |");
            }
            Board.append("\n");
        }
        return String.valueOf(Board);
    }

    public String currentReplayState() {
        ReplaySnapshot snap = currentReplay.getStackReplay().get(this.replayTurn);
        Player p1 = snap.getPlayer1Replay();
        Player p2 = snap.getPlayer2Replay();
        return GAME_MODE + currentReplay.getGameTypeString() + NOW_PLAYING + snap.getWhosPlayingString()+
                "\nPlayer 1: " + p1.getPlayerInfoString() + "\nPlayer 2: " +p2.getPlayerInfoString() + "\n\n"
                + boardGameReplayString(snap.getBoardGameReplay());
    }

    public void setReplayTurn(int num){
        this.replayTurn = num;
        if(this.replayTurn+1 >= currentReplay.getStackReplay().size())
            setReplayEnd(true);
    }
    public int getReplayTurn(){ return this.replayTurn;}

    public void addSnapShot(){
        gameReplay.addSnapshot(new ReplaySnapshot(Player1, Player2, boardGame, playerTurn));
    }
    public Boolean getReplay() { return isReplay; }
    public void setReplay(Boolean replay) { isReplay = replay; }
    public String getWinnerName(){ return currentReplay.getStackReplay().get(this.replayTurn).getWhosPlayingString(); }

    // -------------------------------------------------------------------------------------------------- Load Game
    public boolean getError() { return error; }
    public void setError(Boolean error) { this.error = error; }

    public Boolean fileExists(String filename){
        if(!filename.endsWith(".dat"))
            return false;
        File tempFile = new File(filename);
        return tempFile.exists();
    }

    public Boolean validFile(File filename){
        if(!filename.getName().endsWith(".dat"))
            return false;
        File tempFile = new File(filename.getName());
        return !tempFile.exists();
    }

    public void loadGame(File filename){
        GameData game;
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            game = (GameData) ois.readObject();
            ois.close();
            setGameLoaded(game);
        } catch(Exception ignored){

        }
    }

    public void saveGame(File filename){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
            oos.close();
        } catch(Exception ignored){

        }
    }

    public void setGameLoaded(GameData game){
        this.exit = game.exit;
        this.boardGame = game.boardGame;
        this.Player1 = game.Player1;
        this.Player2 = game.Player2;
        this.mathGame = game.mathGame;
        this.wordGame = game.wordGame;
        this.gameType = game.gameType;
        this.playerTurn = game.playerTurn;
        this.playerRollBack = game.playerRollBack;
        this.playerRollBackSP = game.playerRollBackSP;
        this.rollbackMade = game.rollbackMade;
        this.replayTurn = game.replayTurn;
        this.replayEnd = game.replayEnd;
        this.replays = game.replays;
        this.currentReplay = game.currentReplay;
        this.replaysCurrGame = game.replaysCurrGame;
    }

    // -------------------------------------------------------------------------------------------------- Logs
    public String getLogString() {
        StringBuilder logAux = new StringBuilder();
        for(String l : log){
            logAux.append("\n").append(l);
        }
        return logAux.toString();
    }

    public void addLog(String l){
        log.add(l);
        saveLog();
    }

    public void saveLog(){
        try {
            FileWriter myWriter = new FileWriter("log.txt");
            String logS = getLogString();
            myWriter.write(logS);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
