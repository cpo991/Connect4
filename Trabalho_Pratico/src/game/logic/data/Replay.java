package game.logic.data;

import java.util.List;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Replay {
    private final String title;
    private final List<Piece> pieces;
    private final Player player1;
    private final Player player2;

    public Replay(String title, List<Piece> pieces, Player player1, Player player2){
        this.title = title;
        this.pieces = pieces;
        this.player1 = player1;
        this.player2 = player2;
    }

    public List<Piece> getPieces() { return pieces; }

    public Player getPlayer1() { return player1; }

    public Player getPlayer2() { return player2; }

    public String getTitle() { return title; }
}
