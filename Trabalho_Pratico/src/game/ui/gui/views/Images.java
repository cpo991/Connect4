package game.ui.gui.views;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Images  {
    private Background background;
    private BackgroundImage backgroundImage;
    private Image image;
    private ImageView planetBound;
    private Image player;

    public Images() throws FileNotFoundException {
        backgroundImage = new BackgroundImage(new Image(new FileInputStream("Images/background1.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        this.background = new Background(backgroundImage);
        this.image = new Image(new FileInputStream("Images/PlanetBound.png"));
        this.planetBound = new ImageView(image);
        this.player = new Image(new FileInputStream("Images/player.png"));
    }

    public BackgroundImage getBackgroundImage(){
        return backgroundImage;
    }

    public Background getBackground() {
        return background;
    }

    public Image getImage() {
        return image;
    }

    public ImageView getPlanetBound() {
        return planetBound;
    }

    public Image getPlayer(){return player;}

}
