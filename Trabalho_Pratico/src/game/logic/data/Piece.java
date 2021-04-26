package game.logic.data;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Piece {
    private final int order;
    private final Character piece;
    private final int L;
    private final int C;
    private Character state; // A-active D-Deleted by special piece R-removed by rollback

    public Piece(int order, Character piece, int l, int c, char state) {
        this.order = order;
        this.piece = piece;
        this.L = l;
        this.C = c;
        this.state = state;
    }

    public Character getPiece() { return piece; }

    public int getOrder() { return order; }

    public int getL() { return L; }

    public int getC() { return C; }

    public void setState(char s) { this.state = s; }
}
