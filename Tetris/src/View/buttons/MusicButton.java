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

/**
 * This class handles Music On/Off Button action.
 */
public class MusicButton extends JComponent {
    private BufferedImage onNormal;
    private BufferedImage onMouse;
    private BufferedImage onClick;
    private BufferedImage offNormal;
    private BufferedImage offMouse;
    private BufferedImage offClick;
    private BufferedImage currentImage;
    private IControlSound controlSound;
    private IController controller;
    private boolean on;


    public MusicButton() {
        Dimension MUSIC_BUTTON_POS = Constants.getMusicButtonPos();
        Dimension ONOFF_BUTTON_SIZE = Constants.getOnoffButtonSize();
        setBounds(MUSIC_BUTTON_POS.width, MUSIC_BUTTON_POS.height, ONOFF_BUTTON_SIZE.width, ONOFF_BUTTON_SIZE.height);
        loadImages();
        currentImage = onNormal;
        on = true;
        addMouseListener(new MusicMouse());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(currentImage,0,0,null);
    }

    private class MusicMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (on) {
                currentImage = onClick;
            } else {
                currentImage = offClick;
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
                currentImage = onNormal;
            } else {
                controlSound.muteMusic(true);
                controlSound.stopSound("main");
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

    public void addIControl(IController controller) {
        this.controller = controller;
    }

}
