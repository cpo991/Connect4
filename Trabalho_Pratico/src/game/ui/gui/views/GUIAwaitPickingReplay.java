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

public class GUIAwaitPickingReplay extends StackPane implements PropertyChangeListener, IConstantsColors {
    private BorderPane bp;
    private StackPane sp;
    private GameObserver game;
    private Button btn1, btn2, btn3, btn4, btn5, btnReturn;
    private Images images;
    private VBox menu, topSP;
    private HBox rootHorizontal;
    private MenuBar menuBar;
    private Menu file;
    private MenuItem itemSave;
    private Menu info;
    private MenuItem about,log;
    private Label label;

    public GUIAwaitPickingReplay(GameObserver game) throws FileNotFoundException {
        this.game = game;

        this.sp = new StackPane();
        this.bp = new BorderPane();

        this.label = new Label();

        this.btn1 = new Button(game.getReplaysByNum(1));
        this.btn2 = new Button(game.getReplaysByNum(2));
        this.btn3 = new Button(game.getReplaysByNum(3));
        this.btn4 = new Button(game.getReplaysByNum(4));
        this.btn5 = new Button(game.getReplaysByNum(5));
        this.btnReturn = new Button("Previous");

        this.images = new Images();

        this.menu = new VBox();
        this.rootHorizontal = new HBox();
        this.topSP = new VBox();

        this.menuBar = new MenuBar();
        this.info = new Menu("Info");
        this.about = new MenuItem("About");
        this.log = new MenuItem("Game Log");
        file = new Menu("File");
        itemSave = new MenuItem("Save");

        loadPane();
        propertyChange(null);
    }

    private void loadPane() {
        setButtons();

        file.getItems().addAll(itemSave);
        info.getItems().addAll(about,log);
        menuBar.getMenus().addAll(file, info);

        label.setText("GAME HISTORY\n\n");
        label.setFont(Font.font(TEXT_FONT, 50));
        label.setTextFill(Paint.valueOf(LIGHT_GRAY));

        menu.getChildren().add(images.getPlanetBound());
        menu.getChildren().addAll(label, btn1, btn2, btn3, btn4, btn5, btnReturn);
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

        btnConfig(btn1);
        btnConfig(btn2);
        btnConfig(btn3);
        btnConfig(btn4);
        btnConfig(btn5);
        btnConfig(btnReturn);

        btn1.setOnMousePressed(new BTN1());
        btn2.setOnMousePressed(new BTN2());
        btn3.setOnMousePressed(new BTN3());
        btn4.setOnMousePressed(new BTN4());
        btn5.setOnMousePressed(new BTN5());
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

    private class BTN1 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            /*game.startReplay(1);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }*/
        }
    }

    private class BTN2 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            /*game.startReplay(2);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }*/
        }
    }
    private class BTN3 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            /*game.startReplay(1);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }*/
        }
    }

    private class BTN4 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            /*game.startReplay(1);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }*/
        }
    }

    private class BTN5 implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            /*game.startReplay(1);
            try{
                GUIAwaitDecision guiAwaitDecision = new GUIAwaitDecision(game);
                getChildren().add(guiAwaitDecision);
                System.out.println(game.getCurrentState().getCurrentSituation());
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }*/


        }
    }

    private class BTNRETURN implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            game.previousMenu();
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
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitPickingReplay)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}
