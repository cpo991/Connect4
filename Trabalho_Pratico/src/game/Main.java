package game;

import game.logic.StateMachine;
import game.ui.gui.model.GameObserver;
import game.ui.gui.views.PaneOrganizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static game.ui.gui.IGUIConstants.GAME_ICON;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Main extends Application {
    public static void main(String[] args) {
        //StateMachine stateMachine = new StateMachine();
        //GameUI gameUI = new GameUI(stateMachine);
        //gameUI.run();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage secondaryStage = new Stage();

        GameObserver game = new GameObserver(new StateMachine());
        PaneOrganizer ui = new PaneOrganizer(game);
        //PaneOrganizer ui2 = new PaneOrganizer(jogo);

        Scene scene = new Scene(ui.getRoot(), 960, 690);
        //Scene scene2 = new Scene(ui2.getRoot(), 960,690);

        stage.setMaximized(true);
        stage.setMinWidth(1230);
        stage.setMinHeight(690);

        stage.setTitle("4 In A Row");
        stage.getIcons().add(new Image(new FileInputStream(GAME_ICON)));
        stage.setScene(scene);

        stage.setX(0);
        stage.setY(0);

        stage.show();
    }
}
