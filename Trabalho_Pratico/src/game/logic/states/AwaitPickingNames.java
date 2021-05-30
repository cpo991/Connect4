package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;
import game.logic.data.Player;
import game.utils.Utils;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class AwaitPickingNames extends StateAdapter{
    private final GameData game ;
    private final Player player1;
    private final Player player2;

    protected AwaitPickingNames(GameData game) {
        super(game);
        this.game = game;
        this.player1 = getGame().getPlayerByNum(1);
        this.player2 =  getGame().getPlayerByNum(2);
    }

    @Override
    public IState pickNames(String name1, String name2) {
        if(game.getGameType() == 1 && (player1.getName().equals("Player 1"))) { //player 1 doesn't have name
            player1.setName(name1);
            player1.setIsPerson(true);
            game.addLog("AwaitPickingNames - Player 1 name was set to "+name1);
            Utils.launchLog("AwaitPickingNames", "Player 1 name was set to "+name1);
        }
        if(game.getGameType() == 1 && !player1.getName().equals("Player 1")){ // player 1 has name
            if(player1.getName().equals(name2)) // player 1 name = name
                return new AwaitPickingNames(getGame());
            else {
                player2.setIsPerson(true);
                player2.setName(name2);
                game.addLog("AwaitPickingNames - Player 2 name was set to "+name2);
                Utils.launchLog("AwaitPickingNames","Player 2 name was set to "+name2);
            }
        }
        if(game.getGameType() == 2) {
            player1.setName(name1);
            player1.setIsPerson(true);
            game.addLog("AwaitPickingNames - Player 1 name was set to "+name1);
            Utils.launchLog("AwaitPickingNames","Player 1 name was set to "+name1);
        }
        game.addSnapShot();
        return new AwaitDecision(game);
        /*if(game.getGameType() == 1 && (player1.getName().equals("Player 1"))) { //player 1 doesn't have name
            player1.setName(name);
            player1.setIsPerson(true);
            game.addLog("AwaitPickingNames - Player 1 name was set to "+name);
            Utils.launchLog("AwaitPickingNames", "Player 1 name was set to "+name);
            return new AwaitPickingNames(game);
        }
        if(game.getGameType() == 1 && !player1.getName().equals("Player 1")){ // player 1 has name
            if(player1.getName().equals(name)) // player 1 name = name
                return new AwaitPickingNames(getGame());
            else {
                player2.setIsPerson(true);
                player2.setName(name);
                game.addLog("AwaitPickingNames - Player 2 name was set to "+name);
                Utils.launchLog("AwaitPickingNames","Player 2 name was set to "+name);
            }
        }
        if(game.getGameType() == 2) {
            player1.setName(name);
            player1.setIsPerson(true);
            game.addLog("AwaitPickingNames - Player 1 name was set to "+name);
            Utils.launchLog("AwaitPickingNames","Player 1 name was set to "+name);
        }
        game.addSnapShot();
        return new AwaitDecision(game);*/
    }

    @Override
    public Situation getCurrentSituation() {
        return Situation.AwaitPickingNames;
    }
}
