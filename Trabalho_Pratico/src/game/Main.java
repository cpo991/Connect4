package game;

import game.logic.StateMachine;
import game.ui.text.GameUI;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Main {
    public static void main(String[] args) {
        StateMachine stateMachine = new StateMachine();
        GameUI gameUI = new GameUI(stateMachine);
        gameUI.run();
    }
}
