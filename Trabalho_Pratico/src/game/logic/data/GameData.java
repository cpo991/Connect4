package game.logic.data;

import game.utils.Utils;

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
    private Player Player1;
    private Player Player2;
    private int gameType; //1,2,3
    private List<Piece> pieces;
    private List<String> words;
    private int wordsTime;
    private int playerTurn;
    private int gameTurn;


    public GameData(){
        this.replays = new ArrayList<>();
        this.boardGame = new Character[Constants.LINE_NUM][COLUMN_NUM];
        this.words = new ArrayList<>();
        this.wordsTime = 0;
        this.gameType = -1;
        this.pieces = new ArrayList<>();
        this.gameTurn = 0;
    }
    public Player getPlayer1() { return Player1; }
    public Player getPlayer2() { return Player2; }

    public int getWhosTurn() { return playerTurn; }
    public void changeWhosTurn(){ if( playerTurn == 1) this.playerTurn = 2; else this.playerTurn = 1;}
    public void flipCoin(){
        Random random = new Random();
        this.playerTurn = random.nextInt(2)+1;
    }

    public void initPlayers(){
        setPlayer1(new Player(1,"Player 1", false, 'Y'));
        setPlayer2(new Player(2,"Player 2", false, 'R'));
    }
    public void setPlayer1(Player player) { Player1 = player;}
    public void setPlayer2(Player player) { Player2 = player; }

    public int getGameTurn() {return gameTurn;}

    public void setPlayer1Turn() { this.Player1.addTurn();}
    public void setPlayer2Turn() { this.Player2.addTurn();}

    public int getPlayer1Credits() { return Player1.getCredits();}
    public int getPlayer2Credits() { return Player2.getCredits();}

    public void removePlayer1Credits(int num) { Player1.removeCredits(num); }
    public void removePlayer2Credits(int num) { Player2.removeCredits(num); }

    public String getPlayer1Name() { return Player1.getName();}
    public String getPlayer2Name() { return Player2.getName();}

    public Boolean isPlayer1Person() { return Player1.getIsPerson();}
    public Boolean isPlayer2Person() { return Player2.getIsPerson();}

    public void setPlayer1SP(int num){ Player1.setSpecialPiece(num);}
    public void setPlayer2SP(int num){ Player2.setSpecialPiece(num);}
    
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
                boardGame[L][C-1] = player.getPiece();
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

    public String sortWord() {
        int num = randNum(MIN_WORDS,MAX_WORDS);
        String word = null;
        try
        {
            File file = new File("src/game/utils/words.txt");
            //File file=new File("E:\\Documentos_Carolina\\Documents_Carol\\Aulas\\4º_Ano_2º_Semestre\\[PA]Programacao_Avancada\\TP_PA\\Trabalho_Pratico\\src\\game\\utils\\words.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            StringBuilder sb=new StringBuilder();    //constructs a string buffer with no characters
            String line;
            int count=1;
            while((line=br.readLine())!=null)
            {
                if(count == num)
                    sb.append(line);      //appends line to string buffer
                count++;
            }
            fr.close();    //closes the stream and release the resources
            System.out.println("Contents of File: ");
            System.out.println(sb.toString());   //returns a string that textually represents the object
            word = sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return word;
    }

    public Boolean addWord(String word) {
        if(!words.contains(word)) {
            words.add(word);
            System.out.println(word);
            return true;
        }
        return false;
    }

    public void add5Words() {
        int words = 0;
        String newWord = null;
        while (words != 5){
            newWord = sortWord();
            if(addWord(newWord))
                words++;
        }
    }

    public void setWordsTime(){
        this.wordsTime = getWordsString().length();
    }

    public int getWordsTime() { return wordsTime; }

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

    public String getWordsString(){
        String wordsString;
        wordsString = String.join(" ",words);
        System.out.println(wordsString);
        return wordsString;
    }
}
