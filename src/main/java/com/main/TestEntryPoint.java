package com.main;

import com.enums.Difficulty;
import com.logicController.*;

import java.time.LocalDateTime;

class TestEntryPoint {
    public static void main(String[] args) throws InterruptedException {
        StateRepository sr = new StateRepository();

        DummyUpdater ud = new DummyUpdater(sr, Difficulty.MEDIUM);
        Thread updater = new Thread(ud);
        updater.start();
        System.out.println("Started updater");

        while (true) {
            System.out.println("Thread 1 running - " + LocalDateTime.now());
            Thread.sleep(250);

        }

    }
}