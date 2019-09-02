package view;

import model.tetriminos.ITetrimino;
import utils.Constants;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class paints the right panel (score etc.). The positions and constants are hardcoded for
 * this specific layout.
 */
public class Panel extends Board {
    private int YPOS = TETRIMINO_SIZE.height;
    private int NEXT_YPOS = 154;
    private int SCORE_YPOS = 473;
    private int LEVEL_YPOS = 646;
    private int LINES_YPOS = 558;
    private ArrayList<ITetrimino> next;
    private ITetrimino hold;
    private int score;
    private int level;
    private int lines;

    public Panel() {
        Dimension PANEL_SIZE = Constants.getPanelSize();
        setBounds(291, 0, PANEL_SIZE.width, PANEL_SIZE.height);
    }

    public void paintComponent(Graphics g) {
        if (next != null) {
            drawNext(g);
        }
        if (hold != null) {
            drawHold(g);
        }
        drawScore(g);
        drawLevel(g);
        drawLines(g);
    }

    private void drawNext(Graphics g) {
        for (int i = 0; i < next.size(); i++) {
            ITetrimino nextTetrimino = next.get(i);
            char c = nextTetrimino.getChar();
            int xPos = calcXPos(c);
            int yPos = calcYNext(c, i);
            drawNextTetrimino(g, nextTetrimino, xPos, NEXT_YPOS + yPos);
        }
    }

    private int calcXPos(char c) {
        if (c == 'I') {
            return 18;
        } else if (c == 'O') {
            return 45;
        } else {
            return 32;
        }
    }

    private int calcYNext(char c, int i) {
        int yPos = i * 3 * YPOS;
        if (c == 'I') {
            return yPos;
        } else {
            return yPos + YPOS / 2;
        }
    }

    private void drawNextTetrimino(Graphics g, ITetrimino nextTetrimino, int xPos, int yPos) {
        char[][] tetrimino = nextTetrimino.getShape();
        drawTetrimino(tetrimino, xPos, yPos, g);
    }

    private void drawHold(Graphics g) {
        char c = hold.getChar();
        int xPos = calcXPos(c);
        int yPos = calcYHold(c);
        char[][] tetrimino = hold.getShape();
        drawTetrimino(tetrimino, xPos, yPos, g);
    }

    private void drawTetrimino(char[][] tetrimino, int xPos, int yPos, Graphics g) {
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (!isEmpty(c)) {
                    drawTetrimino(g, c,
                            (xPos + col * TETRIMINO_SIZE.width),
                            (yPos + row * TETRIMINO_SIZE.height));
                }
            }
        }
    }

    private int calcYHold(char c) {
        if (c != 'I') {
            return 47;
        } else {
            return 60 - TETRIMINO_SIZE.height;
        }
    }

    private void drawScore(Graphics g) {
        initFont(g);
        if (isInterval(score, 0, 10)) {
            g.drawString(getString(score), 62, SCORE_YPOS);
        }
        if (isInterval(score, 10, 100)) {
            g.drawString(getString(score), 52, SCORE_YPOS);
        }
        if (isInterval(score, 100, 1000)) {
            g.drawString(getString(score), 41, SCORE_YPOS);
        }
        if (isInterval(score, 1000, 10000)) {
            g.drawString(getString(score), 32, SCORE_YPOS);
        }
        if (score >= 10000) {
            g.drawString(getString(score), 24, SCORE_YPOS);
        }
    }

    private void drawLevel(Graphics g) {
        initFont(g);
        if (isInterval(level, 0, 10)) {
            g.drawString(getString(level), 62, LEVEL_YPOS);
        }
        if (isInterval(level, 10, 100)) {
            g.drawString(getString(level), 52, LEVEL_YPOS);
        }
    }

    private void drawLines(Graphics g) {
        initFont(g);
        if (isInterval(lines, 0, 10)) {
            g.drawString(getString(lines), 62, LINES_YPOS);
        }
        if (isInterval(lines, 9, 100)) {
            g.drawString(getString(lines), 52, LINES_YPOS);
        }
        if (lines >= 100) {
            g.drawString(getString(lines), 41, LINES_YPOS);
        }
    }

    private void initFont(Graphics g) {
        g.setFont(Constants.getFont());
        g.setColor(Constants.getFontColor());
    }

    private boolean isInterval(int n, int min, int max) {
        return n >= min && n < max;
    }

    private String getString(int n) {
        return String.valueOf(n);
    }

    public void updateNextTetrimino(ArrayList<ITetrimino> next) {
        this.next = next;
        repaint();
    }

    public void updateHold(ITetrimino hold) {
        this.hold = hold;
        repaint();
    }

    public void updateScore(int score) {
        this.score = score;
        repaint();
    }

    public void updateLevel(int level) {
        this.level = level;
        repaint();
    }

    public void updateLines(int lines) {
        this.lines = lines;
        repaint();
    }
}