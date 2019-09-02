package view.sound;

import model.IModelSound;
import utils.FileHandler;
import utils.SoundClip;

import java.util.Map;

/**
 * This class holds all the .wav files (sounds)
 */
public class Sound implements IControlSound, IModelSound {
    private Map<String, SoundClip> soundMap;
    private boolean muteSound;
    private boolean muteMusic;


    public Sound() {
        soundMap = FileHandler.getSoundMap();
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

    public boolean isSoundMute() {
        return muteSound;
    }
}