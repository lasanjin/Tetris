package Tetris;

/**
 * Observer interface for updating controller class
 */
public interface IControl {
    void stopTetris();
    void startTetris();
    boolean getIsRunning();
    boolean getIsMenu();
}
