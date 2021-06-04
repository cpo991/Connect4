package game.logic.data;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public interface Constants {

    int COLUMN_NUM = 7;
    int LINE_NUM = 6;

    int MAX_CREDITS = 5;
    int MAX_REPLAYS = 5;

    int MIN_WORDS = 1;
    int MAX_WORDS = 100;

    String GAME_MODE = "\nGame Mode: ";
    String NOW_PLAYING = "\nNow Playing: ";
    String PLAYER1 = "\nPlayer 1: ";
    String PLAYER2 = "\nPlayer 2: ";
    String PIECE = "\t | Piece: ";
    String CREDITS = "\t | Credits: ";
    String TURN = "\t | Turn: ";
    String SPECIAL_PIECES = "\t | Special Pieces: ";
    String GAME_TYPE1 = "Player vs Player";
    String GAME_TYPE2 = "Player vs Computer";
    String GAME_TYPE3 = "Computer vs Computer";

    String OPTIONS_BOARD = " [1] [2] [3] [4] [5] [6] [7] <- Put piece on column";

    String AWAITBEGINNING_HTP = "Select one of the options";
    String AWAITDECISION_HTP = "Select one of the options";
    String AWAITPICKINGGAMEMODE_HTP = "Select one of the options";
    String AWAITPICKINGNAMESHTP = "Select one of the options";
    String AWAITPICKINGREPLAY_HTP = "Select one of the options";
    String AWAITSPECIALPIECE_HTP = "Select one of the options";
}

