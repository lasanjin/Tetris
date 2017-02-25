package View;

import Tetriminos.ITetrimino;
import Tetris.IControl;
import Sound.*;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * This class updates the View.
 */
public class View extends JFrame implements IControlView {
    private Menu menu;
    private Settings settings;
    private Info info;
    private Frame frame;
    private Board board;
    private Panel panel;


    public View(Sound sound) {
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

        setTitle("Tetris 2.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void addIControl(IControl controller) {
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
        if (!state) {
            settings.setVisible(false);
            setGlassPane(menu);
            menu.setVisible(true);
        } else {
            menu.setVisible(false);
            setGlassPane(settings);
            settings.setVisible(state);
        }
    }

    @Override
    public void showInfo(boolean state) {
        if (!state) {
            info.setVisible(false);
            setGlassPane(menu);
            menu.setVisible(true);
        } else {
            menu.setVisible(false);
            setGlassPane(info);
            info.setVisible(state);
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
