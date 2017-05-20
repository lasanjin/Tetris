import Controller.Controller;
import Utils.FileHandler;
import View.sound.Sound;
import View.*;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
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
