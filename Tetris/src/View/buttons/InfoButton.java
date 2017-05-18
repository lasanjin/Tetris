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
 * This class handles Info Button action.
 */
public class InfoButton extends JComponent {
    private BufferedImage infoNormal;
    private BufferedImage infoMouse;
    private BufferedImage infoClick;
    private BufferedImage currentImage;
    private IControlSound controlSound;
    private IControlView controlView;


    public InfoButton() {
        Dimension INFO_BUTTON_POS = Constants.getInfoButtonPos();
        Dimension BUTTON_SIZE = Constants.getButtonSize();
        setBounds(INFO_BUTTON_POS.width, INFO_BUTTON_POS.height, BUTTON_SIZE.width, BUTTON_SIZE.height);
        initImage();
        currentImage = infoNormal;
        addMouseListener(new InfoMouse());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }

    private class InfoMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            controlSound.playSound("press");
            currentImage = infoClick;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentImage = infoNormal;
            repaint();
            controlView.showInfo(true);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            currentImage = infoMouse;
            repaint();
            controlSound.playSound("buttons");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = infoNormal;
            repaint();
        }
    }

    private void initImage() {
        try {
            infoNormal = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/infonormal.jpg"));
            infoMouse = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/infomouse.jpg"));
            infoClick = ImageIO.read(getClass().getClassLoader().getResource("Resources/buttons/infoclick.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

}
