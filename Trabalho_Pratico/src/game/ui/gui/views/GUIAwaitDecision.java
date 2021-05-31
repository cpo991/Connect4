package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IGUIConstants;
import game.ui.gui.model.GameObserver;
import game.ui.gui.resources.CSSManager;
import game.ui.gui.resources.Images;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIAwaitDecision extends StackPane implements PropertyChangeListener, IGUIConstants {
    private GameObserver game;
    private BorderPane bp;
    private StackPane sp;

    private Images images;
    private Image imageP1,imageP2;
    private ImageView logo, ivP1, ivP2;
    private HBox screen, menuGameMode, menuPlayerTurn, menuP1H, menuP2H, menuBtnMiniGame, menuBtnSP, menuBtnUndo;
    private VBox menuOpt, board, menuP1V, menuP2V;

    private MenuBar menuBar;
    private Menu info;
    private MenuItem about, log;
    private Menu file;
    private MenuItem itemSave;

    private Label labelGameMode, labelPlayerTurn, labelGameTurn, labelP1Name, labelP1Credits, labelP1SP, labelP1Turn,
            labelP2Name, labelP2Credits, labelP2SP, labelP2Turn;
    private Button btnMiniGame, btnSP, btnUndo;

    public GUIAwaitDecision(GameObserver game)  throws FileNotFoundException {
        this.game = game;
        CSSManager.setCSS(this,"mystyle.css");
        this.bp = new BorderPane();
        this.sp = new StackPane();

        this.images = new Images();
        this.logo = images.getGameLogo();

        this.screen = new HBox();
        this.menuOpt = new VBox();
        this.board = new VBox();
        this.menuGameMode = new HBox();
        this.menuPlayerTurn = new HBox();
        this.menuPlayerTurn = new HBox();
        this.menuP1V = new VBox();
        this.menuP1H = new HBox();
        this.menuP2V = new VBox();
        this.menuP2H = new HBox();
        this.menuBtnMiniGame = new HBox();
        this.menuBtnSP = new HBox();
        this.menuBtnUndo = new HBox();

        this.btnMiniGame = new Button("Play MiniGame");
        this.btnSP = new Button("Set Special Piece");
        this.btnUndo = new Button("Undo");

        this.menuBar = new MenuBar();
        this.info = new Menu("Info");
        this.about = new MenuItem("About");
        this.log = new MenuItem("Game Log");
        file = new Menu("File");
        itemSave = new MenuItem("Save");

        this.labelGameMode = new Label(game.getGameModeString());
        this.labelPlayerTurn = new Label(game.getPlayerTurnString() + "'s Turn");
        this.labelP1Name = new Label(game.getP1Name());
        this.labelP1Credits = new Label("Credits: "+ game.getP1Credits());
        this.labelP1SP = new Label("Special Pieces: "+ game.getP1SP());
        this.labelP1Turn = new Label("Turn to MiniGame: "+ (4-game.getP1Turn()));
        this.labelP2Name = new Label(game.getP2Name());
        this.labelP2Credits = new Label("Credits: "+ game.getP2Credits());
        this.labelP2SP = new Label("Special Pieces: "+ game.getP2SP());
        this.labelP2Turn = new Label("Turn to MiniGame: "+ (4-game.getP2Turn()));

        loadPane();
        propertyChange(null);

    }


    private void loadPane() throws FileNotFoundException{

        setButtons();
        setImages();

        file.getItems().addAll(itemSave);
        info.getItems().addAll(about, log);
        menuBar.getMenus().addAll(file, info);

        // BOARD
        board.setBackground(new Background(new BackgroundFill(BLACK_BACKGROUND, new CornerRadii(0), Insets.EMPTY)));
        board.getChildren().add(images.getBoard());
        board.setPadding(new Insets(10, 10, 10, 10));
        board.prefWidthProperty().bind(screen.widthProperty().multiply(0.90));
        board.setAlignment(Pos.CENTER);

        // OPTIONS
        menuBtnUndo.getChildren().add(btnUndo);
        menuBtnUndo.setAlignment(Pos.CENTER);
        menuBtnMiniGame.getChildren().add(btnMiniGame);
        menuBtnMiniGame.setAlignment(Pos.CENTER);
        menuBtnSP.getChildren().add(btnSP);
        menuBtnSP.setAlignment(Pos.CENTER);
        menuOpt.setBackground(new Background(new BackgroundFill(BLACK_BACKGROUND, new CornerRadii(0), Insets.EMPTY)));
        menuOpt.getChildren().addAll(logo, menuGameMode, menuPlayerTurn, menuP1H, menuP2H);
        if(!game.hasPlayerSP())
            menuOpt.getChildren().add(menuBtnSP);
        if(!game.isMiniGame())
            menuOpt.getChildren().add(menuBtnMiniGame);
        if(game.hasCredits())
            menuOpt.getChildren().add(menuBtnUndo);
        menuOpt.setSpacing(10);
        menuOpt.setPadding(new Insets(10, 10, 10, 10));
        menuOpt.prefWidthProperty().bind(screen.widthProperty().multiply(0.10));


        //GAME INFO
        logo.setFitHeight(120);
        logo.setFitWidth(380);
        labelGameMode.setFont(Font.font(TEXT_FONT, 25));
        labelGameMode.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelPlayerTurn.setFont(Font.font(TEXT_FONT, 25));
        labelPlayerTurn.setTextFill(Paint.valueOf(LIGHT_GRAY));
        menuGameMode.getChildren().add(labelGameMode);
        menuGameMode.setAlignment(Pos.CENTER);
        menuPlayerTurn.getChildren().add(labelPlayerTurn);
        menuPlayerTurn.setAlignment(Pos.CENTER);

        // PLAYER INFO
        labelP1Name.setFont(Font.font(TEXT_FONT, 15));
        labelP1Name.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelP1Credits.setFont(Font.font(TEXT_FONT, 15));
        labelP1Credits.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelP1SP.setFont(Font.font(TEXT_FONT, 15));
        labelP1SP.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelP1Turn.setFont(Font.font(TEXT_FONT, 15));
        labelP1Turn.setTextFill(Paint.valueOf(LIGHT_GRAY));
        if(game.getGameMode() == 3)
            menuP1V.getChildren().add(labelP1Name);
        else
            menuP1V.getChildren().addAll(labelP1Name, labelP1Credits, labelP1SP, labelP1Turn);
        menuP1V.setSpacing(15);
        menuP1H.getChildren().addAll(ivP1,menuP1V);
        menuP1H.setSpacing(15);
        menuP1H.setPadding(new Insets(50, 0, 50, 10));

        labelP2Name.setFont(Font.font(TEXT_FONT, 15));
        labelP2Name.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelP2Credits.setFont(Font.font(TEXT_FONT, 15));
        labelP2Credits.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelP2SP.setFont(Font.font(TEXT_FONT, 15));
        labelP2SP.setTextFill(Paint.valueOf(LIGHT_GRAY));
        labelP2Turn.setFont(Font.font(TEXT_FONT, 15));
        labelP2Turn.setTextFill(Paint.valueOf(LIGHT_GRAY));
        if(game.getGameMode() == 1)
            menuP2V.getChildren().addAll(labelP2Name, labelP2Credits, labelP2SP, labelP2Turn);
        else
            menuP2V.getChildren().add(labelP2Name);
        menuP2V.setSpacing(15);
        menuP2H.getChildren().addAll(ivP2,menuP2V);
        menuP2H.setSpacing(15);
        menuP2H.setPadding(new Insets(0, 0, 50, 10));

        // SCREEN
        screen.getChildren().addAll(board, menuOpt);
        screen.setSpacing(50);
        screen.setAlignment(Pos.CENTER);

        sp.setBackground(images.getBackground());
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(screen);
        sp.setPadding(new Insets(50));

        bp.setTop(menuBar);
        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setImages() throws FileNotFoundException{
        this.imageP1 = new Image(new FileInputStream(PLAYER_ICON));
        this.imageP2 = new Image(new FileInputStream(PLAYER_ICON));

        this.ivP1 = new ImageView(imageP1);
        this.ivP2 = new ImageView(imageP2);

        ivP1.setFitWidth(120);
        ivP1.setFitHeight(120);

        ivP2.setFitWidth(120);
        ivP2.setFitHeight(120);
    }

    private void setButtons() {

        //File -> Save
        itemSave.setText("Save Game");
        itemSave.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            Stage fileChooseStage = new Stage();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
            fileChooser.setInitialDirectory(new java.io.File("."));
            fileChooser.getExtensionFilters().add(extFilter);
            File ficheiro = fileChooser.showSaveDialog(fileChooseStage);

            if (ficheiro != null) {
                game.saveGameFile(ficheiro);
                try {
                    GUIAwaitBeginning guiAwaitBeginning = new GUIAwaitBeginning(game);
                    getChildren().add(guiAwaitBeginning);
                    System.out.println(game.getCurrentState().getCurrentSituation());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


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

        btnConfig(btnSP);
        btnConfig(btnUndo);
        btnConfig(btnMiniGame);

    }

    public void btnConfig(Button btn){
        btn.getStyleClass().setAll("dark-blue-default");
        btn.setOnMouseEntered(event -> btn.getStyleClass().setAll(new String[]{"dark-blue-hover"}));
        btn.setOnMouseExited(event -> btn.getStyleClass().setAll(new String[]{"dark-blue-default"}));
        btn.setOnMousePressed(event -> btn.getStyleClass().setAll(new String[]{"dark-blue-pressed"}));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitDecision)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}
