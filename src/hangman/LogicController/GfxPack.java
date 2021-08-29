package hangman.LogicController;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class GfxPack implements Serializable {
    private final Map<Integer,byte[]> contents = new TreeMap<>();

    public void add(int index, byte[] image) {
        contents.put(index, image);
    }

    public byte[] getImage(int index) {
        if (index >= 0 && index < contents.size()) {
            return contents.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Map<Integer,byte[]> getContents() {
        return contents;
    }
}

