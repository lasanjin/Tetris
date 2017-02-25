package Tetris;

import Sound.Sound;
import View.*;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Sound sound = new Sound();
        View view = new View(sound);
        Controller controller = new Controller(view, sound);
        controller.run();
    }
}
