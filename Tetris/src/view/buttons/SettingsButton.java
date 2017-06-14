package view.buttons;

import model.IControlView;
import utils.Constants;
import utils.FileHandler;
import view.sound.IControlSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * This class handles MainView.sound Button action.
 */
public class SettingsButton extends JComponent {
    private Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;
    private IControlView controlView;
    private IControlSound controlSound;


    public SettingsButton() {
        Dimension SETTINGS_BUTTON_POS = Constants.getSettingsButtonPos();
        Dimension BUTTON_SIZE = Constants.getButtonSize();
        setBounds(SETTINGS_BUTTON_POS.width, SETTINGS_BUTTON_POS.height, BUTTON_SIZE.width, BUTTON_SIZE.height);
        imageMap = FileHandler.getSettingsButtonImageMap();
        currentImage = imageMap.get("settingsNormal");
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
            currentImage = imageMap.get("settingsClick");
            repaint();
            controlSound.playSound("press");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentImage = imageMap.get("settingsNormal");
            repaint();
            controlView.showSettings(true);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            currentImage = imageMap.get("settingsMouse");
            repaint();
            controlSound.playSound("buttons");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = imageMap.get("settingsNormal");
            repaint();
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

}
