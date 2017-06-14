package view.buttons;

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
 * This class handles Music On/Off Button action.
 */
public class MusicButton extends JComponent {
    private Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;
    private IControlSound controlSound;
    private IController controller;
    private boolean on;


    public MusicButton() {
        Dimension MUSIC_BUTTON_POS = Constants.getMusicButtonPos();
        Dimension ONOFF_BUTTON_SIZE = Constants.getOnoffButtonSize();
        setBounds(MUSIC_BUTTON_POS.width, MUSIC_BUTTON_POS.height, ONOFF_BUTTON_SIZE.width, ONOFF_BUTTON_SIZE.height);
        imageMap = FileHandler.getOnOffButtonImageMap();
        currentImage = imageMap.get("onNormal");
        on = true;
        addMouseListener(new MusicMouse());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }

    private class MusicMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (on) {
                currentImage = imageMap.get("onClick");
            } else {
                currentImage = imageMap.get("offClick");
            }
            repaint();
            controlSound.playSound("press");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            on = !on;
            if (on) {
                controlSound.muteMusic(false);
                if (controller.getIsRunning() && !controller.getIsMenu()) {
                    controlSound.loopSound("main");
                }
                currentImage = imageMap.get("onNormal");
            } else {
                controlSound.muteMusic(true);
                controlSound.stopSound("main");
                currentImage = imageMap.get("offNormal");
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (on) {
                currentImage = imageMap.get("onMouse");
            } else {
                currentImage = imageMap.get("offMouse");
            }
            repaint();
            controlSound.playSound("buttons");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (on) {
                currentImage = imageMap.get("onNormal");
            } else {
                currentImage = imageMap.get("offNormal");
            }
            repaint();
        }
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

    public void addIControl(IController controller) {
        this.controller = controller;
    }

}
