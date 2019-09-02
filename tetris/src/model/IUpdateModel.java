package model;

import model.exceptions.DropException;
import model.exceptions.GameOverException;
import model.exceptions.NextLevelException;

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
