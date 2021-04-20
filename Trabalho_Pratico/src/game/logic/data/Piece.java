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

    public Piece(int order, Character piece, int l, int c) {
        this.order = order;
        this.piece = piece;
        L = l;
        C = c;
    }

    public Character getPiece() { return piece; }

    public int getOrder() { return order; }

    public int getL() { return L; }

    public int getC() { return C; }
}
