package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

public class AwaitMathAnswer extends StateAdapter{
    GameData game = getGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhosTurn());
    private final Player player1 = getGame().getPlayerByNum(1);
    private final Player player2 = getGame().getPlayerByNum(2);
    protected AwaitMathAnswer(GameData game) { super(game); }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitMathAsnwer;
    }
}
