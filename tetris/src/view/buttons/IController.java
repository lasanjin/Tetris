package view.buttons;

/**
 * Observer interface, notifying controller class
 */
public interface IController {
    void stopTetris();
    void startTetris();
    boolean getIsRunning();
    boolean getIsMenu();
}
