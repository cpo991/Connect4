package game.ui.text;

import game.logic.StateMachine;
import game.utils.Utils;

public class GameUI {
    StateMachine stateMachine;
    private boolean exit;
    
    public GameUI(StateMachine stateMachine){this.stateMachine = stateMachine;}
    
    public void run() {
        exit = false;
        while(!exit) {
            switch (stateMachine.getCurrentSituation()) {
                case AwaitBeginning:
                    AwaitBeginningUI();
                    break;
                case AwaitDecision:
                    AwaitDecisionUI();
                    break;
                case AwaitGameMode:
                    AwaitGameModeUI();
                    break;
                case AwaitGamePicker:
                    AwaitGamePickerUI();
                    break;
                case AwaitPickingReplay:
                    AwaitPickingReplayUI();
                    break;
                case AwaitPickingNames:
                    AwaitPickingNamesUI();
                    break;
                case AwaitPiecePlacement:
                    AwaitPiecePlacementUI();
                    break;
                case AwaitReplay:
                    AwaitReplayUI();
                    break;
                case AwaitRollback:
                    AwaitRollbackUI();
                    break;
                case EndGame:
                    EndGameUI();
                    break;
            }
        }
    }

    private void AwaitBeginningUI() {
        switch (Utils.choseOption("New Game", "Historic", "Exit")) {
            case 1 -> stateMachine.chooseGameMode();
            case 2 -> stateMachine.chooseReplay();
            default -> exit = true;
        }
    }

    private void AwaitGameModeUI() {
        switch (Utils.choseOption("Player vs Player","Player vs Computer", "Computer vs Computer", "Return")){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                stateMachine.chooseMenu();
                break;
        }
    }

    private void AwaitPickingNamesUI() {
    }

    private void AwaitDecisionUI() {
        int option = Utils.choseOption("1","2", "3", "4", "5", "6","7", "Rollback");
        if(option == 8) {
            //rollback
        }
        if(option == 9) {
            //minijogo
        }
        else
            stateMachine.setPiece(option);
    }

    private void AwaitPiecePlacementUI() {
    }

    private void AwaitRollbackUI() {
    }

    private void AwaitGamePickerUI() {
        switch (Utils.choseOption("Math Game", "Words Game")){
            case 1:
                stateMachine.startMathGame();
                break;
            case 2:
                stateMachine.startWordsGame();
                break;

        }
    }

    private void EndGameUI() {
        switch (Utils.choseOption("Continue", "Exit Game")){
            case 1 -> stateMachine.chooseMenu();
            default -> exit = true;
        }
    }

    private void AwaitPickingReplayUI() {
    }

    private void AwaitReplayUI() {
    }
}
