package View;

import Tetriminos.ITetrimino;
import Tetris.IControl;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Observer class for updating the view.
 */
public interface IControlView {
    void updateTetrimino(ITetrimino activeTetrimino);
    void updateNextTetrimino(ArrayList<ITetrimino> nextTetriminos);
    void updateBoard(char[][] board);
    void updateScore(int score);
    void updateLevel(int level);
    void updateLines(int lines);
    void showMenu(boolean state);
    void showSettings(boolean state);
    void showInfo(boolean state);
    void addKeys(KeyListener keys);
    void removeKeys(KeyListener keys);
    void addMouse(MouseListener mouse);
    void addIControl(IControl controller);
    void updateGhost(ITetrimino ghost);
    void updateHold(ITetrimino hold);
    void paintBoard(boolean state);
}
