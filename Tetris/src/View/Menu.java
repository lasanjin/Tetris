package View;

import Model.IControlView;
import View.buttons.IController;
import View.buttons.InfoButton;
import View.buttons.PlayButton;
import View.buttons.SettingsButton;
import View.sound.IControlSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class paints the Menu view.
 */
public class Menu extends JPanel {
    private BufferedImage menuButton;
    private PlayButton playButton;
    private SettingsButton settingsButton;
    private InfoButton infoButton;


    public Menu() {
        setLayout(null);
        playButton = new PlayButton();
        settingsButton = new SettingsButton();
        infoButton = new InfoButton();
        add(playButton);
        add(settingsButton);
        add(infoButton);
        loadImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(menuButton,0,0,null);
    }

    private void loadImage() {
        try {
            menuButton = ImageIO.read(getClass().getClassLoader().getResource("Resources/frame/menu.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
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
