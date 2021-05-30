package game.ui.gui.views;

import game.logic.Situation;
import game.ui.gui.IConstantsColors;
import game.ui.gui.model.GameObserver;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIAwaitDecision extends StackPane implements IConstantsImages, PropertyChangeListener, IConstantsColors {
    private GameObserver game;
    private BorderPane bp;
    private StackPane sp;
    private Button btn1, btn2, btn3, btn4, btn5, btn6,btn7, btnSave;
    private Button visitSurface, visitStation, skip, quit;
    private VBox menu, topSP;
    private HBox rootHorizontal;

    private Images images;

    private Image planet;
    private ImageView planetView;

    private Label labelNave;
    private Label labelPlaneta1, labelPlaneta2;

    private VBox fundoOpcoes;
    private HBox ecra;
    private VBox opcoes, nave, infoPlanet, jogo;
    private HBox rootPlanet;

    private MenuBar menuBar;
    private Menu info;
    private MenuItem about, log;
    private Menu file;
    private MenuItem itemSave;

    public GUIAwaitDecision(GameObserver game)  throws FileNotFoundException {
        this.game = game;

        //System.out.println(mundo.hasStation());

        this.bp = new BorderPane();
        this.sp = new StackPane();

        this.images = new Images();

        this.visitSurface = new Button("Visit Surface");
        this.visitStation = new Button("Visit Station");
        this.skip = new Button("Skip");
        this.quit = new Button("Quit");

        this.labelNave = new Label();
        this.labelPlaneta1 = new Label();
        this.labelPlaneta2 = new Label();

        this.rootPlanet = new HBox();

        this.ecra = new HBox();
        this.fundoOpcoes = new VBox();

        this.jogo = new VBox();
        this.opcoes = new VBox();
        this.infoPlanet = new VBox();
        this.nave = new VBox();


        this.menuBar = new MenuBar();
        this.info = new Menu("Info");
        this.about = new MenuItem("About");
        this.log = new MenuItem("Game Log");
        file = new Menu("File");
        itemSave = new MenuItem("Save");

        loadPane();
        propertyChange(null);
    }


    private void loadPane() throws FileNotFoundException{

        setButtons();
        setImages();

        file.getItems().addAll(itemSave);
        info.getItems().addAll(about, log);
        menuBar.getMenus().addAll(file, info);


        // INFORMAÇÕES DO PLANETA

        labelPlaneta1.setTextFill(Paint.valueOf(WHITE));
        labelPlaneta1.setFont(Font.font(TEXT_FONT, 20));
        labelPlaneta1.setText("Resources:");


        labelPlaneta2.setTextFill(Paint.valueOf(WHITE));
        labelPlaneta2.setFont(Font.font(TEXT_FONT, 18));
        labelPlaneta2.setText("adajsdaidaisdjaisjdaidjaisdjaisdjadj" + "\n");


        infoPlanet.getChildren().addAll(labelPlaneta1, labelPlaneta2);
        infoPlanet.setBackground(menuBackground);
        infoPlanet.setPadding(new Insets(20));
        infoPlanet.setAlignment(Pos.CENTER);

        rootPlanet.getChildren().addAll(planetView,infoPlanet);
        rootPlanet.setAlignment(Pos.CENTER);
        rootPlanet.setSpacing(70);



        // INFORMAÇÕES DA NAVE

        labelNave.setText("YOUR SHIP\n" + "game.getShip()");
        labelNave.setFont(Font.font(TEXT_FONT, 15));
        labelNave.setTextFill(Paint.valueOf(LIGHT_GRAY));

        nave.getChildren().add(labelNave);
        nave.setBackground(menuBackground);
        //nave.setSpacing(20);
        nave.setPadding(new Insets(20));


        // INFORMAÇÕES DO JOGO

        jogo.getChildren().addAll(rootPlanet,nave);
        jogo.setAlignment(Pos.CENTER_LEFT);
        jogo.setSpacing(80);
        jogo.setPadding(new Insets(30));



        // MENU OPCOES


        // verificar se já não esgotou todas as voltas ou se tem landing party!!!!
        /*if(game.hasStation()) {
            if(game.hasLandingParty() && game.hasDrone())
                opcoes.getChildren().addAll(visitSurface,visitStation,skip,quit);
            else{
                opcoes.getChildren().addAll(visitStation, skip,quit);
            }
        }
        else{
            if(game.hasLandingParty() && game.hasDrone())
                opcoes.getChildren().addAll(visitSurface, skip, quit);
            else{
                opcoes.getChildren().addAll(skip,quit);
            }
        }*/
        opcoes.setAlignment(Pos.CENTER);
        opcoes.setBackground(menuBackground);
        //opcoes.setSpacing(10);
        opcoes.setPadding(new Insets(10));

        fundoOpcoes.getChildren().add(opcoes);
        fundoOpcoes.setAlignment(Pos.CENTER_RIGHT);
        fundoOpcoes.setSpacing(20);
        fundoOpcoes.setPadding(new Insets(100, 20, 80, 10));



        // ECRA DE JOGO

        ecra.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255, 0.1), new CornerRadii(0), Insets.EMPTY)));
        ecra.setAlignment(Pos.CENTER);
        ecra.getChildren().addAll(jogo, fundoOpcoes);
        ecra.setSpacing(20);


        sp.setBackground(images.getBackground());
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(ecra);
        sp.setPadding(new Insets(50));

        bp.setTop(menuBar);
        bp.setCenter(sp);

        this.getChildren().addAll(bp);
    }

    private void setImages() throws FileNotFoundException{
        this.planet = new Image (new FileInputStream(PLAYER_ICON));


        this.planetView = new ImageView(this.planet);

        planetView.setFitHeight(130);
        planetView.setFitWidth(130);

    }

    private void setButtons() {

        //File -> Save
        itemSave.setText("Save Game");
        itemSave.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            Stage fileChooseStage = new Stage();
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            File ficheiro = fileChooser.showSaveDialog(fileChooseStage);

            if (ficheiro != null) {
                game.saveGameFile(ficheiro);
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

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(game.getCurrentState().getCurrentSituation() == Situation.AwaitDecision)
            this.sp.setVisible(true);
        else
            this.sp.setVisible(false);
    }
}
