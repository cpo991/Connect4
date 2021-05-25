package game.ui.gui.views;

import game.ui.gui.model.GameObserver;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;

public class PaneOrganizer extends BorderPane {
    StackPane areaUtil;

    public PaneOrganizer(GameObserver game) throws FileNotFoundException {
        areaUtil = new GUIAwaitBeginning(game);
    }

    public StackPane getRoot(){
        return areaUtil;
    }
}
