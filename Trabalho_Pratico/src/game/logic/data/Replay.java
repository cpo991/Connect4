package game.logic.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static game.logic.data.Constants.*;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Replay implements Serializable {
    private String title;
    private List<ReplaySnapshot> replays;
    private int gameType;


    public Replay(String title, int gameType){
        this.title = title;
        this.gameType = gameType;
        this.replays = new ArrayList<>();
    }

    public String getTitle() { return title; }

    public List<ReplaySnapshot> getStackReplay() {
        return replays;
    }

    public String getGameTypeString() {
        return switch (gameType) {
            case 1 -> GAME_TYPE1;
            case 2 -> GAME_TYPE2;
            case 3 -> GAME_TYPE3;
            default -> "Not Defined";
        };
    }

    public void addSnapshot(ReplaySnapshot snapshot){
        this.replays.add(snapshot);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }
}
