package game.ui.gui.views;

import game.logic.GameObserver;
import game.logic.Situation;
import game.ui.gui.IGUIConstants;
import game.ui.gui.resources.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class GUIAwaitMiniGameAnswer extends StackPane implements IGUIConstants {
    private GameObserver game;
    private Label mainLabel, gameLabel;
    private TextField answerWord, answerMath;
    private Button btnSend;

    public GUIAwaitMiniGameAnswer(GameObserver game) {
        this.game = game;
        loadPane();
        registerObserver();
        refresh();
    }

    private void loadPane() {
        setLabels();
        setButtons();

        this.answerWord = new TextField();
        answerWord.promptTextProperty().set("Insert the previous words");
        answerWord.setFont(Font.font(TEXT_FONT, 30));
        this.answerMath = new TextField();
        answerWord.promptTextProperty().set("Insert total of the expression");
        answerMath.setFont(Font.font(TEXT_FONT, 30));

        VBox mainMenu = new VBox();
        mainMenu.getChildren().addAll(mainLabel, gameLabel, answerWord, answerMath, btnSend);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setSpacing(50);

        HBox rootHorizontal = new HBox();
        rootHorizontal.getChildren().add(mainMenu);
        rootHorizontal.setAlignment(Pos.CENTER);

        VBox topSP = new VBox();
        ImageLoader imageLoader = new ImageLoader();
        topSP.setBackground(imageLoader.getBackground());
        topSP.setAlignment(Pos.CENTER);
        topSP.getChildren().addAll(rootHorizontal);


        StackPane sp = new StackPane();
        sp.getChildren().add(topSP);

        BorderPane bp = new BorderPane();
        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setLabels() {
        this.mainLabel = new Label();
        mainLabel.setText("Write these words");
        mainLabel.setFont(Font.font(TEXT_FONT, 50));
        mainLabel.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.gameLabel = new Label();
        gameLabel.setText("Write these words");
        gameLabel.setFont(Font.font(TEXT_FONT, 30));
        gameLabel.setTextFill(Paint.valueOf(LIGHT_GRAY));
    }

    private void setButtons() {
        this.btnSend = new Button("Send");
        btnConfig(btnSend);
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
        game.addPropertyChangeListener(PROPERTY_GAME, evt -> refresh());

        btnSend.setOnAction((e)->{
            if(game.getMiniGameTurn() == 1){
                if(!answerMath.getText().equals(""))
                    game.insertMathAnswer(Double.parseDouble(answerMath.getText()));
                else
                    answerMath.requestFocus();
            }else{
                if(!answerWord.getText().equals(""))
                    game.insertWordsAnswer(answerWord.getText());
                else
                    answerWord.requestFocus();
            }
        });

        answerWord.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!answerWord.getText().equals(""))
                    game.insertWordsAnswer(answerWord.getText());
                else
                    answerWord.requestFocus();
            }
        });

        answerMath.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!answerMath.getText().equals(""))
                    game.insertMathAnswer(Double.parseDouble(answerMath.getText()));
                else
                    answerMath.requestFocus();
            }
        });
    }

    private void refresh() {
        if(game.getCurrentSituation() == Situation.AwaitMiniGameAnswer){
            if(game.getMiniGameTurn() == 1){
                mainLabel.setText("Math Game");
                answerMath.setText("");
                answerMath.setTooltip(new Tooltip("Insert the answer of the previous expression"));
                gameLabel.setText(game.getMathString() + " = ?");
                answerMath.setVisible(game.getMiniGameTurn() == 1);
                answerWord.setVisible(game.getMiniGameTurn() == 2);
                answerMath.requestFocus();
            }else{
                mainLabel.setText("Words Game");
                answerWord.setText("");
                answerWord.setTooltip(new Tooltip("Write the previous words"));
                gameLabel.setText(game.getWordsString());
                answerMath.setVisible(game.getMiniGameTurn() == 1);
                answerWord.setVisible(game.getMiniGameTurn() == 2);
                answerWord.requestFocus();
            }
            this.setVisible(true);
        }
        else
            this.setVisible(false);
    }
}

