package game.ui.gui.views;

import game.logic.Situation;
import game.logic.data.Constants;
import game.ui.gui.IGUIConstants;
import game.logic.GameObserver;
import game.ui.gui.resources.CSSManager;
import game.ui.gui.resources.ImageLoader;
import game.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Optional;


public class GUIAwaitDecision extends StackPane implements IGUIConstants, Constants {
    private GameObserver game;
    private BorderPane bp;
    private StackPane sp;

    private GridPane gridBoard;

    private ImageLoader imageLoader;
    private ImageView imageP1, imageP2;

    private HBox boardMenu, screen;
    private VBox board;
    private Label labelGameMode, labelPlayerTurn, labelP1Name, labelP1Credits, labelP1SP, labelP1Turn,
            labelP2Name, labelP2Credits, labelP2SP, labelP2Turn;
    private Button btnMiniGame, btnSP, btnUndo, btnNext;

    public GUIAwaitDecision(GameObserver game){
        this.game = game;
        CSSManager.setCSS(this,"style.css");
        this.bp = new BorderPane();
        this.sp = new StackPane();

        this.imageLoader = new ImageLoader();

        this.gridBoard = new GridPane();
        this.screen = new HBox();
        this.boardMenu = new HBox();
        this.board = new VBox();

        loadPane();
        registerListeners();
        refresh();
    }


    private void loadPane(){
        setLabels();
        setButtons();
        setImages();
        loadBoard();

        HBox menuBtnMiniGame = new HBox();
        menuBtnMiniGame.getChildren().add(btnMiniGame);
        menuBtnMiniGame.setAlignment(Pos.CENTER);

        HBox menuBtnSP = new HBox();
        menuBtnSP.getChildren().add(btnSP);
        menuBtnSP.setAlignment(Pos.CENTER);

        HBox menuBtnUndo = new HBox();
        menuBtnUndo.getChildren().add(btnUndo);
        menuBtnUndo.setAlignment(Pos.CENTER);

        HBox menuBtnNext = new HBox();
        menuBtnNext.getChildren().add(btnNext);
        menuBtnNext.setAlignment(Pos.CENTER);

        //GAME INFO
        ImageView logo = imageLoader.getGameLogo();
        logo.setFitHeight(120);
        logo.setFitWidth(380);

        HBox menuGameMode = new HBox();
        menuGameMode.getChildren().add(labelGameMode);
        menuGameMode.setAlignment(Pos.CENTER);

        HBox menuPlayerTurn = new HBox();
        menuPlayerTurn.getChildren().add(labelPlayerTurn);
        menuPlayerTurn.setAlignment(Pos.CENTER);

        // PLAYER 1 INFO
        VBox menuP1V = new VBox();
        menuP1V.getChildren().addAll(labelP1Name, labelP1Credits, labelP1SP, labelP1Turn);
        menuP1V.setSpacing(15);

        HBox menuP1H = new HBox();
        menuP1H.getChildren().addAll(imageP1,menuP1V);
        menuP1H.setSpacing(15);
        menuP1H.setPadding(new Insets(50, 0, 50, 10));
        menuP1H.setAlignment(Pos.CENTER);

        // PLAYER 2 INFO
        VBox menuP2V = new VBox();
        menuP2V.getChildren().addAll(labelP2Name, labelP2Credits, labelP2SP, labelP2Turn);
        menuP2V.setSpacing(15);

        HBox menuP2H = new HBox();
        menuP2H.getChildren().addAll(imageP2,menuP2V);
        menuP2H.setSpacing(15);
        menuP2H.setPadding(new Insets(0, 0, 50, 10));
        menuP2H.setAlignment(Pos.CENTER);


        // OPTIONS

        VBox menuOpt = new VBox();
        menuOpt.setBackground(new Background(new BackgroundFill(BLACK_BACKGROUND, new CornerRadii(0), Insets.EMPTY)));
        menuOpt.getChildren().addAll(logo, menuGameMode, menuPlayerTurn, menuP1H, menuP2H, menuBtnSP,
                menuBtnMiniGame, menuBtnUndo, menuBtnNext);
        menuOpt.setSpacing(10);
        menuOpt.setPadding(new Insets(10, 10, 10, 10));
        menuOpt.prefWidthProperty().bind(screen.widthProperty().multiply(0.3));
        menuOpt.setAlignment(Pos.CENTER);


        // SCREEN
        screen.getChildren().addAll(board, menuOpt);
        screen.setSpacing(100);
        screen.setAlignment(Pos.CENTER);

        sp.setBackground(imageLoader.getBackground());
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(screen);
        sp.setPadding(new Insets(50));

        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setImages(){
        this.imageP1 = imageLoader.getPlayer1();
        this.imageP2 = imageLoader.getPlayer2();

        imageP1.setFitWidth(120);
        imageP1.setFitHeight(120);

        imageP2.setFitWidth(120);
        imageP2.setFitHeight(120);
    }

    private void setLabels(){
        this.labelGameMode = new Label();
        labelGameMode.setFont(Font.font(TEXT_FONT, 25));
        labelGameMode.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelPlayerTurn = new Label();
        labelPlayerTurn.setFont(Font.font(TEXT_FONT, 25));
        labelPlayerTurn.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP1Name = new Label();
        labelP1Name.setFont(Font.font(TEXT_FONT, 15));
        labelP1Name.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP1Credits = new Label();
        labelP1Credits.setFont(Font.font(TEXT_FONT, 15));
        labelP1Credits.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP1SP = new Label();
        labelP1SP.setFont(Font.font(TEXT_FONT, 15));
        labelP1SP.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP1Turn = new Label();
        labelP1Turn.setFont(Font.font(TEXT_FONT, 15));
        labelP1Turn.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP2Name = new Label();
        labelP2Name.setFont(Font.font(TEXT_FONT, 15));
        labelP2Name.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP2Credits = new Label();
        labelP2Credits.setFont(Font.font(TEXT_FONT, 15));
        labelP2Credits.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP2SP = new Label();
        labelP2SP.setFont(Font.font(TEXT_FONT, 15));
        labelP2SP.setTextFill(Paint.valueOf(LIGHT_GRAY));

        this.labelP2Turn = new Label();
        labelP2Turn.setFont(Font.font(TEXT_FONT, 15));
        labelP2Turn.setTextFill(Paint.valueOf(LIGHT_GRAY));
    }

    private void loadBoard(){
        boardMenu.setBackground(imageLoader.getBackground());
        board.setBackground(new Background(new BackgroundFill(BLACK_BACKGROUND, new CornerRadii(0), Insets.EMPTY)));
        board.getChildren().add(gridBoard);
        board.setPadding(new Insets(10, 10, 10, 10));
        board.prefWidthProperty().bind(screen.widthProperty().multiply(0.7));
        board.setAlignment(Pos.CENTER);
        refreshBoard();
        gridBoard.setAlignment(Pos.CENTER);
    }

    private void refreshBoard() {
        for(int i=0; i<COLUMN_NUM;i++){
            for(int j = 0; j< LINE_NUM; j++){
                Pane pane = new Pane();
                pane.setMaxHeight(140);
                pane.setMaxWidth(140);
                pane.setPrefSize(130, 130);
                switch (game.getPiecePosition(j,i)){
                    case 0 -> pane.setBackground(imageLoader.getWhite(j,i));
                    case 1 -> pane.setBackground(imageLoader.getYellow(j,i));
                    case 2 -> pane.setBackground(imageLoader.getRed(j,i));
                }
                gridBoard.add(pane, i, j);
                if(game.isCurrPlayerPerson()) {
                    pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Node source = (Node) mouseEvent.getSource();
                            game.setPiece(GridPane.getColumnIndex(source) + 1);
                        }
                    });
                }
            }
        }
    }

    private void setButtons() {
        this.btnNext = new Button ("Next");
        this.btnUndo = new Button("Undo");
        this.btnMiniGame = new Button("Play MiniGame");
        this.btnSP = new Button("Set Special Piece");
        btnConfig(btnSP);
        btnConfig(btnUndo);
        btnConfig(btnMiniGame);
        btnConfig(btnNext);
    }

    public void btnConfig(Button btn){
        btn.getStyleClass().setAll("dark-blue-default");
        btn.setOnMouseEntered(event -> btn.getStyleClass().setAll(new String[]{"dark-blue-hover"}));
        btn.setOnMouseExited(event -> btn.getStyleClass().setAll(new String[]{"dark-blue-default"}));
        btn.setOnMousePressed(event -> btn.getStyleClass().setAll(new String[]{"dark-blue-pressed"}));
    }

    private void registerListeners(){
        game.addPropertyChangeListener(PROPERTY_GAME, evt -> {
            refresh();
        });

        btnSP.setOnAction((ActionEvent e) -> {
            game.chooseSpecialPiece();
            refresh();
        });

        btnUndo.setOnAction((ActionEvent e) -> {
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("Number of rollbacks");
            Optional<String> result = td.showAndWait();
            result.ifPresent(name -> {
                if(Utils.isNumeric(name)){
                    int num = Integer.parseInt(name);
                    if(num>0 && num <= game.getCurrentCredits() && (game.getGameTurn()-num >= 0))
                        game.rollback(num);
                    refresh();
                }
            });
        });

        btnMiniGame.setOnAction((ActionEvent e) -> { game.startMiniGame(); });

        btnNext.setOnAction((ActionEvent e) -> { game.setPiece(-1); });
    }

    private void refresh() {
        if(game.getCurrentSituation() ==  Situation.AwaitDecision ){
            refreshBoard();
            labelP1Credits.setVisible(game.getGameMode() != 3);
            labelP1SP.setVisible(game.getGameMode() != 3);
            labelP1Turn.setVisible(game.getGameMode() != 3);
            labelP2Credits.setVisible(game.getGameMode() == 1);
            labelP2SP.setVisible(game.getGameMode() == 1);
            labelP2Turn.setVisible(game.getGameMode() == 1);
            if(game.isCurrPlayerPerson()){
                btnSP.setVisible(game.hasPlayerSP());
                btnMiniGame.setVisible(game.isMiniGame());
                btnUndo.setVisible(game.hasCredits());
                btnNext.setVisible(false);
            }else {
                btnSP.setVisible(false);
                btnMiniGame.setVisible(false);
                btnUndo.setVisible(false);
                btnNext.setVisible(true);
            }
            labelGameMode.setText(game.getGameModeString());
            labelPlayerTurn.setText(game.getPlayerTurnString() + "'s Turn");
            labelP1Name.setText(game.getP1Name());
            labelP1Credits.setText("Credits: "+ game.getP1Credits());
            labelP1SP.setText("Special Pieces: "+ game.getP1SP());
            labelP1Turn.setText("Turn to MiniGame: "+ (4-game.getP1Turn()));
            labelP2Name.setText(game.getP2Name());
            labelP2Credits.setText("Credits: "+ game.getP2Credits());
            labelP2SP.setText("Special Pieces: "+ game.getP2SP());
            labelP2Turn.setText("Turn to MiniGame: "+ (4-game.getP2Turn()));
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }
}
