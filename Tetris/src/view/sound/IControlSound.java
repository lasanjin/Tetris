package view.sound;

import model.IModelSound;

/**
 * Observer interface for MainView.sound
 */
public interface IControlSound extends IModelSound {
    void stopSound(String name);
    void loopSound(String name);
    void stopAllSound(boolean main);
    void muteSound(boolean mute);
    void muteMusic(boolean mute);
    boolean getMuteSound();
}
