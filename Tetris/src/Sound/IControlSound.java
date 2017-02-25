package Sound;

/**
 * Observer interface for Sound
 */
public interface IControlSound {
    void playSound(String name);
    void stopSound(String name);
    void loopSound(String name);
    void stopAllSound(boolean main);
    void stopFXSound();
    void muteSound(boolean mute);
    void muteMusic(boolean mute);
    boolean getMuteSound();
}
