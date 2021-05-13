package game.ui.text;

import game.logic.StateMachine;
import game.logic.data.Constants;
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
                case AwaitMathAnswer -> AwaitMathAnswerUI();
                case AwaitWordsAnswer -> AwaitWordsAnswerUI();
                case AwaitSpecialPiece -> AwaitSpecialPieceUI();
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
        int option, maxOption;
        Boolean mode = false;
        if(stateMachine.isCurrPlayerPerson()) {
            do {
                maxOption = 8;
                System.out.println(" [1] [2] [3] [4] [5] [6] [7] <- Put piece on column");
                System.out.println("8 - Rollback");
                if(stateMachine.hasPlayerSpecialPiece()){
                    System.out.println("9 - Special Piece");
                    maxOption++;
                    if (stateMachine.miniGame()) {
                        System.out.println("10 - Play Mini Game");
                        maxOption++;
                    }
                    mode = true;
                }else{
                    if (stateMachine.miniGame()) {
                        System.out.println("9 - Play Mini Game");
                        maxOption++;
                        mode = false;
                    }
                }
                option = Utils.askInt("\n> ");
            } while ((option < 0 || option > maxOption));
            if (option == 8) stateMachine.chooseRollback();
            if(mode) {
                if (option == 9) stateMachine.chooseSpecialPiece();
                if (option == 10) stateMachine.startMiniGame();
                else stateMachine.setPiece(option);
            }else {
                if (option == 9) stateMachine.startMiniGame();
                else stateMachine.setPiece(option);
            }
        }else{
            Utils.next();
            stateMachine.setPiece(-1);
        }
    }

    private void AwaitRollbackUI() {
        System.out.println("Credits to rollback: " + stateMachine.getCurrentCredits());
        System.out.println("Game Turn: " + stateMachine.getGameTurn());
        int num = askInt("How many rollbacks?");
        if(num<0 || num>stateMachine.getCurrentCredits()) {
            do {
                System.out.println("Insuficient Credtis, negative value or insuficient plays to rollback");
                System.out.println("Credits to rollback: " + stateMachine.getCurrentCredits());
                num = askInt("How many rollbacks?");
            } while (num <= 0 || num >= stateMachine.getCurrentCredits());
        }
        stateMachine.rollback(num);
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
            System.out.println(" [1] [2] [3] [4] [5] [6] [7]");
            System.out.println("8 - Cancel");
            option = Utils.askInt("\n> ");
        }while (option<=0 || option > 8);
        if(option == 8)
            stateMachine.cancelSpecialPiece();
        else
            stateMachine.setSpecialPiece(option);
    }

    private void AwaitPickingRollbackUI() {

    }

    private void EndGameUI() {
        printEndData();
        System.out.println("\n----------- WINNER -----------\n");
        System.out.println("\t\t\t" + getPlayerName());
        System.out.println("\n---------- End Game ----------\n");
        if (choseOption("Continue", "Exit Game") == 1) {
            stateMachine.continuePlaying();
        } else {
            exit = true;
        }
    }

    private void AwaitPickingReplayUI() {
        int option;
        do {
            System.out.println("\n----------- REPLAYS -----------\n");
            System.out.println(stateMachine.getReplaysTitle());
            option = Utils.askInt("\n> ");
        }while (option < 0 || option > 5);
        stateMachine.startReplay(option);
    }

    private void AwaitReplayUI() {
    }

    private void printData() {
        System.out.println();
        System.out.println("Game Mode: " + getGameModeString());
        System.out.println("Now Playing: "+ getPlayerName());
        CommonData();
    }

    private void printEndData() {
        System.out.println();
        System.out.println("Game Mode: " + getGameModeString());
        CommonData();
    }

    private void CommonData() {
        Character[][] boardGame;
        System.out.print("Player 1: "+stateMachine.getPlayer1Name()+
                "\t| Piece: "+stateMachine.getPlayer1Piece());
        if(stateMachine.getsPlayer1Person()) {
            System.out.println("\t| Credits: "+stateMachine.getPlayer1Credits()+
                    "\t| Turn: "+stateMachine.getPlayer1Turn()+
                    "\t| Special Pieces: " + stateMachine.getPlayer1SP());
        }
        System.out.print("Player 2: "+stateMachine.getPlayer2Name()+
                "\t| Piece: "+stateMachine.getPlayer2Piece());
        if(stateMachine.getsPlayer2Person()) {
            System.out.println("\t| Credits: " + stateMachine.getPlayer2Credits() +
                    "\t| Turn: " + stateMachine.getPlayer2Turn() +
                    "\t| Special Pieces: " + stateMachine.getPlayer2SP());
        }
        System.out.println();
        boardGame = stateMachine.getBoard();
        System.out.println();
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
