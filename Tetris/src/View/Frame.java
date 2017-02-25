package View;

import Tetris.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class paints the frame.
 */
public class Frame extends JPanel {
    private BufferedImage frame;


    public Frame() {
        setLayout(null);
        setPreferredSize(new Dimension(Constants.getFrameSize()));
        loadImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(frame, 0, 0, null);
    }

    private void loadImage() {
        try {
            frame = ImageIO.read(getClass().getClassLoader().getResource("View/frame/panel.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

}
