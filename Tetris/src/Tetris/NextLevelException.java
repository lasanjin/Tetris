package Tetris;

/**
 * This class is a message from model to controller
 */
public class NextLevelException extends Exception {
    private int newUpdateInterval;

    NextLevelException(int level) {
        newUpdateInterval = level;
    }

    public int getNewUpdateInterval() {
        return newUpdateInterval;
    }
}
