package com.logicController;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class GraphicsPackage implements Serializable {
    private final Map<Integer, byte[]> contents = new TreeMap<>();

    public void add(int index, byte[] image) {
        contents.put(index, image);
    }

    public Map<Integer, byte[]> getContents() {
        return contents;
    }
}

