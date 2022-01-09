package com.dialogCommands;

import com.interfaces.HangmanDialog;
import com.interfaces.ShowDialogCommand;

public abstract class BaseShowDialogCommand implements ShowDialogCommand {
    private HangmanDialog dialog;
    private String message;

    public BaseShowDialogCommand(HangmanDialog dialog, String message) {
        this.dialog = dialog;
        this.message = message;
    }

    public void showDialog() {
        dialog.showDialog(message);
    }

    public Integer getResult() {
        return this.getResult();
    }
}
