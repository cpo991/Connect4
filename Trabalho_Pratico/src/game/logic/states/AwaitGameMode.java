package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitGameMode extends StateAdapter{
    GameData game = getGame();
    private final Player playerC = getGame().getPlayerByNum(getGame().getWhosTurn());
    private final Player player1 = getGame().getPlayerByNum(1);
    private final Player player2 = getGame().getPlayerByNum(2);

    protected AwaitGameMode(GameData game) { super(game); }

    @Override
    public IState chooseGameMode(int option) {
        getGame().setGameType(option);
        switch (option) {
            case 1, 2 -> {
                return new AwaitPickingNames(game);
            }
            case 3 -> {
                return new AwaitDecision(game);
            }
        }
        return new AwaitGameMode(game);
    }

    @Override
    public IState previousMenu() { return new AwaitBeginning(game); }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitGameMode;
    }
}
