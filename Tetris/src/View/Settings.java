package View;

import Model.IControlView;
import Utils.FileHandler;
import View.buttons.IController;
import View.buttons.MusicButton;
import View.buttons.SoundButton;
import View.sound.IControlSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * This class paints and updates Settigs view.
 */
public class Settings extends JPanel {
    private BufferedImage settingsImage;
    private IControlView controlView;
    private SoundButton soundButton;
    private MusicButton musicButton;


    public Settings() {
        setLayout(null);
        soundButton = new SoundButton();
        musicButton = new MusicButton();
        add(soundButton);
        add(musicButton);
        addMouseListener(new SettingsMouse());
        settingsImage = FileHandler.getSettingsImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(settingsImage,0,0,null);
    }

    private class SettingsMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            controlView.showSettings(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

    public void addIControlSound(IControlSound sound) {
        soundButton.addIControlSound(sound);
        musicButton.addIControlSound(sound);
    }

    public void addIControl(IController controller) {
        musicButton.addIControl(controller);
    }
}