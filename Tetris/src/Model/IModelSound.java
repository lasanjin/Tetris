package Model;

/**
 * Observer interface for updating sound from Model.
 */
public interface IModelSound {
    void playSound(String name);
    void stopFXSound();
}
