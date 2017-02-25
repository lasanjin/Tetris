package View;

import Sound.IControlSound;
import Tetris.Constants;
import Tetris.IControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class handles Sound On/Off Button action.
 */
public class PlayButton extends JComponent {
    private BufferedImage playNormal;
    private BufferedImage playMouse;
    private BufferedImage playClick;
    private BufferedImage currentImage;
    private IControlView controlView;
    private IControl controller;
    private IControlSound controlSound;


    public PlayButton() {
        Dimension PLAY_BUTTON_POS = Constants.getPlayButtonPos();
        Dimension BUTTON_SIZE = Constants.getButtonSize();
        setBounds(PLAY_BUTTON_POS.width, PLAY_BUTTON_POS.height, BUTTON_SIZE.width, BUTTON_SIZE.height);
        loadImages();
        currentImage = playNormal;
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
            currentImage = playClick;
            repaint();
            controlSound.playSound("press");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentImage = playNormal;
            repaint();
            controlView.showMenu(false);
            controller.stopTetris();
            controller.startTetris();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            controlSound.playSound("buttons");
            currentImage = playMouse;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = playNormal;
            repaint();
        }
    }

    private void loadImages() {
        try {
            playNormal = ImageIO.read(getClass().getClassLoader().getResource("View/buttons/playnormal.jpg"));
            playMouse = ImageIO.read(getClass().getClassLoader().getResource("View/buttons/playmouse.jpg"));
            playClick = ImageIO.read(getClass().getClassLoader().getResource("View/buttons/playclick.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

    public void addIControl(IControl controller) {
        this.controller = controller;
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

}
