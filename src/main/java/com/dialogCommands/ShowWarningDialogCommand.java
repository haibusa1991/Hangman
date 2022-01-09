package com.dialogCommands;

import com.dialogs.WarningDialog;

public class ShowWarningDialogCommand extends BaseShowDialogCommand {

    public ShowWarningDialogCommand(String message) {
        super(new WarningDialog(), message);
    }

    @Override
    public void execute() {
        super.showDialog();
    }

    @Override
    public Integer getDialogResult() {
        return null;
    }
}
