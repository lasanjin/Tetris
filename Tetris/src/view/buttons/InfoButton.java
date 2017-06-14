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
 * This class handles Info Button action.
 */
public class InfoButton extends JComponent {
    private Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;
    private IControlSound controlSound;
    private IControlView controlView;


    public InfoButton() {
        Dimension INFO_BUTTON_POS = Constants.getInfoButtonPos();
        Dimension BUTTON_SIZE = Constants.getButtonSize();
        setBounds(INFO_BUTTON_POS.width, INFO_BUTTON_POS.height, BUTTON_SIZE.width, BUTTON_SIZE.height);
        imageMap = FileHandler.getInfoButtonMap();
        currentImage = imageMap.get("infoNormal");
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
            currentImage = imageMap.get("infoClick");;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentImage = imageMap.get("infoNormal");
            repaint();
            controlView.showInfo(true);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            currentImage = imageMap.get("infoMouse");
            repaint();
            controlSound.playSound("buttons");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = imageMap.get("infoNormal");
            repaint();
        }
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }

}
