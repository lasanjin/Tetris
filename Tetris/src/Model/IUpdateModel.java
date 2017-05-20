package Model;

import Model.exceptions.DropException;
import Model.exceptions.GameOverException;
import Model.exceptions.NextLevelException;

/**
 * Observer interface for updating the model.
 */
public interface IUpdateModel {
    void updateModel() throws DropException, NextLevelException, GameOverException;
    void actOnKeys(int key) throws DropException;
    void actOnKeys(boolean hardDrop);
    void addScore(boolean softDrop);
    void stopScoreTask();
}
