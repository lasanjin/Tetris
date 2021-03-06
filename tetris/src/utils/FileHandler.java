package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all game gui, images and sound.
 */
public class FileHandler {
    private static Map<Character, BufferedImage> tetriminoImageMap;
    private static Map<String, BufferedImage> infoButtonImageMap;
    private static Map<String, BufferedImage> playButtonImageMap;
    private static Map<String, BufferedImage> settingsButtonImageMap;
    private static Map<String, BufferedImage> onfOffButtonImageMap;
    private static Map<String, SoundClip> soundMap;
    private static BufferedImage boardImage;
    private static BufferedImage frameImage;
    private static BufferedImage infoImage;
    private static BufferedImage settingsImage;
    private static BufferedImage menuImage;

    public FileHandler() {
        loadSounds();
        loadTetriminoImages();
        loadInfoButtonImages();
        loadPlayButtonImages();
        loadOnOffButtonImages();
        loadSettingsButtonImages();
        loadBoardImage();
        loadFrameImage();
        loadInfoImage();
        loadSettingsImage();
        loadMenuImage();
    }

    private void loadSounds() {
        soundMap = new HashMap<>();
        addSound("main", "main", -25);
        addSound("lockdown", "lockdown", -15);
        addSound("move", "move", -10);
        addSound("rotate", "rotate", -15);
        addSound("rotatefail", "rotatefail", -10);
        addSound("clearline", "clearline", -15);
        addSound("doubleclear", "doubleclear", -15);
        addSound("tripleclear", "tripleclear", -15);
        addSound("buttons", "buttons", -10);
        addSound("press", "press", -8);
        addSound("harddrop", "harddrop", -30);
        addSound("levelup", "levelup", -15);
        addSound("amazing", "amazing", -15);
        addSound("fantastic", "fantastic", -15);
        addSound("verygood", "verygood", -15);
        addSound("brilliant", "brilliant", -15);
        addSound("wow", "wow", -15);
        addSound("excellent", "excellent", -15);
        addSound("wonderful", "wonderful", -15);
        addSound("gameover", "gameover", -15);
        addSound("one", "one", -15);
        addSound("two", "two", -15);
        addSound("three", "three", -15);
        addSound("hold", "hold", -15);

    }

    private void addSound(String name, String file, float volume) {
        soundMap.put(name, new SoundClip(file, volume));
    }

    private void loadTetriminoImages() {
        tetriminoImageMap = new HashMap<>();
        try {
            tetriminoImageMap.put('I', ImageIO.read(getResource("resources/tetriminos/I.jpg")));
            tetriminoImageMap.put('J', ImageIO.read(getResource("resources/tetriminos/J.jpg")));
            tetriminoImageMap.put('L', ImageIO.read(getResource("resources/tetriminos/L.jpg")));
            tetriminoImageMap.put('O', ImageIO.read(getResource("resources/tetriminos/O.jpg")));
            tetriminoImageMap.put('S', ImageIO.read(getResource("resources/tetriminos/S.jpg")));
            tetriminoImageMap.put('T', ImageIO.read(getResource("resources/tetriminos/T.jpg")));
            tetriminoImageMap.put('Z', ImageIO.read(getResource("resources/tetriminos/Z.jpg")));
            tetriminoImageMap.put('G', ImageIO.read(getResource("resources/tetriminos/ghost.jpg")));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadInfoButtonImages() {
        infoButtonImageMap = new HashMap<>();
        try {
            infoButtonImageMap.put(
                    "infoNormal",
                    ImageIO.read(getResource("resources/buttons/info/infonormal.jpg")));
            infoButtonImageMap.put(
                    "infoMouse",
                    ImageIO.read(getResource("resources/buttons/info/infomouse.jpg")));
            infoButtonImageMap.put(
                    "infoClick",
                    ImageIO.read(getResource("resources/buttons/info/infoclick.jpg")));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadPlayButtonImages() {
        playButtonImageMap = new HashMap<>();
        try {
            playButtonImageMap.put(
                    "playNormal",
                    ImageIO.read(getResource("resources/buttons/play/playnormal.jpg")));
            playButtonImageMap.put(
                    "playMouse",
                    ImageIO.read(getResource("resources/buttons/play/playmouse.jpg")));
            playButtonImageMap.put(
                    "playClick",
                    ImageIO.read(getResource("resources/buttons/play/playclick.jpg")));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadOnOffButtonImages() {
        onfOffButtonImageMap = new HashMap<>();
        try {
            onfOffButtonImageMap.put(
                    "onNormal",
                    ImageIO.read(getResource("resources/buttons/onoff/onnormal.jpg")));
            onfOffButtonImageMap.put(
                    "onMouse",
                    ImageIO.read(getResource("resources/buttons/onoff/onmouse.jpg")));
            onfOffButtonImageMap.put(
                    "onClick",
                    ImageIO.read(getResource("resources/buttons/onoff/onclick.jpg")));
            onfOffButtonImageMap.put(
                    "offNormal",
                    ImageIO.read(getResource("resources/buttons/onoff/offnormal.jpg")));
            onfOffButtonImageMap.put(
                    "offMouse",
                    ImageIO.read(getResource("resources/buttons/onoff/offmouse.jpg")));
            onfOffButtonImageMap.put(
                    "offClick",
                    ImageIO.read(getResource("resources/buttons/onoff/offclick.jpg")));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadSettingsButtonImages() {
        settingsButtonImageMap = new HashMap<>();
        try {
            settingsButtonImageMap.put(
                    "settingsNormal",
                    ImageIO.read(getResource("resources/buttons/settings/settingsnormal.jpg")));
            settingsButtonImageMap.put(
                    "settingsMouse",
                    ImageIO.read(getResource("resources/buttons/settings/settingsmouse.jpg")));
            settingsButtonImageMap.put(
                    "settingsClick",
                    ImageIO.read(getResource("resources/buttons/settings/settingsclick.jpg")));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadMenuImage() {
        try {
            menuImage = ImageIO.read(getResource("resources/frame/menu.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadFrameImage() {
        try {
            frameImage = ImageIO.read(getResource("resources/frame/panel.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadBoardImage() {
        try {
            boardImage = ImageIO.read(getResource("resources/frame/board.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadInfoImage() {
        try {
            infoImage = ImageIO.read(getResource("resources/frame/info.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadSettingsImage() {
        try {
            settingsImage = ImageIO.read(getResource("resources/frame/settings.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
    }

    private URL getResource(String path) {
        return getClass().getClassLoader().getResource(path);
    }

    public static Map<String, SoundClip> getSoundMap() {
        return soundMap;
    }

    public static Map<Character, BufferedImage> getTetriminoImages() {
        return tetriminoImageMap;
    }

    public static Map<String, BufferedImage> getInfoButtonMap() {
        return infoButtonImageMap;
    }

    public static Map<String, BufferedImage> getPlayButtonMap() {
        return playButtonImageMap;
    }

    public static Map<String, BufferedImage> getOnOffButtonImageMap() {
        return onfOffButtonImageMap;
    }

    public static Map<String, BufferedImage> getSettingsButtonImageMap() {
        return settingsButtonImageMap;
    }

    public static BufferedImage getBoardImage() {
        return boardImage;
    }

    public static BufferedImage getFramImage() {
        return frameImage;
    }

    public static BufferedImage getInfoImage() {
        return infoImage;
    }

    public static BufferedImage getSettingsImage() {
        return settingsImage;
    }

    public static BufferedImage getMenuImage() {
        return menuImage;
    }
}