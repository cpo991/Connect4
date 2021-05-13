package game.logic.data;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Piece {
    private final int order;
    private Character piece; // Y - yellow R - red S - special
    private final int L;
    private final int C;
    private Character state; // A-active D-Deleted by special piece R-removed by rollback N - new added by rollback

    public Piece(int order, Character piece, int l, int c, char state) {
        this.order = order;
        this.piece = piece;
        this.L = l;
        this.C = c;
        this.state = state;
    }

    public Character getPiece() { return piece; }
    public void setPiece(Character piece) { this.piece = piece; }

    public int getOrder() { return order; }

    public int getL() { return L; }
    public int getC() { return C; }

    public Character getState() {return state;}
    public void setState(Character state) { this.state = state; }
}
