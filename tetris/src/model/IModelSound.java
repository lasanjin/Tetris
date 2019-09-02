package model;

/**
 * Observer interface for updating sound from model.
 */
public interface IModelSound {
    void playSound(String name);
    void stopFXSound();
}
