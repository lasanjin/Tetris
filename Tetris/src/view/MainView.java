package view;

import model.IControlView;
import model.tetriminos.ITetrimino;
import view.buttons.IController;
import view.sound.Sound;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * This class updates the MainView.
 */
public class MainView extends JFrame implements IControlView {
    private Menu menu;
    private Settings settings;
    private Info info;
    private Frame frame;
    private Board board;
    private Panel panel;


    public MainView(Sound sound) {
        frame = new Frame();
        setContentPane(frame);

        menu = new Menu();
        menu.addIControlSound(sound);
        menu.addIControlView(this);
        setGlassPane(menu);
        menu.setVisible(true);

        settings = new Settings();
        settings.addIControlView(this);
        settings.addIControlSound(sound);

        info = new Info();
        info.addIControlView(this);

        board = new Board();
        add(board);

        panel = new Panel();
        add(panel);

        setTitle("model 2.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addIControl(IController controller) {
        menu.addIControl(controller);
        settings.addIControl(controller);
    }

    @Override
    public void addKeys(KeyListener keys) {
        addKeyListener(keys);
    }

    @Override
    public void removeKeys(KeyListener keys) {
        removeKeyListener(keys);
    }

    @Override
    public void addMouse(MouseListener mouse) {
        frame.addMouseListener(mouse);
    }

    @Override
    public void showMenu(boolean state) {
        setGlassPane(menu);
        menu.setVisible(state);
    }

    @Override
    public void showSettings(boolean state) {
        toggleView(settings, state);
    }

    @Override
    public void showInfo(boolean state) {
        toggleView(info, state);
    }

    public void toggleView(JPanel panel, boolean state) {
        if (!state) {
            panel.setVisible(false);
            setGlassPane(menu);
            menu.setVisible(true);
        } else {
            menu.setVisible(false);
            setGlassPane(panel);
            panel.setVisible(state);
        }
    }

    @Override
    public void updateTetrimino(ITetrimino activeTetrimino) {
        board.updateTetrimino(activeTetrimino);
    }

    @Override
    public void updateNextTetrimino(ArrayList<ITetrimino> nextTetriminos) {
        panel.updateNextTetrimino(nextTetriminos);
    }

    @Override
    public void updateHold(ITetrimino hold) {
        panel.updateHold(hold);
    }

    @Override
    public void updateGhost(ITetrimino ghost) {
        board.updateGhost(ghost);
    }

    @Override
    public void updateBoard(char[][] board) {
        this.board.updateBoard(board);
    }

    @Override
    public void updateScore(int score) {
        panel.updateScore(score);
    }

    @Override
    public void updateLevel(int level) {
        panel.updateLevel(level);
    }

    @Override
    public void updateLines(int lines) {
        panel.updateLines(lines);
    }

    @Override
    public void paintBoard(boolean state) {
        board.paintBoard(state);
    }
}