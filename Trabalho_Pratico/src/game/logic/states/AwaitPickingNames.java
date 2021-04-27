package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingNames extends StateAdapter{
    GameData game = getGame();
    private final Player player1 = getGame().getPlayerByNum(1);
    private final Player player2 = getGame().getPlayerByNum(2);

    protected AwaitPickingNames(GameData game) {
        super(game);
    }

    @Override
    public IState pickNames(String name) {
        if(game.getGameType() == 1 && (player1.getName().equals("Player 1"))) { //player 1 doesn't have name
            player1.setName(name);
            player1.setIsPerson(true);
            return new AwaitPickingNames(game);
        }
        if(game.getGameType() == 1 && !player1.getName().equals("Player 1")){ // player 1 has name
            if(player1.getName().equals(name)) // player 1 name = name
                return new AwaitPickingNames(getGame());
            else {
                player2.setIsPerson(true);
                player2.setName(name);
            }
        }
        if(game.getGameType() == 2) {
            player1.setName(name);
            player1.setIsPerson(true);
        }
        return new AwaitDecision(game);
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingNames;
    }
}
