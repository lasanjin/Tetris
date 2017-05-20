package View.buttons;

import Utils.Constants;
import Utils.FileHandler;
import View.sound.IControlSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Map;

public class SoundButton extends JComponent {
    private Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;
    private IControlSound controlSound;
    private boolean on;


    public SoundButton() {
        Dimension SOUND_BUTTON_POS = Constants.getSoundButtonPos();
        Dimension ONOFF_BUTTON_SIZE = Constants.getOnoffButtonSize();
        setBounds(SOUND_BUTTON_POS.width, SOUND_BUTTON_POS.height, ONOFF_BUTTON_SIZE.width, ONOFF_BUTTON_SIZE.height);
        imageMap = FileHandler.getOnOffButtonImageMap();
        currentImage = imageMap.get("onNormal");
        on = true;
        addMouseListener(new SoundMouse());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }

    private class SoundMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (on) {
                currentImage = imageMap.get("onClick");
                controlSound.playSound("press");
            } else {
                currentImage = imageMap.get("offClick");
                controlSound.muteSound(false);
                controlSound.playSound("press");
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            on = !on;
            if (on) {
                currentImage = imageMap.get("onNormal");
            } else {
                controlSound.stopAllSound(false);
                controlSound.muteSound(true);
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
}
