package com.main;

import com.enums.Difficulty;
import com.logicController.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TestEntryPoint {
//    public static void main(String[] args) {
//        String mask = new RandomWordmask().getMask(Difficulty.HARD);
//        OnlineWordsFetcher owf=new OnlineWordsFetcher(mask);
//        Thread t = new Thread(owf);
//        t.start();
//        System.out.println(owf.getWords());
//
//        while (t.isAlive()){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(owf.getWords());
//    }

    public static void main(String[] args) {
        StateRepository sr = new StateRepository();

        Thread updater = new Thread(new DummyUpdater(sr, Difficulty.EASY));
        updater.start();

        while (true){
            System.out.println("Thread 1 running - " + LocalDateTime.now());
            if (updater.isAlive()) {
                System.out.println("Thread 2 is still running");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}