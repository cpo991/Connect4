package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IGUIConstants;
import game.logic.GameObserver;
import game.ui.gui.resources.ImageLoader;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GUIAwaitPickingNames extends StackPane implements IGUIConstants {
    private BorderPane bp;
    private StackPane sp;
    private GameObserver game;
    private ImageLoader imageLoader;
    private ImageView imageP1, imageP2;
    private VBox topSP;
    private HBox rootHorizontal;
    private Label labelP1, labelP2, mainLabel;
    private TextField inputP1, inputP2;
    private Button btnStart;
    private VBox mainMenu, menuP1, menuP2;
    private HBox menuHP1, menuHP2;

    public GUIAwaitPickingNames(GameObserver game){
        this.game = game;

        this.sp = new StackPane();
        this.bp = new BorderPane();

        this.labelP1 = new Label();
        this.labelP2 = new Label();
        this.mainLabel = new Label();
        this.imageLoader = new ImageLoader();

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
        loadPane();
        registerObserver();
        refresh();
    }

    private void loadPane() {
        setImages();
        setButtons();

        mainLabel.setText("Choose Player Name");
        mainLabel.setFont(Font.font(TEXT_FONT, 50));
        mainLabel.setTextFill(Paint.valueOf(LIGHT_GRAY));

        inputP1.setFont(Font.font(TEXT_FONT, 20));
        inputP1.setTooltip(new Tooltip("Insert Player 1 name"));
        inputP2.setFont(Font.font(TEXT_FONT, 20));
        inputP2.setTooltip(new Tooltip("Insert Player 2 name"));

        labelP1.setText("Player 1");
        labelP1.setFont(Font.font(TEXT_FONT, 40));
        labelP1.setTextFill(Paint.valueOf(LIGHT_GRAY));

        labelP2.setText("Player 2");
        labelP2.setFont(Font.font(TEXT_FONT, 40));
        labelP2.setTextFill(Paint.valueOf(LIGHT_GRAY));

        menuP1.getChildren().addAll(labelP1,inputP1);
        menuP1.setSpacing(50);
        menuP1.setAlignment(Pos.CENTER);

        menuP2.getChildren().addAll(labelP2,inputP2);
        menuP2.setSpacing(50);
        menuP2.setAlignment(Pos.CENTER);

        menuHP1.getChildren().addAll(imageP1,menuP1);
        menuHP1.setSpacing(50);
        menuHP1.setAlignment(Pos.CENTER);

        menuHP2.getChildren().addAll(imageP2,menuP2);
        menuHP2.setSpacing(50);
        menuHP2.setAlignment(Pos.CENTER);

        mainMenu.getChildren().addAll(mainLabel, menuHP1, menuHP2, btnStart);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setSpacing(50);

        rootHorizontal.getChildren().add(mainMenu);
        rootHorizontal.setAlignment(Pos.CENTER);

        topSP.setBackground(imageLoader.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);

        sp.getChildren().add(topSP);

        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setImages() {
        this.imageP1 = imageLoader.getPlayer1();
        this.imageP2 = imageLoader.getPlayer2();

        imageP1.setFitWidth(120);
        imageP1.setFitHeight(120);

        imageP2.setFitWidth(120);
        imageP2.setFitHeight(120);
    }

    private void setButtons() {
        btnConfig(btnStart);

        btnStart.setOnAction((e)->game.pickNames(inputP1.getText(), inputP2.getText()));

        inputP1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    if(game.getGameMode()==1 && !inputP1.getText().equals(""))
                        inputP2.requestFocus();
                    if(game.getGameMode()==2 && !inputP1.getText().equals(""))
                        game.pickNames(inputP1.getText(), inputP2.getText());
                    if(game.getGameMode()==1 && !inputP1.getText().equals("") && !inputP2.getText().equals(""))
                        game.pickNames(inputP1.getText(), inputP2.getText());
                }
            }
        });

        inputP2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    if(game.getGameMode()==1 && !inputP2.getText().equals(""))
                        inputP1.requestFocus();
                    if(game.getGameMode()==1 && !inputP1.getText().equals("") && !inputP2.getText().equals(""))
                        game.pickNames(inputP1.getText(), inputP2.getText());
                }
            }
        });
    }

    public void btnConfig(Button btn){
        btn.setOnMouseEntered(event -> btn.setFont(Font.font(TEXT_FONT, 35)));
        btn.setOnMouseExited(event -> btn.setFont(Font.font(TEXT_FONT, 30)));
        btn.setBackground(null);
        btn.setFont(Font.font(TEXT_FONT, 30));
        btn.setTextFill(Color.valueOf(LIGHT_GRAY));
        btn.setMinHeight(70);
    }

    private void registerObserver(){
        game.addPropertyChangeListener(PROPERTY_GAME, evt -> {
            refresh();
        });
    }

    private void refresh() {
        if(game.getCurrentSituation() == Situation.AwaitPickingNames){
            inputP1.setText("");
            inputP2.setText("");
            inputP1.requestFocus();
            inputP2.setVisible((game.getGameMode() == 1));
            this.setVisible(true);
        }else
            this.setVisible(false);
    }
}

