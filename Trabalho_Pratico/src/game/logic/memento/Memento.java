package game.logic.memento;

import java.io.*;
/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Memento {
    private byte[] snapshot = null;

    public Memento(Object obj) throws IOException {
        ByteArrayOutputStream bAos;
        ObjectOutputStream oos = null;

        try {
            bAos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bAos);
            oos.writeObject(obj);
            snapshot = bAos.toByteArray();
        }finally {
            if(oos!=null)
                oos.close();
        }
    }

    public Object getSnapshot() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        if (snapshot == null)
            return null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(snapshot));
            return ois.readObject();
        } finally {
            if(ois!=null){
                ois.close();
            }
        }
    }
}
