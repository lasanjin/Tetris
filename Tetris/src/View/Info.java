package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class paints the Info view.
 */
public class Info extends JPanel {
    private BufferedImage info;
    private IControlView controlView;


    public Info() {
        addMouseListener(new InfoMouse());
        initImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(info,0,0,null);
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

    private void initImage() {
        try {
            info = ImageIO.read(getClass().getClassLoader().getResource("View/frame/info.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }
}
