package game.logic.memento;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public interface ICareTaker {
    void saveMemento();
    void undo();
    void redo();
}