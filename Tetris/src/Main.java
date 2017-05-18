import Controller.Controller;
import View.sound.Sound;
import View.*;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Sound sound = new Sound();
        MainView view = new MainView(sound);
        Controller controller = new Controller(view, sound);
        controller.run();
    }
}
