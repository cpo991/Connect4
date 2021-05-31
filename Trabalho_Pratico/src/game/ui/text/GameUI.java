package game.ui.text;

import game.logic.StateMachine;
import game.logic.data.Constants;
import game.utils.Utils;

import static game.utils.Utils.*;

public class GameUI {
    StateMachine stateMachine;
    
    public GameUI(StateMachine stateMachine){this.stateMachine = stateMachine;}
    
    public void run() {
        while(!stateMachine.getExit()) {
            switch (stateMachine.getCurrentSituation()) {
                case AwaitBeginning -> AwaitBeginningUI();
                case AwaitDecision -> AwaitDecisionUI();
                case AwaitGameMode -> AwaitGameModeUI();
                case AwaitGamePicker -> AwaitGamePickerUI();
                case AwaitPickingReplay -> AwaitPickingReplayUI();
                case AwaitPickingNames -> AwaitPickingNamesUI();
                case AwaitReplay -> AwaitReplayUI();
                case EndGame -> EndGameUI();
                case AwaitPickingRollback -> AwaitPickingRollbackUI();
                case AwaitMathAnswer -> AwaitMathAnswerUI();
                case AwaitWordsAnswer -> AwaitWordsAnswerUI();
                case AwaitSpecialPiece -> AwaitSpecialPieceUI();
                case AwaitPickingLoadGame -> AwaitPickingLoadGameUI();
                case AwaitSaveGameFile -> AwaitSaveGameFile();
            }
        }
    }




    private void AwaitBeginningUI() {
        switch (choseOption("New Game", "Game History", "Load Game", "Exit")) {
            case 1 -> stateMachine.startGame();
            case 2 -> stateMachine.chooseReplay();
            case 3 -> stateMachine.pickGame();
            default -> stateMachine.exit();
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
        //stateMachine.pickNames(askString("Insert Name of player: "));
    }

    private void AwaitDecisionUI() {
        printData();
        int option, maxOption;
        boolean mode = false;
        if(stateMachine.isCurrPlayerPerson()) {
            do {
                maxOption = 10;
                System.out.println(Constants.OPTIONS_BOARD);
                System.out.println("8 - Save Game");
                System.out.println("9 - See log");
                System.out.println("10 - Rollback");
                if(stateMachine.hasPlayerSpecialPiece()){
                    System.out.println("11 - Special Piece");
                    maxOption++;
                    if (stateMachine.isMiniGame()) {
                        System.out.println("12 - Play Mini Game");
                        maxOption++;
                    }
                    mode = true;
                }else{
                    if (stateMachine.isMiniGame()) {
                        System.out.println("11 - Play Mini Game");
                        maxOption++;
                        mode = false;
                    }
                }
                option = Utils.askInt("\n> ");
                if(option == 9) {
                    System.out.println("\n----------- LOGS -----------\n");
                    System.out.println(stateMachine.getLogString());
                    printData();
                }
            } while ((option < 0 || option > maxOption || option == 9));
            if(option == 8) stateMachine.saveGame();
            if (option == 10) stateMachine.chooseRollback();
            if(mode) {
                if (option == 11) stateMachine.chooseSpecialPiece();
                if (option == 12) stateMachine.startMiniGame();
                else stateMachine.setPiece(option);
            }else {
                if (option == 11) stateMachine.startMiniGame();
                else stateMachine.setPiece(option);
            }
        }else{
            Utils.next();
            stateMachine.setPiece(-1);
        }
    }

    private void AwaitGamePickerUI() {
        switch (choseOption("Math Game", "Words Game", "Cancel")) {
            case 1 -> stateMachine.startMathGame();
            case 2 -> stateMachine.startWordsGame();
            case 0 -> stateMachine.cancelMiniGame();
        }
    }

    private void AwaitWordsAnswerUI() {
        System.out.println("Write these words with the space");
        System.out.println(stateMachine.getWords());
        stateMachine.insertWordsAnswer(askString("> "));
    }

    private void AwaitMathAnswerUI() {
        System.out.println("Solve this:");
        System.out.println(stateMachine.getMath());
        stateMachine.insertMathAnswer(askDouble("> "));
    }

    private void AwaitSpecialPieceUI() {
        int option;
        do{
            System.out.println("Choose a column to insert the special piece:");
            System.out.println(Constants.OPTIONS_BOARD);
            System.out.println("8 - Cancel");
            option = Utils.askInt("\n> ");
        }while (option<=0 || option > 8);
        if(option == 8)
            stateMachine.setSpecialPiece(0);
        else
            stateMachine.setSpecialPiece(option);
    }

    private void AwaitPickingRollbackUI() {
        System.out.println("Credits to rollback: " + stateMachine.getCurrentCredits());
        System.out.println("Game Turn: " + stateMachine.getGameTurn());
        System.out.println("0 - Cancel");
        int num = askInt("How many rollbacks?");
        if(num<0 || num>stateMachine.getCurrentCredits() || (stateMachine.getGameTurn()-num < 0)) {
            do {
                System.out.println("Insufficient Credits, negative value or insufficient plays to rollback");
                System.out.println("Credits to rollback: " + stateMachine.getCurrentCredits());
                System.out.println("0 - Cancel");
                num = askInt("How many rollbacks?");
            } while (num < 0 || num > stateMachine.getCurrentCredits()|| (stateMachine.getGameTurn()-num < 0));
        }
        if(num == 0)
            stateMachine.previousMenu();
        stateMachine.rollback(num);
    }

    private void EndGameUI() {
        if(stateMachine.isReplay()){
            System.out.println(stateMachine.getReplay());
            System.out.println("\n----------- WINNER -----------\n");
            System.out.println("\t\t\t" + stateMachine.getReplayWinner());
        }else{
            printData();
            if(stateMachine.isBoardFull())
            System.out.println("\n----------- DRAFT -----------\n");
            else{
                System.out.println("\n----------- WINNER -----------\n");
                System.out.println("\t\t\t" + stateMachine.getPlayerTurnString());
            }

        }
        System.out.println("\n---------- End Game ----------\n");
        if (choseOption("Continue", "Exit Game") == 1) {
            stateMachine.continuePlaying();
        } else {
            stateMachine.exit();
        }
    }

    private void AwaitPickingReplayUI() {
        int option;
        do {
            System.out.println("\n----------- REPLAYS -----------\n");
            System.out.println(stateMachine.getReplaysTitle());
            System.out.println("0 - Previous");
            option = Utils.askInt("\n> ");
        }while (option < 0 || option > 5);
        if(option == 0)
            stateMachine.previousMenu();
        else
            stateMachine.startReplay(option);
    }

    private void AwaitReplayUI() {
        System.out.println(stateMachine.getReplay());
        Utils.next();
        stateMachine.nextStep();
    }

    private void printData() {
        System.out.println(stateMachine.getStateGameString());
        CommonData();
    }

    private void CommonData() {
        System.out.print(stateMachine.getPlayer1String());
        System.out.print(stateMachine.getPlayer2String()+"\n");
        System.out.println(stateMachine.getBoardString());
    }

    private void AwaitPickingLoadGameUI() {
        String filename;
        if(stateMachine.getError()) {
            System.out.println("[ERROR] File not found");
        }
        System.out.println("0 - Previous");
        filename = Utils.askString("Filename: ");
        if (filename.equals("0"))
            stateMachine.previousMenu();
        //else
        //stateMachine.loadGame(filename);

    }

    private void AwaitSaveGameFile() {
        String filename;
        if(stateMachine.getError()) {
            System.out.println("[ERROR] File name already exists or doesn't end with .dat");
        }
        System.out.println("0 - Previous");
        filename = Utils.askString("Name of the file to save the current game: ");
        if (filename.equals("0"))
            stateMachine.previousMenu();
        //else
            //stateMachine.saveGameFile(filename);
    }
}
