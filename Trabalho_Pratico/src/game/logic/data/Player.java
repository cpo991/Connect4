package game.logic.data;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Player {
    private String name;
    private boolean isPerson;
    private int credits;
    private int turn; // turn for mini game
    private final Character piece;
    private int specialPiece;

    public Player(String name, boolean isPerson, Character piece){
        this.name = name;
        this.isPerson = isPerson; // 1 - yes / 0 - no
        this.credits = Constants.MAX_CREDITS;
        this.turn = 0;
        this.piece = piece;
        this.specialPiece = 5;
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

    public int getTurn(){ return turn; }
    public void addTurn(){
        this.turn++;
    }
    public void resetTurn(){
        this.turn = 0;
    }

    public Character getPiece(){return piece;}
}
