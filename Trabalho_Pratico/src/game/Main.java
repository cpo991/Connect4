package game;

import game.logic.StateMachine;
import game.logic.GameObserver;
import game.ui.gui.GameRoot;
import game.ui.gui.MainPane;
import javafx.application.Application;
import javafx.application.Platform;
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
        StateMachine stateMachine = new StateMachine();
        GameObserver game = new GameObserver(stateMachine);

        GameRoot gameRoot = new GameRoot(game);

        Scene scene = new Scene(gameRoot);

        stage.setMaximized(true);
        stage.setMinWidth(1230);
        stage.setMinHeight(690);

        stage.setTitle("Connect 4");
        stage.getIcons().add(new Image(new FileInputStream(GAME_ICON)));
        stage.setScene(scene);

        stage.setX(0);
        stage.setY(0);
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.show();
    }
}
