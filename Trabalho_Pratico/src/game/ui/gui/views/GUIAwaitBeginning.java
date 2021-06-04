package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IGUIConstants;
import game.logic.GameObserver;
import game.ui.gui.resources.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class GUIAwaitBeginning extends StackPane implements IGUIConstants {
    private GameObserver game;
    private BorderPane bp;
    private StackPane sp;
    private Button btnNewGame, btnExit, btnHistory, btnLoad;
    private ImageLoader imageLoader;
    private VBox menu, topSP;
    private HBox rootHorizontal;

    public GUIAwaitBeginning(GameObserver game){
        this.game = game;

        this.bp = new BorderPane();
        this.sp = new StackPane();

        this.btnNewGame = new Button("New Game");
        this.btnHistory = new Button("Game History");
        this.btnLoad = new Button("Load Game");
        this.btnExit = new Button("Exit");

        this.imageLoader = new ImageLoader();

        this.menu = new VBox();
        this.rootHorizontal = new HBox();
        this.topSP = new VBox();

        loadPane();
        registerListeners();
        refresh();
    }

    private void loadPane() {
        setButtons();

        menu.getChildren().add(imageLoader.getGameLogo());
        menu.getChildren().addAll(btnNewGame, btnHistory, btnLoad, btnExit);
        menu.setAlignment(Pos.CENTER);

        rootHorizontal.getChildren().add(menu);
        rootHorizontal.setAlignment(Pos.CENTER);

        topSP.setBackground(imageLoader.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);

        sp.getChildren().add(topSP);
        bp.setCenter(sp);

        this.getChildren().add(bp);

    }

    private void setButtons(){
        btnConfig(btnNewGame);
        btnConfig(btnHistory);
        btnConfig(btnLoad);
        btnConfig(btnExit);
    }

    public void btnConfig(Button btn){
        btn.setOnMouseEntered(event -> btn.setFont(Font.font(TEXT_FONT, 35)));
        btn.setOnMouseExited(event -> btn.setFont(Font.font(TEXT_FONT, 30)));
        btn.setBackground(null);
        btn.setFont(Font.font(TEXT_FONT, 30));
        btn.setTextFill(Color.valueOf(LIGHT_GRAY));
        btn.setMinHeight(70);
    }

    private void registerListeners(){
        game.addPropertyChangeListener(PROPERTY_GAME, evt -> refresh());

        btnLoad.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            Stage fileChooseStage = new Stage();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new java.io.File("."));
            File gameFile = fileChooser.showOpenDialog(fileChooseStage);
            if (gameFile != null) {
                game.loadGame(gameFile);
            }
        });

        btnNewGame.setOnAction((e)->game.startGame());
        btnHistory.setOnAction((e)->game.chooseReplay());
        btnExit.setOnAction((e)->{
            Stage window = (Stage) this.getScene().getWindow();
            fireEvent( new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }

    private void refresh() {
        this.setVisible(game.getCurrentSituation() == Situation.AwaitBeginning);
    }
}
