package game.logic.states;

import game.logic.Situation;

import java.io.IOException;

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
    IState insertAnswer();

    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    IState rollback(int num);


    // ------------------------------------------------------------------------------------   EndGame
    IState continuePlaying();




    // ------------------------------------------------------------------------------------   AwaitPickingReplay


    // ------------------------------------------------------------------------------------   AwaitReplay
}
