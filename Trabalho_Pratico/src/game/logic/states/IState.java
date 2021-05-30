package game.logic.states;

import game.logic.Situation;

import java.io.File;
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
    IState pickGame();
    // ------------------------------------------------------------------------------------   AwaitGameMode
    IState chooseGameMode(int option);
    IState previousMenu();

    // ------------------------------------------------------------------------------------   AwaitPickingNames
    IState pickNames(String name1, String name2);

    // ------------------------------------------------------------------------------------   AwaitDecision
    IState setPiece(int option);
    IState chooseRollback();
    IState startMiniGame();
    IState chooseSpecialPiece();
    IState saveGame();


    // ------------------------------------------------------------------------------------   AwaitGamePicker
    IState startMathGame();
    IState startWordsGame();
    IState cancelMiniGame();
    // ------------------------------------------------------------------------------------   AwaitMathAnswer
    IState insertMathAnswer(double answer);

    // ------------------------------------------------------------------------------------   AwaitWordsAnswer
    IState insertWordsAnswer(String answer);

    // ------------------------------------------------------------------------------------   AwaitSpecialPiece
    IState setSpecialPiece(int option);
    // ------------------------------------------------------------------------------------   AwaitPickingRollback
    IState rollback(int num);
    IState exit();

    // ------------------------------------------------------------------------------------   EndGame
    IState continuePlaying();


    // ------------------------------------------------------------------------------------   AwaitPickingReplay
    IState startReplay(int option);

    // ------------------------------------------------------------------------------------   AwaitReplay
    IState nextStep();
    IState endReplay();

    // ------------------------------------------------------------------------------------   AwaitPickingLoadGame
    IState loadGame(File filename);

    // ------------------------------------------------------------------------------------   AwaitSaveGameFile
    IState saveGameFile(File filename);
}
