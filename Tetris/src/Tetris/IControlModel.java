package Tetris;

/**
 * Observer interface for updating the model
 *
 */
public interface IControlModel {
    void updateModel() throws DropException, NextLevelException, GameOverException;
    void actOnKeys(int key) throws DropException;
    void actOnKeys(boolean hardDrop);
    void addScore(boolean softDrop);
    void stopScoreTask();
}
