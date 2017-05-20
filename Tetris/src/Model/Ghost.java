package Model;

import Model.tetriminos.ITetrimino;
import Model.tetriminos.TetriminoFactory;
import Utils.Constants;

import java.awt.*;

/**
 * This class handles "ghost piece" logic.
 */
public class Ghost implements IGhost {
    private Dimension TETRIMINO_SIZE = Constants.getTetriminoSize();
    private Dimension BOARD_SIZE = Constants.getBoardSize();
    private IControlView controlView;
    private ITetrimino ghost;
    private char[][] board;


    public Ghost() {
        ghost = TetriminoFactory.createGhost(0, 0);
    }

    private boolean checkCollision(int dy) {
        char[][] tetrimino = ghost.getShape();
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (checkChar(c)) {
                    int newX = (ghost.getX() / TETRIMINO_SIZE.width) + col;
                    int newY = (ghost.getY() + dy) / TETRIMINO_SIZE.height + row;
                    try {
                        char c_ = board[newY][newX];
                        if (checkChar(c_)) {
                            return false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.getCause();
                    }
                }
            }
        }
        return true;
    }

    private boolean checkYCollision(int dy) {
        char[][] tetrimino = ghost.getShape();
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (checkChar(c)) {
                    int newY = (ghost.getY() + dy) / TETRIMINO_SIZE.height + row;
                    if (newY >= BOARD_SIZE.height / TETRIMINO_SIZE.height) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkChar(char c) {
        return c != ' ';
    }

    @Override
    public void updateBoard(char[][] board) {
        this.board = board;
    }

    @Override
    public void updateGhost(ITetrimino active) {
        ghost.setShape(active.getShape());
        ghost.setPosition(active.getX(), active.getY());
    }

    @Override
    public void placeGhost() {
        while (checkYCollision(TETRIMINO_SIZE.height) && checkCollision(TETRIMINO_SIZE.height)) {
            ghost.updatePosition(0, TETRIMINO_SIZE.height);
            controlView.updateGhost(ghost);
        }
    }

    public void addIControlView(IControlView view) {
        controlView = view;
    }
}