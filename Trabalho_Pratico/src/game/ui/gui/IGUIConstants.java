package game.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public interface IGUIConstants {
    public static String PROPERTY_GAME = "Game";

    String LIGHT_GRAY = "#cccdff";
    String BLACK = "#2B2B2B";
    String TEXT_FONT = "Montserrat";
    Color BLACK_BACKGROUND = (Color.rgb(255,255,255, 0.1));
    Color BLACK_BACKGROUND_END = (Color.rgb(255,255,255, 0.3));
    Color BLACK_BTN = (Color.rgb(0,0,0, 0.9));

    String GAME_ICON = "src/game/ui/gui/resources/images/GameLogo.png";
    String PLAYER_ICON = "src/game/ui/gui/resources/images/player.png";

    Background menuBackground = new Background(new BackgroundFill(Color.rgb(204,205,255, 0.2), CornerRadii.EMPTY, Insets.EMPTY));
}