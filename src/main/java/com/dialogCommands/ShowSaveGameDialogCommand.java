package com.dialogCommands;

import com.dialogs.SaveGameConfirmationDialog;

public class ShowSaveGameDialogCommand extends BaseShowDialogCommand {

    public ShowSaveGameDialogCommand() {
        super(new SaveGameConfirmationDialog(), null);
    }

    @Override
    public void execute() {
        super.showDialog();
    }

    @Override
    public Integer getDialogResult() {
       return super.getResult();
    }
}
