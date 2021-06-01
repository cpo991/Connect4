package game.ui.gui.resources;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Images  {
    private Background background, red, white, yellow;;
    private BackgroundImage backgroundImage ;
    private Image image;
    private ImageView gameLogo, board, player;

    public Images() throws FileNotFoundException {
        backgroundImage = new BackgroundImage(new Image(new FileInputStream("src/game/ui/gui/resources/images/GameBackground.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        this.background = new Background(backgroundImage);
        this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/GameLogo.png"));
        this.gameLogo = new ImageView(image);
        this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/board.png"));
        this.board = new ImageView(image);
        this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/player.png"));
        this.player = new ImageView(image);
        backgroundImage = new BackgroundImage(new Image(new FileInputStream("src/game/ui/gui/resources/images/Yellow.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        this.yellow = new Background(backgroundImage);
        backgroundImage = new BackgroundImage(new Image(new FileInputStream("src/game/ui/gui/resources/images/Red.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        this.red = new Background(backgroundImage);
        backgroundImage = new BackgroundImage(new Image(new FileInputStream("src/game/ui/gui/resources/images/White.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        this.white = new Background(backgroundImage);
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

    public ImageView getBoard(){return board;}

    public ImageView getGameLogo() {
        return gameLogo;
    }

    public ImageView getPlayer(){return player;}

    public Background getWhite(int L, int C){return white;}

    public Background getYellow(int L, int C){return yellow;}

    public Background getRed(int L, int C){return red;}


}
