package game.logic.data;

import java.io.Serializable;

import static game.logic.data.Constants.*;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Player implements Serializable {
    private String name;
    private boolean isPerson;
    private int credits;
    private int turn; // turn for mini game
    private final Character piece;
    private int specialPiece;
    private int numRollBack;

    public Player(String name, boolean isPerson, Character piece){
        this.name = name;
        this.isPerson = isPerson; // 1 - yes / 0 - no
        this.credits = Constants.MAX_CREDITS;
        this.turn = 0;
        this.piece = piece;
        this.specialPiece = 5;
        this.numRollBack = 0;
    }

    public void setSpecialPiece(int specialPiece) { this.specialPiece = specialPiece; }
    public int getSpecialPiece() { return specialPiece;}

    public String getName(){ return name;}
    public void setName(String name){ this.name = name; }

    public boolean getIsPerson(){ return isPerson;}
    public void setIsPerson(Boolean isPerson){ this.isPerson = isPerson;}

    public int getCredits(){ return credits;}
    public void removeCredits(int num){
        this.credits = credits - num;
    }
    public void setCredits(int num){ this.credits = num;}

    public void setTurn(int num) {this.turn = num;}
    public int getTurn(){ return turn; }
    public void addTurn(){
        this.turn++;
    }
    public void resetTurn(){
        this.turn = 0;
    }

    public Character getPiece(){return piece;}

    public int getNumRollBack() { return numRollBack; }
    public void setNumRollBack(int numRollBack) { this.numRollBack = numRollBack; }

    public String getPlayerInfoString(){
        String playerString;
        playerString = name + PIECE + piece;
        if(isPerson)
            playerString = playerString + CREDITS + credits +TURN + turn +
                    SPECIAL_PIECES + specialPiece;
        return playerString;
    }
}
