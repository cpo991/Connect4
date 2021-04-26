package game.ui.text;

import game.logic.StateMachine;
import game.logic.data.Constants;
import game.logic.states.AwaitWordsAnswer;
import game.utils.Utils;

import static game.utils.Utils.*;

public class GameUI {
    StateMachine stateMachine;
    private boolean exit;
    
    public GameUI(StateMachine stateMachine){this.stateMachine = stateMachine;}
    
    public void run() {
        exit = false;
        while(!exit) {
            switch (stateMachine.getCurrentSituation()) {
                case AwaitBeginning -> AwaitBeginningUI();
                case AwaitDecision -> AwaitDecisionUI();
                case AwaitGameMode -> AwaitGameModeUI();
                case AwaitGamePicker -> AwaitGamePickerUI();
                case AwaitPickingReplay -> AwaitPickingReplayUI();
                case AwaitPickingNames -> AwaitPickingNamesUI();
                case AwaitReplay -> AwaitReplayUI();
                case AwaitRollback -> AwaitRollbackUI();
                case EndGame -> EndGameUI();
                case AwaitPickingRollback -> AwaitPickingRollbackUI();
                case AwaitMathAsnwer -> AwaitMathAnswerUI();
                case AwaitWordsAnswer -> AwaitWordsAnswerUI();
            }
        }
    }

    private void AwaitBeginningUI() {
        switch (choseOption("New Game", "Game History", "Exit")) {
            case 1 -> stateMachine.startGame();
            case 2 -> stateMachine.chooseReplay();
            default -> exit = true;
        }
    }

    private void AwaitGameModeUI() {
        switch (choseOption("Player vs Player", "Player vs Computer", "Computer vs Computer", "Return")) {
            case 1 -> stateMachine.chooseGameMode(1);
            case 2 -> stateMachine.chooseGameMode(2);
            case 3 -> stateMachine.chooseGameMode(3);
            default -> stateMachine.previousMenu();
        }
    }

    private void AwaitPickingNamesUI() {
        stateMachine.pickNames(askString("Insert Name of player: "));
    }

    private void AwaitDecisionUI() {
        printData();
        int option, maxOption=8;
        if(stateMachine.isCurrPlayerPerson()) {
            do {
                System.out.println("Put piece on column: [1] [2] [3] [4] [5] [6] [7]");
                System.out.println("8 - Rollback");
                if (stateMachine.isMinigame()) {
                    System.out.println("9 - Play Mini Game");
                    maxOption = 9;
                }
                option = Utils.askInt("\n> ");
            } while (option < 0 || option > maxOption);
            if (option == 8) {
                stateMachine.chooseRollback();
            }
            if (option == 9) {
                stateMachine.startMiniGame();
            } else
                stateMachine.setPiece(option);
        }else{
            Utils.next();
            stateMachine.setPiece(-1);
        }
    }

    private void AwaitRollbackUI() {
        System.out.println("Credits to rollback: " + stateMachine.getCurrentCredits());
        int num = askInt("How many rollbacks?");
        if(num<=0 || num>=stateMachine.getCurrentCredits()) {
            do {
                System.out.println("Insuficient Credtis or negative value");
                System.out.println("Credits to rollback: " + stateMachine.getCurrentCredits());
                num = askInt("How many rollbacks?");
            } while (num <= 0 || num >= stateMachine.getCurrentCredits());
        }
        stateMachine.rollback(num);
    }

    private void AwaitGamePickerUI() {
        switch (choseOption("Math Game", "Words Game")) {
            case 1 -> stateMachine.startMathGame();
            case 2 -> stateMachine.startWordsGame();
        }
    }

    private void AwaitWordsAnswerUI() {

    }

    private void AwaitMathAnswerUI() {

    }

    private void AwaitPickingRollbackUI() {

    }

    private void EndGameUI() {
        printEndData();
        System.out.println();
        System.out.println("----------- WINNER -----------");
        System.out.println();
        System.out.println("\t\t\t" + getPlayerName());
        System.out.println();
        System.out.println("---------- End Game ----------");
        System.out.println();
        if (choseOption("Continue", "Exit Game") == 1) {
            stateMachine.continuePlaying();
        } else {
            exit = true;
        }
    }

    private void AwaitPickingReplayUI() {
    }

    private void AwaitReplayUI() {
    }

    private void printData() {
        Character [][] boardGame;
        System.out.println();
        System.out.println("Game Mode: " + getGameModeString());
        System.out.println("Now Playing: "+ getPlayerName());
        CommonData();
    }

    private void printEndData() {
        Character [][] boardGame;
        System.out.println();
        System.out.println("Game Mode: " + getGameModeString());
        CommonData();
    }

    private void CommonData() {
        Character[][] boardGame;
        System.out.println("Player 1: "+stateMachine.getPlayer1Name()+
                "\t| Credits: "+stateMachine.getPlayer1Credits()+
                "\t| Piece: "+stateMachine.getPlayer1Piece()+
                "\t| Turn: "+stateMachine.getPlayer1Turn());
        System.out.println("Player 2: "+stateMachine.getPlayer2Name()+
                "\t| Credits: "+stateMachine.getPlayer2Credits()+
                "\t| Piece: "+stateMachine.getPlayer2Piece() +
                "\t| Turn: "+stateMachine.getPlayer2Turn());
        System.out.println();
        boardGame = stateMachine.getBoard();
        for (int L = 0; L < Constants.LINE_NUM; L++) {
            System.out.print("|");
            for (int C = 0; C < Constants.COLUMN_NUM ; C++) {
                System.out.print(" "+boardGame[L][C]+" |");
            }
            System.out.println();
        }
    }

    private String getPlayerName(){
        if(stateMachine.getPlayerTurn() == 1)
            return stateMachine.getPlayer1Name();
        else
            return stateMachine.getPlayer2Name();
    }

    private String getGameModeString() {
        return switch (stateMachine.getGameMode()) {
            case 1 -> "Player vs Player";
            case 2 -> "Player vs Computer";
            case 3 -> "Computer vs Computer";
            default -> "Not Defined";
        };
    }

}
