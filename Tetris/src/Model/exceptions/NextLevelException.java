package Model.exceptions;

import Model.Model;

/**
 * This class is a message from model to controller
 */
public class NextLevelException extends Exception {
    private int newUpdateInterval;

    public NextLevelException(int level) {
        newUpdateInterval = level;
    }

    public int getNewUpdateInterval() {
        return newUpdateInterval;
    }
}
