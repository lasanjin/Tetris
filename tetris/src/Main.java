import controller.Controller;
import utils.FileHandler;
import view.sound.Sound;
import view.*;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        // enable OpenGL to get accelerated performance in Linux:
        System.setProperty("sun.java2d.opengl", "True");
        System.setProperty("sun.java2d.accthreshold", "0");

        try {
            new FileHandler();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.getCause();
        }
        Sound sound = new Sound();
        MainView view = new MainView(sound);
        Controller controller = new Controller(view, sound);
        controller.run();
    }
}
