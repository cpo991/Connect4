package game.ui.gui.resources;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private BackgroundImage backgroundImage ;
    private Image image;
    private static Map<String, Image> imgCache = new HashMap<>();

    public ImageLoader() { }

    public Background getBackground() {
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/GameBackground.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(backgroundImage);
    }

    public ImageView getGameLogo() {
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/GameLogo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ImageView(image);
    }

    public ImageView getPlayer(){
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/player.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ImageView(image);
    }


    public ImageView getPlayer1(){
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/player1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ImageView(image);
    }

    public ImageView getPlayer2(){
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/player2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ImageView(image);
    }

    public Background getWhite(int L, int C){
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/White.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(backgroundImage);
    }

    public Background getYellow(int L, int C){
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/Yellow.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(backgroundImage);
    }

    public Background getRed(int L, int C){
        try {
            this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/Red.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        return new Background(backgroundImage);
    }


}
