package View;

import Model.IControlView;
import Utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * This class paints the Info view.
 */
public class Info extends JPanel {
    private BufferedImage infoImage;
    private IControlView controlView;


    public Info() {
        addMouseListener(new InfoMouse());
        infoImage = FileHandler.getInfoImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(infoImage,0,0,null);
    }

    private class InfoMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            controlView.showInfo(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }
}