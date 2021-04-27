package game.logic.states;

import game.logic.Situation;
import game.logic.data.GameData;

public class AwaitWordsAnswer extends StateAdapter{
    protected AwaitWordsAnswer(GameData game) { super(game); }

    @Override
    public IState insertAnswer() {
        return new AwaitDecision(getGame());
    }

    @Override
    public Situation getCurrentSituation() { return Situation.AwaitWordsAnswer; }
}
