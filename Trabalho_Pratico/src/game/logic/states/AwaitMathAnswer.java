package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitMathAnswer extends StateAdapter{

    protected AwaitMathAnswer(GameData game) { super(game); }

    @Override
    public Situation getCurrentSituation() {
        return null;
    }
}
