package game.logic.states;

import game.logic.Situation;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public interface IState {

    IState chooseGameMode();

    IState chooseReplay();

    IState chooseNames();

    IState startGame();

    IState chooseMenu();

    IState startMathGame();

    IState startWordsGame();

    Situation getCurrentSituation();

    IState pickNames();

    IState setPiece(int option);

}
