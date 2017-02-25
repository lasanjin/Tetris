package View;

import Sound.IControlSound;
import Tetris.IControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class paints and updates Settigs view.
 */
public class Settings extends JPanel {
    private BufferedImage settings;
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
        initImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(settings,0,0,null);
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

    private void initImage() {
        try {
            settings = ImageIO.read(getClass().getClassLoader().getResource("View/frame/settings.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

    public void addIControlSound(IControlSound sound) {
        soundButton.addIControlSound(sound);
        musicButton.addIControlSound(sound);
    }

    public void addIControl(IControl controller) {
        musicButton.addIControl(controller);
    }
}
