package game.logic.memento;

import java.io.IOException;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public interface IMementoOriginator {
    Memento getMemento() throws IOException;
    void setMemento(Memento m) throws IOException, ClassNotFoundException;
}

