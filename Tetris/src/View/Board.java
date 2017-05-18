package View;

import Model.tetriminos.ITetrimino;
import Utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class paints the Model game board.
 */
public class Board extends JComponent {
    protected Dimension TETRIMINO_SIZE = Constants.getTetriminoSize();
    private HashMap<Character, BufferedImage> imageMap;
    private BufferedImage boardImage;
    private ITetrimino active;
    private char[][] board;
    private ITetrimino ghost;
    protected boolean paint;


    public Board() {
        Dimension BOARD_SIZE = Constants.getBoardSize();
        setBounds(10, 9, BOARD_SIZE.width, BOARD_SIZE.height);
        imageMap = new HashMap(7);
        loadImageMap();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(boardImage, 0, 0, null);
        if(paint) {
            if (board != null) {
                updateBoard(g);
            }
            if (ghost != null) {
                updateGhost(g);
            }
            if (active != null) {
                updateTetrimino(g);
            }
        }
    }

    private void updateGhost(Graphics g) {
        char[][] tetrimino = ghost.getShape();
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (checkChar(c)) {
                    int xPos = ghost.getX();
                    int yPos = ghost.getY();
                    drawTetrimino(g, 'G',
                            (xPos + col * TETRIMINO_SIZE.width),
                            (yPos + row * TETRIMINO_SIZE.height));
                }
            }
        }
    }

    private void updateBoard(Graphics g) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char c = board[row][col];
                if (checkChar(c)) {
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

    private void updateTetrimino(Graphics g) {
        char[][] tetrimino = active.getShape();
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (checkChar(c)) {
                    int xPos = active.getX();
                    int yPos = active.getY();
                    drawTetrimino(g, c,
                            (xPos + col * TETRIMINO_SIZE.width),
                            (yPos + row * TETRIMINO_SIZE.height));
                }
            }
        }
    }

    protected void drawTetrimino(Graphics g, char c, int xPos, int yPos) {
        g.drawImage(imageMap.get(c), xPos, yPos, null);
    }

    protected boolean checkChar(char c) {
        return c != ' ';
    }

    private void loadImageMap() {
        try {
            imageMap.put('I', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/I.jpg")));
            imageMap.put('J', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/J.jpg")));
            imageMap.put('L', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/L.jpg")));
            imageMap.put('O', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/O.jpg")));
            imageMap.put('S', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/S.jpg")));
            imageMap.put('T', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/T.jpg")));
            imageMap.put('Z', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/Z.jpg")));
            imageMap.put('G', ImageIO.read(getClass().getClassLoader().getResource("Resources/tetriminos/ghost.jpg")));
            boardImage = ImageIO.read(getClass().getClassLoader().getResource("Resources/frame/board.jpg"));
        } catch (IOException e) {
            e.getCause();
        }
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
