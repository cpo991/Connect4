package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IConstantsColors;
import game.ui.gui.model.GameObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;

public class GUIAwaitBeginning extends StackPane implements PropertyChangeListener, IConstantsColors {
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

    public GUIAwaitBeginning(GameObserver game) throws FileNotFoundException {
        this.game = game;

        this.bp = new BorderPane();
        this.sp = new StackPane();

        this.btnNewGame = new Button("New Game");
        this.btnHistory = new Button("Game History");
        this.btnLoad = new Button("Load Game");
        this.btnExit = new Button("Exit");

        this.images = new Images();

        this.menu = new VBox();
        this.rootHorizontal = new HBox();
        this.topSP = new VBox();

        this.menuBar = new MenuBar();
        this.info = new Menu("Info");
        this.about = new MenuItem("About");
        file = new Menu("File");
        itemSave = new MenuItem("Save");

        loadPane();
        propertyChange(null);
    }

    private void loadPane() {

        setButtons();

        file.getItems().addAll(itemSave);
        info.getItems().add(about);
        menuBar.getMenus().addAll(file, info);

        menu.getChildren().add(images.getPlanetBound());
        menu.getChildren().addAll(btnNewGame, btnHistory, btnLoad, btnExit);
        menu.setAlignment(Pos.CENTER);

        rootHorizontal.getChildren().add(menu);
        rootHorizontal.setAlignment(Pos.CENTER);

        topSP.setBackground(images.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);

        sp.getChildren().add(topSP);

        bp.setTop(menuBar);
        bp.setCenter(sp);

        this.getChildren().add(bp);

    }

    private void setButtons(){

        //File -> Save
        itemSave.disableProperty().setValue(true);
        itemSave.setText("Save Game");


        //Info -> about
        about.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("About");
            alert.getDialogPane().setContentText("Trabalho desenvolvido no âmbito da unidade curricular Programação Avançada." +
                    "\nCarolina Oliveira - 2017011988\nAno Letivo 2020/2021");
            alert.showAndWait();
        });

        btnConfig(btnNewGame);
        btnConfig(btnHistory);
        btnConfig(btnLoad);
        btnConfig(btnExit);

        btnLoad.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            Stage fileChooseStage = new Stage();
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            File ficheiro = fileChooser.showOpenDialog(fileChooseStage);

            if (ficheiro != null) {
                game.loadGame(ficheiro);
            }

            try {
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        btnNewGame.setOnMousePressed(new BTNNEWGAME());
        btnHistory.setOnMousePressed(new BTNHISTORY());
        btnExit.setOnMousePressed(new BTNEXIT());
    }

    public void btnConfig(Button btn){
        btn.setOnMouseEntered(event -> btn.setFont(Font.font(TEXT_FONT, 35)));
        btn.setOnMouseExited(event -> btn.setFont(Font.font(TEXT_FONT, 30)));
        btn.setBackground(null);
        btn.setFont(Font.font(TEXT_FONT, 30));
        btn.setTextFill(Color.valueOf(LIGHT_GRAY));
        btn.setMinHeight(70);
    }


    class BTNNEWGAME implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            /*game.startGame();
            try{
                GUIAwaitPickingGameMode guiAwaitPickingGameMode = new GUIAwaitPickingGameMode(game);
                getChildren().add(guiAwaitPickingGameMode);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }*/
        }
    }

    class BTNHISTORY implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            game.startGame();
            try{
                GUIAwaitPickingGameMode guiAwaitPickingGameMode = new GUIAwaitPickingGameMode(game);
                getChildren().add(guiAwaitPickingGameMode);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

    }

    class BTNEXIT implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            System.exit(0);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitBeginning)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}
