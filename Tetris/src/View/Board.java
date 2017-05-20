package View;

import Model.tetriminos.ITetrimino;
import Utils.Constants;
import Utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * This class paints the Model game board.
 */
public class Board extends JComponent {
    protected Dimension TETRIMINO_SIZE = Constants.getTetriminoSize();
    private Map<Character, BufferedImage> imageMap;
    private BufferedImage boardImage;
    private ITetrimino active;
    private char[][] board;
    private ITetrimino ghost;
    protected boolean paint;


    public Board() {
        Dimension BOARD_SIZE = Constants.getBoardSize();
        setBounds(10, 9, BOARD_SIZE.width, BOARD_SIZE.height);
        imageMap = FileHandler.getTetriminoImages();
        boardImage = FileHandler.getBoardImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(boardImage, 0, 0, null);
        if(paint) {
            if (board != null) {
                updateBoard(g);
            }
            if (ghost != null) {
                updateTetrimino(ghost, g);
            }
            if (active != null) {
                updateTetrimino(active, g);
            }
        }
    }

    private void updateBoard(Graphics g) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char c = board[row][col];
                if (!isEmpty(c)) {
                    drawBoard(g, c,
                            col * TETRIMINO_SIZE.width,
                            row * TETRIMINO_SIZE.height);
                }
            }
        }
    }

    private void drawBoard(Graphics g, char c, int xPos, int yPos) {
        g.drawImage(imageMap.get(c), xPos, yPos, null);
    }

    private void updateTetrimino(ITetrimino t, Graphics g) {
        char[][] tetrimino = t.getShape();
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (!isEmpty(c)) {
                    int xPos = t.getX();
                    int yPos = t.getY();
                    drawTetrimino(g, t.getChar(),
                            (xPos + col * TETRIMINO_SIZE.width),
                            (yPos + row * TETRIMINO_SIZE.height));
                }
            }
        }
    }

    protected void drawTetrimino(Graphics g, char c, int xPos, int yPos) {
        g.drawImage(imageMap.get(c), xPos, yPos, null);
    }

    protected boolean isEmpty(char c) {
        return c == ' ';
    }

    public void updateTetrimino(ITetrimino active) {
        this.active = active;
        repaint();
    }

    public void updateBoard(char[][] board) {
        this.board = board;
        repaint();
    }

    public void updateGhost(ITetrimino ghost) {
        this.ghost = ghost;
        repaint();
    }

    public void paintBoard(boolean state){
        paint = state;
        repaint();
    }
}