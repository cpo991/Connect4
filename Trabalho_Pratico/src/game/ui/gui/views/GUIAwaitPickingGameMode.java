package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IGUIConstants;
import game.logic.GameObserver;
import game.ui.gui.resources.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;

public class GUIAwaitPickingGameMode extends StackPane implements IGUIConstants {
    private BorderPane bp;
    private StackPane sp;
    private GameObserver game;
    private Button btnPP, btnPC, btnCC, btnReturn;
    private ImageLoader imageLoader;
    private VBox menu, topSP;
    private HBox rootHorizontal;
    private Label label;

    public GUIAwaitPickingGameMode(GameObserver game){
        this.game = game;

        this.sp = new StackPane();
        this.bp = new BorderPane();

        this.label = new Label();

        this.btnPP = new Button("Player vs Player");
        this.btnPC = new Button("Player vs Computer");
        this.btnCC = new Button("Computer vs Computer");
        this.btnReturn = new Button("Previous");

        this.imageLoader = new ImageLoader();

        this.menu = new VBox();
        this.rootHorizontal = new HBox();
        this.topSP = new VBox();

        loadPane();
        registerObserver();
        refresh();
    }

    private void loadPane() {
        setButtons();

        label.setText("GAME MODE\n\n");
        label.setFont(Font.font(TEXT_FONT, 50));
        label.setTextFill(Paint.valueOf(LIGHT_GRAY));

        menu.getChildren().add(imageLoader.getGameLogo());
        menu.getChildren().addAll(label,btnPP, btnPC, btnCC, btnReturn);
        menu.setAlignment(Pos.CENTER);

        rootHorizontal.getChildren().add(menu);
        rootHorizontal.setAlignment(Pos.CENTER);

        topSP.setBackground(imageLoader.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);

        sp.getChildren().add(topSP);
        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setButtons() {
        btnConfig(btnPP);
        btnConfig(btnPC);
        btnConfig(btnCC);
        btnConfig(btnReturn);

        btnPP.setOnAction((e)->game.chooseGameMode(1));
        btnPC.setOnAction((e)->game.chooseGameMode(2));
        btnCC.setOnAction((e)->game.chooseGameMode(3));
        btnReturn.setOnAction((e)->game.previousMenu());
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
        game.addPropertyChangeListener(PROPERTY_GAME, evt -> { refresh(); });
    }

    private void refresh() {
        this.setVisible(game.getCurrentSituation() ==   Situation.AwaitPickingGameMode);
    }
}
