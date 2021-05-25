package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IConstantsColors;
import game.ui.gui.model.GameObserver;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

public class GUIAwaitDecision extends StackPane implements IConstantsImages, PropertyChangeListener, IConstantsColors {
    private BorderPane bp;
    private StackPane sp;
    private GameObserver game;
    private Button btnNewGame, btnExit, btnHistory, btnLoad;
    private Images images;
    private VBox menu, topSP;
    private HBox rootHorizontal;
    private MenuBar menuBar;
    private Menu file;
    private MenuItem itemSave;
    private Menu info;
    private MenuItem about;

    public GUIAwaitDecision(GameObserver game)  throws FileNotFoundException {
        this.game = game;
        this.bp = new BorderPane();
        this.sp = new StackPane();

        /*this.btnNewGame = new Button("New Game");
        this.btnHistory = new Button("Game History");
        this.btnLoad = new Button("Load Game");
        this.btnExit = new Button("Exit");*/

        this.images = new Images();

        this.menu = new VBox();
        this.rootHorizontal = new HBox();
        this.topSP = new VBox();

        this.menuBar = new MenuBar();
        this.info = new Menu("Info");
        this.about = new MenuItem("About");
        file = new Menu("File");
        itemSave = new MenuItem("Save");
        //loadPane();
        propertyChange(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitDecision)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}
