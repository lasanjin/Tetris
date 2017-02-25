package Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class loads a .wav file
 */
public class SoundClip {
    private Clip clip;
    private FloatControl floatControl;
    private float volume;


    public SoundClip(String name, float volume) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource("sounds/" + name + ".wav")));
        } catch (Exception e) {
            System.out.println("Could not load " + name);
        }
        try {
            this.volume = volume;
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(volume);
        } catch (Exception e) {
            System.out.println("Could not set volume for " + name);
        }
    }

    public void play() {
        if (clip == null) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        if (clip == null) {
            return;
        }
        clip.loop(clip.LOOP_CONTINUOUSLY);
        while (!clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip == null) {
            return;
        }
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        if (clip == null) {
            return;
        }
        if (clip.isRunning()) {
            stop();
            clip.drain();
            clip.close();
        }
    }

    public void setVolume(float volume) {
        float newVolume = this.volume + volume;
        if (newVolume > -80 && newVolume < 3) {
            floatControl.setValue(newVolume);
            this.volume = newVolume;
        }
    }

}