package game.logic.states;

import game.logic.Situation;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public interface IState {

    Situation getCurrentSituation();

    // ------------------------------------------------------------------------------------   AwaitBeginning
    IState startGame();
    IState chooseReplay();

    // ------------------------------------------------------------------------------------   AwaitGameMode
    IState chooseGameMode(int option);
    IState previousMenu();

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    IState pickNames(String name);

    // ------------------------------------------------------------------------------------   AwaitDecision
    IState setPiece(int option);
    IState chooseRollback();
    IState startMiniGame();

    // ------------------------------------------------------------------------------------   AwaitGamePicker
    IState startMathGame();
    IState startWordsGame();

    // ------------------------------------------------------------------------------------   AwaitMathAnswer

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer


    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    IState rollback(int num);


    // ------------------------------------------------------------------------------------   EndGame
    IState continuePlaying();




    // ------------------------------------------------------------------------------------   AwaitPickingReplay


    // ------------------------------------------------------------------------------------   AwaitReplay
}
