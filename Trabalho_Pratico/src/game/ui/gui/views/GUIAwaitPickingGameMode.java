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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

public class GUIAwaitPickingGameMode extends StackPane implements PropertyChangeListener, IConstantsColors {
    private BorderPane bp;
    private StackPane sp;
    private GameObserver game;
    private Button btnPP, btnPC, btnCC, btnReturn;
    private Images images;
    private VBox menu, topSP;
    private HBox rootHorizontal;
    private MenuBar menuBar;
    private Menu file;
    private MenuItem itemSave;
    private Menu info;
    private MenuItem about;
    private Label label;

    public GUIAwaitPickingGameMode(GameObserver game) throws FileNotFoundException {
        this.game = game;

        this.sp = new StackPane();
        this.bp = new BorderPane();

        this.label = new Label();

        this.btnPP = new Button("Player vs Player");
        this.btnPC = new Button("Player vs Computer");
        this.btnCC = new Button("Computer vs Computer");
        this.btnReturn = new Button("Previous");

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

        label.setText("GAME MODE\n\n");
        label.setFont(Font.font(TEXT_FONT, 50));
        label.setTextFill(Paint.valueOf(LIGHT_GRAY));

        menu.getChildren().add(images.getPlanetBound());
        menu.getChildren().addAll(label, btnPP, btnPC, btnCC, btnReturn);
        menu.setAlignment(Pos.CENTER);

        rootHorizontal.getChildren().add(menu);
        rootHorizontal.setAlignment(Pos.CENTER);

        topSP.setBackground(images.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);

        sp.getChildren().add(topSP);

        bp.setTop(menuBar);
        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setButtons() {

        //File -> Save
        itemSave.disableProperty().setValue(true);
        itemSave.setText("Save Game");


        //Info -> about
        about.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Acerca:");
            alert.getDialogPane().setContentText("Trabalho Final da Unidade Curricular Programação Avançada." +
                    "\nAna Guilherme - 2017012687\nAno Letivo 19/20");
            alert.showAndWait();
        });

        btnConfig(btnPP);
        btnConfig(btnPC);
        btnConfig(btnCC);
        btnConfig(btnReturn);

        btnPP.setOnMousePressed(new BTNPP());
        btnPC.setOnMousePressed(new BTNPC());
        btnCC.setOnMousePressed(new BTNCC());
        btnReturn.setOnMousePressed(new BTNRETURN());
    }

    public void btnConfig(Button btn){
        btn.setOnMouseEntered(event -> btn.setFont(Font.font(TEXT_FONT, 35)));
        btn.setOnMouseExited(event -> btn.setFont(Font.font(TEXT_FONT, 30)));
        btn.setBackground(null);
        btn.setFont(Font.font(TEXT_FONT, 30));
        btn.setTextFill(Color.valueOf(LIGHT_GRAY));
        btn.setMinHeight(70);
    }

    private class BTNPP implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            game.chooseGameMode(1);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private class BTNPC implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            game.chooseGameMode(2);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private class BTNCC implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            game.chooseGameMode(3);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private class BTNRETURN implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            try{
                GUIAwaitBeginning guiAwaitBeginning = new GUIAwaitBeginning(game);
                getChildren().add(guiAwaitBeginning);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitGameMode)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}
