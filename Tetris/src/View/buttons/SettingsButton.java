package View.buttons;

import Model.IControlView;
import Utils.Constants;
import View.sound.IControlSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class handles MainView.sound Button action.
 */
public class SettingsButton extends JComponent {
    private BufferedImage settingsNormal;
    private BufferedImage settingsMouse;
    private BufferedImage settingsClick;
    private BufferedImage currentImage;
    private IControlView controlView;
    private IControlSound controlSound;


    public SettingsButton() {
        Dimension SETTINGS_BUTTON_POS = Constants.getSettingsButtonPos();
        Dimension BUTTON_SIZE = Constants.getButtonSize();
        setBounds(SETTINGS_BUTTON_POS.width, SETTINGS_BUTTON_POS.height, BUTTON_SIZE.width, BUTTON_SIZE.height);
        loadImages();
        currentImage = settingsNormal;
        addMouseListener(new SettingsMouse());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }

    private class SettingsMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            currentImage = settingsClick;
            repaint();
            controlSound.playSound("press");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentImage = settingsNormal;
            repaint();
            controlView.showSettings(true);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            currentImage = settingsMouse;
            repaint();
            controlSound.playSound("buttons");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = settingsNormal;
            repaint();
        }
    }

    private void loadImages() {
        try {
            settingsNormal = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/settingsnormal.jpg"));
            settingsMouse = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/settingsmouse.jpg"));
            settingsClick = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/settingsclick.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

}
