package game.ui.gui;

import game.logic.GameObserver;
import game.logic.Situation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

import static game.ui.gui.IGUIConstants.PROPERTY_GAME;

public class GameRoot extends BorderPane {
    private GameObserver game;
    private MenuItem saveItem;
    private MainPane mainPane;
    private String howToPlay;

    public GameRoot(GameObserver game) {
        this.game = game;
        createMainPane();
        menus();
        registerListener();
        refresh();
    }
    private void registerListener(){
        // regista um observador do jogoObservavel
        game.addPropertyChangeListener(PROPERTY_GAME, evt -> {
            refresh();
        });
    }

    private void createMainPane(){
        mainPane = new MainPane(game);
        setCenter(mainPane);
    }

    private void menus() {
        MenuBar menuBar = new MenuBar();
        setTop(menuBar);

        // FILE MENU
        Menu fileMenu = new Menu("File");  // underscore: abre com alt + j

        this.saveItem = new MenuItem("Save Game");
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        fileMenu.getItems().addAll(saveItem,new SeparatorMenuItem(), exitItem);

        saveItem.setOnAction(new SaveGameListener());

        exitItem.setOnAction((ActionEvent e)-> {
            Stage window = (Stage) this.getScene().getWindow();
            fireEvent( new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        // HELP MENU
        Menu helpMenu = new Menu("Info");

        MenuItem howToPlayItem = new MenuItem("How To Play");
        howToPlayItem.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));

        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        MenuItem logItem = new MenuItem("Game Log");
        logItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

        helpMenu.getItems().addAll(howToPlayItem, aboutItem, logItem);

        howToPlayItem.setOnAction(new HowToPlayListener());
        aboutItem.setOnAction(new AboutListener());
        logItem.setOnAction(new LogListener());

        menuBar.getMenus().addAll(fileMenu,helpMenu);
    }


    class SaveGameListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            Stage fileChooseStage = new Stage();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
            fileChooser.setInitialDirectory(new java.io.File("."));
            fileChooser.getExtensionFilters().add(extFilter);
            File ficheiro = fileChooser.showSaveDialog(fileChooseStage);

            if (ficheiro != null) {
                game.saveGameFile(ficheiro);
            }
        }
    }

    class HowToPlayListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("How to play");
            alert.getDialogPane().setContentText(howToPlay);
            alert.showAndWait();
        }
    }

    static class AboutListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("About");
            alert.getDialogPane().setContentText("Trabalho desenvolvido no âmbito da unidade curricular Programação Avançada." +
                    "\nCarolina Oliveira - 2017011988\nAno Letivo 2020/2021");
            alert.showAndWait();
        }
    }

    class LogListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Game Log");
            alert.getDialogPane().setContentText(game.getLogString());
            alert.showAndWait();
        }
    }

    private void refresh() {
        howToPlay = game.getHowToPlayString();
        saveItem.setDisable(!(game.getCurrentSituation() ==  Situation.AwaitDecision));
    }
}
