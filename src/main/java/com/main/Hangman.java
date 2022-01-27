package com.main;

import com.logicController.LogicController;

public class Hangman {

    public static void main(String[] args) {

        try {
            LogicController.getInstance();
        } catch (Exception e){
            LogicController.throwError(e.getMessage());
        }


    }
}

