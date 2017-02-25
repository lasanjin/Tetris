package Sound;

import java.util.HashMap;

/**
 * This class holds all the .wav files (sounds)
 */
public class Sound implements IControlSound {
    private HashMap<String, SoundClip> soundMap;
    private boolean muteSound;
    private boolean muteMusic;


    public Sound() {
        soundMap = new HashMap<>(15);
        setSoundMap();
    }

    private void setSoundMap() {
        addSound("main", "main", -25);
        addSound("lockdown", "lockdown", -15);
        addSound("move", "move", -10);
        addSound("rotate", "rotate", -15);
        addSound("rotatefail", "rotatefail", -10);
        addSound("clearline", "clearline", -15);
        addSound("doubleclear", "doubleclear", -15);
        addSound("tripleclear", "tripleclear", -15);
        addSound("buttons", "buttons", -10);
        addSound("press", "press", -8);
        addSound("harddrop", "harddrop", -30);
        addSound("levelup", "levelup", -15);
        addSound("amazing", "amazing", -15);
        addSound("fantastic", "fantastic", -15);
        addSound("verygood", "verygood", -15);
        addSound("brilliant", "brilliant", -15);
        addSound("wow", "wow", -15);
        addSound("excellent", "excellent", -15);
        addSound("wonderful", "wonderful", -15);
        addSound("gameover", "gameover", -15);
        addSound("one", "one", -15);
        addSound("two", "two", -15);
        addSound("three", "three", -15);
        addSound("hold", "hold", -15);

    }

    private void addSound(String name, String file, float volume) {
        soundMap.put(name, new SoundClip(file, volume));
    }

    @Override
    public void playSound(String name) {
        if (name.equals("buttons") && name.equals("press")) {
            soundMap.get(name).play();
        }
        if (muteMusic && muteSound) {
            return;
        }
        if (muteMusic && !muteSound) {
            if (!name.equals("main")) {
                soundMap.get(name).play();
            }
        }
        if (!muteMusic && muteSound) {
            if (name.equals("main")) {
                soundMap.get(name).play();
            }
        } else if(!muteMusic && !muteSound) {
            soundMap.get(name).play();
        }
    }

    @Override
    public void stopSound(String name) {
        soundMap.get(name).stop();
    }

    @Override
    public void loopSound(String name) {
        if (!muteMusic) {
            soundMap.get(name).loop();
        }
    }

    @Override
    public void stopFXSound() {
        for (String name : soundMap.keySet()) {
            if (!name.equals("main")
                    && !name.equals("amazing")
                    && !name.equals("fantastic")
                    && !name.equals("verygood")
                    && !name.equals("wonderful")
                    && !name.equals("brilliant")
                    && !name.equals("excellent")
                    && !name.equals("wow")) {
                soundMap.get(name).stop();
            }
        }
    }

    @Override
    public void stopAllSound(boolean main) {
        if (!main) {
            for (String name : soundMap.keySet()) {
                if (!name.equals("main") && !name.equals("buttons")) {
                    soundMap.get(name).stop();
                }
            }
        } else {
            for (String name : soundMap.keySet()) {
                if (!name.equals("buttons")) {
                    soundMap.get(name).stop();
                }
            }
        }
    }

    @Override
    public void muteSound(boolean state) {
        muteSound = state;
    }

    @Override
    public void muteMusic(boolean state) {
        muteMusic = state;
    }

    public boolean getMuteSound() {
        return muteSound;
    }

}
