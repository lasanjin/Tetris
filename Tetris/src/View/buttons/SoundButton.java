package View.buttons;

import Utils.Constants;
import View.sound.IControlSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SoundButton extends JComponent {
    private BufferedImage onNormal;
    private BufferedImage onMouse;
    private BufferedImage onClick;
    private BufferedImage offNormal;
    private BufferedImage offMouse;
    private BufferedImage offClick;
    private BufferedImage currentImage;
    private IControlSound controlSound;
    private boolean on;


    public SoundButton() {
        Dimension SOUND_BUTTON_POS = Constants.getSoundButtonPos();
        Dimension ONOFF_BUTTON_SIZE = Constants.getOnoffButtonSize();
        setBounds(SOUND_BUTTON_POS.width, SOUND_BUTTON_POS.height, ONOFF_BUTTON_SIZE.width, ONOFF_BUTTON_SIZE.height);
        loadImages();
        currentImage = onNormal;
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
                currentImage = onClick;
                controlSound.playSound("press");
            } else {
                currentImage = offClick;
                controlSound.muteSound(false);
                controlSound.playSound("press");
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            on = !on;
            if (on) {
                currentImage = onNormal;
            } else {
                controlSound.stopAllSound(false);
                controlSound.muteSound(true);
                currentImage = offNormal;
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (on) {
                currentImage = onMouse;
            } else {
                currentImage = offMouse;
            }
            repaint();
            controlSound.playSound("buttons");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (on) {
                currentImage = onNormal;
            } else {
                currentImage = offNormal;
            }
            repaint();
        }
    }

    private void loadImages() {
        try {
            onNormal = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/onoff/onnormal.jpg"));
            onMouse = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/onoff/onmouse.jpg"));
            onClick = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/onoff/onclick.jpg"));
            offNormal = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/onoff/offnormal.jpg"));
            offMouse = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/onoff/offmouse.jpg"));
            offClick = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/onoff/offclick.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }
}
