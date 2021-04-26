package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingNames extends StateAdapter{


    protected AwaitPickingNames(GameData game) {
        super(game);
    }

    @Override
    public IState pickNames(String name) {
        if(getGame().getGameType() == 1 && (getGame().getPlayer1Name().equals("Player 1"))) { //player 1 doesn't have name
            getGame().getPlayer1().setName(name);
            getGame().getPlayerByNum(1).setIsPerson(true);
            return new AwaitPickingNames(getGame());
        }
        if(getGame().getGameType() == 1 && !getGame().getPlayer1Name().equals("Player 1")){ // player 1 has name
            if(getGame().getPlayer1Name().equals(name)) // player 1 name = name
                return new AwaitPickingNames(getGame());
            else {
                getGame().getPlayerByNum(2).setIsPerson(true);
                getGame().getPlayer2().setName(name);
            }
        }
        if(getGame().getGameType() == 2) {
            getGame().getPlayer1().setName(name);
            getGame().getPlayerByNum(1).setIsPerson(true);
        }
        return new AwaitDecision(getGame());
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingNames;
    }
}
