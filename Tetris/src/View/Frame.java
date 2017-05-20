package View;

import Utils.Constants;
import Utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class paints the frameImage.
 */
public class Frame extends JPanel {
    private BufferedImage frameImage;


    public Frame() {
        setLayout(null);
        setPreferredSize(new Dimension(Constants.getFrameSize()));
        frameImage = FileHandler.getFramImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(frameImage, 0, 0, null);
    }
}
