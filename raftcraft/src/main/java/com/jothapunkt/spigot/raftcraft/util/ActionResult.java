package com.jothapunkt.spigot.raftcraft.util;

public class ActionResult {
    private boolean success = true;
    private String errorMessage = null;

    public ActionResult() {}
    public ActionResult(String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = false;
    }

    public boolean isSuccessful() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
