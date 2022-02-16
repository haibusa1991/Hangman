package com.main;

import com.enums.Difficulty;
import com.logicController.RandomWordmask;

class TestEntryPoint {
    public static void main(String[] args) {
String s;
        while (true){
             s =  new RandomWordmask().getMask(Difficulty.HARD);
            System.out.println("Mask: " + s + " Length: " + s.length());
        }
    }
}