package Model;

import Model.tetriminos.ITetrimino;

/**
 * Observer interface for updating ghost class
 *
 */
public interface IGhost {
    void updateGhost(ITetrimino aciveTetrimino);
    void updateBoard(char[][] board);
    void placeGhost();
    void addIControlView(IControlView view);
}