package game.ui.gui;

import game.logic.GameObserver;
import game.ui.gui.views.*;
import javafx.scene.layout.*;

public class MainPane extends BorderPane {
    private GameObserver game;

    public MainPane(GameObserver game) {
        this.game = game;
        loadPane();
    }

    private void loadPane(){

        GUIAwaitBeginning guiAwaitBeginning = new GUIAwaitBeginning(game);
        GUIAwaitPickingGameMode guiAwaitPickingGameMode = new GUIAwaitPickingGameMode(game);
        GUIAwaitPickingNames guiAwaitPickingNames = new GUIAwaitPickingNames(game);
        GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
        GUIAwaitPickingReplay guiAwaitPickingReplay = new GUIAwaitPickingReplay(game);
        GUIAwaitSpecialPiece guiAwaitSpecialPiece = new GUIAwaitSpecialPiece(game);
        GUIAwaitMiniGameAnswer guiAwaitMiniGameAnswer = new GUIAwaitMiniGameAnswer(game);
        GUIEndGame guiEndGame = new GUIEndGame(game);
        GUIAwaitReplay guiAwaitReplay = new GUIAwaitReplay(game);

        // STACKPANE COM OS PAINEIS DOS ESTADOS
        StackPane center = new StackPane(guiAwaitBeginning, guiAwaitPickingGameMode, guiAwaitPickingNames,
                guiAwaitDecision, guiAwaitSpecialPiece, guiAwaitPickingReplay, guiAwaitMiniGameAnswer, guiEndGame,
                guiAwaitReplay);

        setCenter(center);
    }
}
