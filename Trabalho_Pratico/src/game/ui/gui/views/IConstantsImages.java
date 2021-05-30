package game.ui.gui.views;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public interface IConstantsImages {

    String GAME_ICON = "Images/fourInARow.png";
    String PLAYER_ICON = "Images/player.png";

    Background menuBackground = new Background(new BackgroundFill(Color.rgb(204,205,255, 0.2), CornerRadii.EMPTY, Insets.EMPTY));

}