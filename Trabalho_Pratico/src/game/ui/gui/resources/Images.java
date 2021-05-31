package game.ui.gui.resources;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Images  {
    private Background background;
    private BackgroundImage backgroundImage;
    private Image image, boardImage;
    private ImageView gameLogo, board;
    private Image player;

    public Images() throws FileNotFoundException {
        backgroundImage = new BackgroundImage(new Image(new FileInputStream("src/game/ui/gui/resources/images/GameBackground.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        this.background = new Background(backgroundImage);
        this.image = new Image(new FileInputStream("src/game/ui/gui/resources/images/GameLogo.png"));
        this.gameLogo = new ImageView(image);
        this.boardImage = new Image(new FileInputStream("src/game/ui/gui/resources/images/board.png"));
        this.board = new ImageView(boardImage);
        this.player = new Image(new FileInputStream("src/game/ui/gui/resources/images/player.png"));
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

    public Image getPlayer(){return player;}

}
