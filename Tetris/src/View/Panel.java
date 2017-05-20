package View;

import Model.tetriminos.ITetrimino;
import Utils.Constants;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class paints the right panel (score etc.)
 */
public class Panel extends Board {
    private int Y_POS = TETRIMINO_SIZE.height;
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
        if (c == 'I') {
            if (i == 0) {
                return 0;
            }
            if (i == 1) {
                return 3 * Y_POS;
            }
            if (i == 2) {
                return 6 * Y_POS;
            }
        } else {
            if (i == 0) {
                return Y_POS / 2;
            }
            if (i == 1) {
                return (3 * Y_POS) + Y_POS / 2;
            }
            if (i == 2) {
                return (6 * Y_POS) + Y_POS / 2;
            }
        }
        return 0;
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
        g.setFont(new Font("Courier New", Font.PLAIN, 32));
        g.setColor(new Color(201, 201, 201));
        if (score >= 0 && score < 10) {
            g.drawString(String.valueOf(score), 62, SCORE_YPOS);
        }
        if (score >= 10 && score < 100) {
            g.drawString(String.valueOf(score), 52, SCORE_YPOS);
        }
        if (score >= 100 && score < 1000) {
            g.drawString(String.valueOf(score), 41, SCORE_YPOS);
        }
        if (score >= 1000 && score < 10000) {
            g.drawString(String.valueOf(score), 32, SCORE_YPOS);
        }
        if (score >= 10000) {
            g.drawString(String.valueOf(score), 24, SCORE_YPOS);
        }
    }

    private void drawLevel(Graphics g) {
        g.setFont(new Font("Courier New", Font.PLAIN, 32));
        g.setColor(new Color(201, 201, 201));
        if (level >= 0 && level < 10) {
            g.drawString(String.valueOf(level), 62, LEVEL_YPOS);
        }
        if (level >= 10 && level < 100) {
            g.drawString(String.valueOf(level), 52, LEVEL_YPOS);
        }
    }

    private void drawLines(Graphics g) {
        g.setFont(new Font("Courier New", Font.PLAIN, 32));
        g.setColor(new Color(201, 201, 201));
        if (lines >= 0 && lines < 10) {
            g.drawString(String.valueOf(lines), 62, LINES_YPOS);
        }
        if (lines > 9 && lines < 100) {
            g.drawString(String.valueOf(lines), 52, LINES_YPOS);
        }
        if (lines >= 100) {
            g.drawString(String.valueOf(lines), 41, LINES_YPOS);
        }
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