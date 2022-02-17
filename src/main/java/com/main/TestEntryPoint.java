package com.main;

import com.enums.Difficulty;
import com.gameController.Word;
import com.logicController.OnlineWordsFetcher;
import com.logicController.RandomWordmask;
import com.logicController.StateRepository;

import java.util.List;

class TestEntryPoint {
    public static void main(String[] args) {
        String mask = new RandomWordmask().getMask(Difficulty.HARD);
        OnlineWordsFetcher owf=new OnlineWordsFetcher(mask);
        Thread t = new Thread(owf);
        t.start();
        System.out.println(owf.getWords());

        while (t.isAlive()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(owf.getWords());
    }
}