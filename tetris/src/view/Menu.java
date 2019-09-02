package view;

import model.IControlView;
import utils.FileHandler;
import view.buttons.IController;
import view.buttons.InfoButton;
import view.buttons.PlayButton;
import view.buttons.SettingsButton;
import view.sound.IControlSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class paints the Menu view.
 */
public class Menu extends JPanel {
    private BufferedImage menuImage;
    private PlayButton playButton;
    private SettingsButton settingsButton;
    private InfoButton infoButton;


    public Menu() {
        setLayout(null);
        menuImage = FileHandler.getMenuImage();
        playButton = new PlayButton();
        settingsButton = new SettingsButton();
        infoButton = new InfoButton();
        add(playButton);
        add(settingsButton);
        add(infoButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(menuImage,0,0,null);
    }

    public void addIControlView(IControlView view) {
        playButton.addIControlView(view);
        settingsButton.addIControlView(view);
        infoButton.addIControlView(view);
    }

    public void addIControl(IController controller) {
        playButton.addIControl(controller);
    }

    public void addIControlSound(IControlSound sound) {
        playButton.addIControlSound(sound);
        settingsButton.addIControlSound(sound);
        infoButton.addIControlSound(sound);
    }
}