package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IGUIConstants;
import game.ui.gui.model.GameObserver;
import game.ui.gui.resources.Images;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GUIAwaitPickingNames extends StackPane implements PropertyChangeListener, IGUIConstants {
    private BorderPane bp;
    private StackPane sp;
    private GameObserver game;
    private Images images;
    private Image player1, player2;
    private ImageView ivP1, ivP2;
    private VBox topSP;
    private HBox rootHorizontal;
    private MenuBar menuBar;
    private Menu file;
    private MenuItem itemSave;
    private Menu info;
    private MenuItem about, log;
    private Label labelP1, labelP2, mainLabel;
    private TextField inputP1, inputP2;
    private Button btnStart;
    private VBox mainMenu, menuP1, menuP2;
    private HBox menuHP1, menuHP2;

    public GUIAwaitPickingNames(GameObserver game) throws FileNotFoundException {
        this.game = game;

        this.sp = new StackPane();
        this.bp = new BorderPane();

        this.labelP1 = new Label();
        this.labelP2 = new Label();
        this.mainLabel = new Label();
        this.images = new Images();

        this.rootHorizontal = new HBox();
        this.topSP = new VBox();

        this.mainMenu = new VBox();
        this.menuP1 = new VBox();
        this.menuP2 = new VBox();
        this.menuHP1 = new HBox();
        this.menuHP2 = new HBox();

        this.inputP1 = new TextField();
        this.inputP2 = new TextField();
        this.btnStart = new Button("Save");

        this.menuBar = new MenuBar();
        this.info = new Menu("Info");
        this.about = new MenuItem("About");
        this.log = new MenuItem("Game Log");
        file = new Menu("File");
        itemSave = new MenuItem("Save");

        loadPane();
        propertyChange(null);
    }

    private void loadPane() throws FileNotFoundException {
        setImages();
        setButtons();

        file.getItems().addAll(itemSave);
        info.getItems().addAll(about, log);
        menuBar.getMenus().addAll(file, info);

        mainLabel.setText("Choose Player Name");
        mainLabel.setFont(Font.font(TEXT_FONT, 50));
        mainLabel.setTextFill(Paint.valueOf(LIGHT_GRAY));

        inputP1.setFont(Font.font(TEXT_FONT, 20));
        inputP2.setFont(Font.font(TEXT_FONT, 20));

        labelP1.setText("Player 1");
        labelP1.setFont(Font.font(TEXT_FONT, 40));
        labelP1.setTextFill(Paint.valueOf(LIGHT_GRAY));

        labelP2.setText("Player 2");
        labelP2.setFont(Font.font(TEXT_FONT, 40));
        labelP2.setTextFill(Paint.valueOf(LIGHT_GRAY));

        menuP1.getChildren().addAll(labelP1,inputP1);
        menuP1.setSpacing(50);
        menuP1.setAlignment(Pos.CENTER);

        if(game.getGameMode() == 1)
            menuP2.getChildren().addAll(labelP2,inputP2);
        else
            menuP2.getChildren().add(labelP2);
        menuP2.setSpacing(50);
        menuP2.setAlignment(Pos.CENTER);

        menuHP1.getChildren().addAll(ivP1,menuP1);
        menuHP1.setSpacing(50);
        menuHP1.setAlignment(Pos.CENTER);

        menuHP2.getChildren().addAll(ivP2,menuP2);
        menuHP2.setSpacing(50);
        menuHP2.setAlignment(Pos.CENTER);

        mainMenu.getChildren().addAll(mainLabel, menuHP1, menuHP2, btnStart);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setSpacing(50);

        rootHorizontal.getChildren().add(mainMenu);
        rootHorizontal.setAlignment(Pos.CENTER);

        topSP.setBackground(images.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);

        sp.getChildren().add(topSP);

        bp.setTop(menuBar);
        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setImages() throws FileNotFoundException{

        this.player1 = new Image(new FileInputStream(PLAYER_ICON));
        this.player2 = new Image(new FileInputStream(PLAYER_ICON));

        this.ivP1 = new ImageView(player1);
        this.ivP2 = new ImageView(player2);

        ivP1.setFitWidth(200);
        ivP1.setFitHeight(200);

        ivP2.setFitWidth(200);
        ivP2.setFitHeight(200);

    }

    private void setButtons() {

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

        //Info -> log
        log.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Game Log");
            alert.getDialogPane().setContentText(game.getLogString());
            alert.showAndWait();
        });
        btnConfig(btnStart);

        btnStart.setOnMousePressed(new BTNSTART());
    }

    public void btnConfig(Button btn){
        btn.setOnMouseEntered(event -> btn.setFont(Font.font(TEXT_FONT, 35)));
        btn.setOnMouseExited(event -> btn.setFont(Font.font(TEXT_FONT, 30)));
        btn.setBackground(null);
        btn.setFont(Font.font(TEXT_FONT, 30));
        btn.setTextFill(Color.valueOf(LIGHT_GRAY));
        btn.setMinHeight(70);
    }

    private class BTNSTART implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            game.pickNames(inputP1.getText(), inputP2.getText());
            try {
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitPickingNames)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}

