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
 * This class handles MainView.sound On/Off Button action.
 */
public class PlayButton extends JComponent {
    private Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;
    private IControlView controlView;
    private IController controller;
    private IControlSound controlSound;


    public PlayButton() {
        Dimension PLAY_BUTTON_POS = Constants.getPlayButtonPos();
        Dimension BUTTON_SIZE = Constants.getButtonSize();
        setBounds(PLAY_BUTTON_POS.width, PLAY_BUTTON_POS.height, BUTTON_SIZE.width, BUTTON_SIZE.height);
        imageMap = FileHandler.getPlayButtonMap();
        currentImage = imageMap.get("playNormal");
        addMouseListener(new PlayMouse());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }

    private class PlayMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            currentImage = imageMap.get("playClick");
            repaint();
            controlSound.playSound("press");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentImage = imageMap.get("playNormal");
            repaint();
            controlView.showMenu(false);
            controller.stopTetris();
            controller.startTetris();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            controlSound.playSound("buttons");
            currentImage = imageMap.get("playMouse");
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = imageMap.get("playNormal");
            repaint();
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

    public void addIControl(IController controller) {
        this.controller = controller;
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

}
