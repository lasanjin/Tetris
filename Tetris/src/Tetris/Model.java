package Tetris;

import Sound.IControlSound;
import Tetriminos.ITetrimino;
import View.IControlView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The model. Tetris logic, gameplay
 */
public class Model implements IControlModel {
    private Dimension TETRIMINO_SIZE = Constants.getTetriminoSize();
    private Dimension BOARD_SIZE = Constants.getBoardSize();
    private char[][] board;
    private IControlSound controlSound;
    private IControlView controlView;
    private IGhost ghost;
    private ITetrimino active;
    private ITetrimino rotated;
    private ITetrimino hold;
    private RandomFactory factory;
    private ArrayList<ITetrimino> next;
    private TimerTask scoreTask;
    private int totalScore;
    private int score;
    private int lines;
    private int LEVEL;
    private boolean hardDrop;
    private boolean softDrop;
    private boolean lockdown;
    private boolean isHold;
    private boolean isRunning;


    public Model() {
        Dimension BOARD_SCALE = Constants.getBoardScale();
        board = new char
                [BOARD_SCALE.height]
                [BOARD_SCALE.width];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
        factory = new RandomFactory();
        active = factory.getNextTetrimino();
        active.updatePosition(0, -TETRIMINO_SIZE.height);

        next = new ArrayList<>(3);
        next.add(factory.getNextTetrimino());
        next.add(factory.getNextTetrimino());
        next.add(factory.getNextTetrimino());

        rotated = TetriminoFactory.createTetrimino(active.getChar());

        ghost = new Ghost();
        ghost.updateGhost(active);
        ghost.updateBoard(board);

        lockdown = true;
        isHold = true;
        totalScore = 0;
        score = 0;
        lines = 0;
        LEVEL = 1;

        if (scoreTask != null) {
            scoreTask.cancel();
        }
        scoreTimer();
        isRunning = true;
        (new Timer()).scheduleAtFixedRate(scoreTask, 1, 5);
    }

    private void scoreTimer() {
        scoreTask = new TimerTask() {
            @Override
            public void run() {
                if (isRunning && score > 0) {
                    score--;
                    controlView.updateScore(totalScore++);
                }
            }
        };
    }

    @Override
    public void stopScoreTask() {
        isRunning = false;
    }

    @Override
    public void updateModel() throws DropException, NextLevelException, GameOverException {
        updateYPosition(TETRIMINO_SIZE.height);
        if (isGameOver()) {
            throw new GameOverException();
        }
        nextLevel();
        updateView();
    }

    private void updateYPosition(int dy) throws DropException, NextLevelException {
        if (checkCollision(active, 0, dy)) {
            active.updatePosition(0, dy);
        } else {
            updateCollision();
            nextTetrimino();
            isFilled();
        }
        calcMoveScore();
    }

    private void calcMoveScore() {
        if (softDrop) {
            score++;
        }
        if (hardDrop) {
            score += 2;
        }
    }

    private void updateXPosition(int dx) {
        if (checkCollision(active, dx, 0)) {
            active.updatePosition(dx, 0);
            controlView.updateTetrimino(active);
            ghost.updateGhost(active);
            ghost.placeGhost();
            controlSound.playSound("move");
        }
    }

    @Override
    public void actOnKeys(int key) throws DropException {
        switch (key) {
            case KeyEvent.VK_LEFT:
                updateXPosition(-TETRIMINO_SIZE.width);
                break;
            case KeyEvent.VK_RIGHT:
                updateXPosition(TETRIMINO_SIZE.width);
                break;
            case KeyEvent.VK_UP:
                rotate(false);
                break;
            case KeyEvent.VK_X:
                rotate(false);
                break;
            case KeyEvent.VK_C:
                updateHoldTetrimino();
                break;
            case KeyEvent.VK_Z:
                rotate(true);
                break;
            default:
        }
    }

    @Override
    public void addScore(boolean softDrop) {
        this.softDrop = softDrop;
    }

    @Override
    public void actOnKeys(boolean hardDrop) {
        this.hardDrop = hardDrop;
        isHold = false;
    }

    private void updateHoldTetrimino() {
        if (isHold) {
            playHoldSound();
            isHold = false;
            if (hold != null) {
                ITetrimino temp = active;
                active = TetriminoFactory.createTetrimino(hold.getChar());
                controlView.updateTetrimino(active);
                setNewTetriminoPosition();
                ghost.updateGhost(active);
                ghost.placeGhost();
                hold = TetriminoFactory.createTetrimino(temp.getChar());
                controlView.updateHold(hold);
            } else {
                hold = TetriminoFactory.createTetrimino(active.getChar());
                nextTetrimino();
                controlView.updateHold(hold);
            }
        }
    }

    private void playHoldSound() {
        controlSound.stopFXSound();
        controlSound.playSound("hold");
    }

    private void setNewTetriminoPosition() {
        char c = active.getChar();
        int yPos = -2 * TETRIMINO_SIZE.height;
        if (c == 'O') {
            active.setPosition(4 * TETRIMINO_SIZE.width, yPos);
        } else {
            active.setPosition(3 * TETRIMINO_SIZE.width, yPos);
        }
    }

    private boolean checkCollision(ITetrimino tetrimino, int dx, int dy) {
        char[][] shape = tetrimino.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                char c = shape[row][col];
                if (checkChar(c)) {
                    int newXPos = (tetrimino.getX() + dx) / TETRIMINO_SIZE.width + col;
                    int newYPos = (tetrimino.getY() + dy) / TETRIMINO_SIZE.height + row;
                    if (newXPos < 0) {
                        return false;
                    }
                    if (newXPos >= BOARD_SIZE.width / TETRIMINO_SIZE.width) {
                        return false;
                    }
                    if (newYPos >= BOARD_SIZE.height / TETRIMINO_SIZE.height) {
                        return false;
                    }
                    try {
                        char c_ = board[newYPos][newXPos];
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

    private boolean checkRotateCollision(char[][] shape) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                char c = shape[row][col];
                if (checkChar(c)) {
                    int xPos = rotated.getX() / TETRIMINO_SIZE.width + col;
                    int yPos = rotated.getY() / TETRIMINO_SIZE.height + row;
                    if (xPos < 0) {
                        return false;
                    }
                    if (xPos >= BOARD_SIZE.width / TETRIMINO_SIZE.width) {
                        return false;
                    }
                    try {
                        char c_ = board[yPos][xPos];
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

    private void updateCollision() throws DropException {
        char[][] tetrimino = active.getShape();
        int xPos = active.getX() / TETRIMINO_SIZE.width;
        int yPos = active.getY() / TETRIMINO_SIZE.height;
        for (int row = 0; row < tetrimino.length; row++) {
            for (int col = 0; col < tetrimino[row].length; col++) {
                char c = tetrimino[row][col];
                if (checkChar(c)) {
                    try {
                        board[yPos + row][xPos + col] = tetrimino[row][col];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.getCause();
                    }
                }
            }
        }
        controlView.updateBoard(board);
        ghost.updateBoard(board);
        collisionSound();
        isHold = true;
    }

    private void collisionSound() throws DropException {
        if (hardDrop) {
            controlSound.playSound("harddrop");
            hardDrop = false;
            lockdown = false;
            throw new DropException();
        }
        if (lockdown) {
            controlSound.playSound("lockdown");
        }
        lockdown = true;
    }

    private boolean isGameOver() {
        for (int col = 0; col < board[0].length; col++) {
            boolean isFilled = true;
            for (int row = 0; row < board.length; row++) {
                char c = board[row][col];
                if (!checkChar(c)) {
                    isFilled = false;
                }
                if (isFilled) {
                    return true;
                }
            }
        }
        return false;
    }

    private void nextTetrimino() {
        active = next.get(0);
        next.remove(0);
        next.add(factory.getNextTetrimino());
        controlView.updateTetrimino(active);
        ghost.updateGhost(active);
        ghost.placeGhost();
    }

    private void isFilled() throws NextLevelException {
        int lines = 0;
        for (int row = 0; row < board.length; row++) {
            boolean isFilled = true;
            for (int col = 0; col < board[row].length; col++) {
                char c = board[row][col];
                if (!checkChar(c)) {
                    isFilled = false;
                }
            }
            if (isFilled) {
                lines++;
                clearColumnSound(lines);
                clearColumn(row);
                adjustBoard(row);
            }
        }
        isFilledSound(lines);
    }

    private void clearColumnSound(int lines) {
        if (lines <= 3) {
            controlSound.stopFXSound();
        }
        if (lines == 1) {
            controlSound.playSound("clearline");
        }
        if (lines == 2) {
            controlSound.playSound("doubleclear");
        }
        if (lines == 3) {
            controlSound.playSound("tripleclear");
        }
    }

    private void clearColumn(int row) {
        for (int col = 0; col < board[row].length; col++) {
            try {
                board[row][col] = ' ';
                controlView.updateBoard(board);
                Thread.sleep(30);
            } catch (Exception e) {
                e.getCause();
            }
        }
        lines++;
        controlView.updateLines(lines);
    }

    private void adjustBoard(int clearedRow) {
        for (int row = clearedRow - 1; row >= 0; row--) {
            for (int col = 0; col < board[row].length; col++) {
                board[row + 1][col] = board[row][col];
                board[row][col] = ' ';
            }
        }
        controlView.updateBoard(board);
    }

    private void isFilledSound(int lines) {
        if (lines == 1) {
            calcScore(1);
        }
        if (lines == 2) {
            if (LEVEL <= 3) {
                controlSound.playSound("verygood");
            } else if (LEVEL > 3 && LEVEL <= 6) {
                controlSound.playSound("excellent");
            } else if (LEVEL > 6) {
                controlSound.playSound("brilliant");
            }
            calcScore(2);
        }
        if (lines == 3) {
            if (LEVEL <= 3) {
                controlSound.playSound("wonderful");
            } else if (LEVEL > 3 && LEVEL <= 6) {
                controlSound.playSound("fantastic");
            } else if (LEVEL > 6) {
                controlSound.playSound("wow");
            }
            calcScore(3);
        }
        if (lines == 4) {
            controlSound.playSound("amazing");
            calcScore(4);
        }
    }

    private void calcScore(int lines) {
        if (lines == 1) {
            score += 10;
            score += 100 * LEVEL;
        } else if (lines == 2) {
            score += 30;
            score += 300 * LEVEL;
        } else if (lines == 3) {
            score += 50;
            score += 500 * LEVEL;
        } else if (lines == 4) {
            score += 80;
            score += 800 * LEVEL;
        }
    }

    private void nextLevel() throws NextLevelException {
        if (LEVEL == 1 && lines >= 20) {
            LEVEL = 2;
            controlView.updateLevel(2);
            throw new NextLevelException(300);
        } else if (LEVEL == 2 && lines >= 30) {
            controlView.updateLevel(3);
            LEVEL = 3;
            throw new NextLevelException(250);
        } else if (LEVEL == 3 && lines >= 40) {
            controlView.updateLevel(4);
            LEVEL = 4;
            throw new NextLevelException(225);
        } else if (LEVEL == 4 && lines >= 50) {
            controlView.updateLevel(5);
            LEVEL = 5;
            throw new NextLevelException(200);
        } else if (LEVEL == 5 && lines >= 60) {
            controlView.updateLevel(6);
            LEVEL = 6;
            throw new NextLevelException(175);
        } else if (LEVEL == 6 && lines >= 70) {
            controlView.updateLevel(7);
            LEVEL = 7;
            throw new NextLevelException(150);
        } else if (LEVEL == 7 && lines >= 80) {
            controlView.updateLevel(8);
            LEVEL = 8;
            throw new NextLevelException(125);
        } else if (LEVEL == 8 && lines >= 90) {
            controlView.updateLevel(9);
            LEVEL = 9;
            throw new NextLevelException(100);
        } else if (LEVEL == 9 && lines >= 100) {
            controlView.updateLevel(10);
            LEVEL = 10;
            throw new NextLevelException(75);
        }
    }

    private void updateView() {
        controlView.updateTetrimino(active);
        controlView.updateNextTetrimino(next);
        controlView.updateBoard(board);
        ghost.placeGhost();
    }

    private void rotate(boolean left) {
        setShapeAndPosition(rotated, active);
        char[][] rotatedCopy = copyShape(rotated.getShape());
        if (active.getChar() == 'I') {
            left = !left;
        }
        if (left) {
            rotatedCopy = transpose(mirror(rotatedCopy));
        } else {
            rotatedCopy = mirror(transpose(rotatedCopy));
        }
        rotated.setShape(rotatedCopy);
        if (checkRotateCollision(rotatedCopy)) {
            rotateSound(true);
            updateRotation();
        } else {
            rotateSound(false);
            //calcTwist(left);
        }
    }

    /**
     * Some basic Tetris Battle rules.
     *
     * @param left
     */
    private void calcTwist(boolean left) {
        int move = TETRIMINO_SIZE.width;
        //HÖGER
        if (checkCollision(rotated, move, 0)) {
            rotated.updatePosition(move, 0);
            updateRotation();
        }
        //VÄNSTER
        else if (checkCollision(rotated, -move, 0)) {
            rotated.updatePosition(-move, 0);
            updateRotation();
        }
        //NER
        else if (checkCollision(rotated, 0, move)) {
            rotated.updatePosition(0, move);
            updateRotation();
        }
        //HÖGER NER
        else if (checkCollision(rotated, move, move)) {
            rotated.updatePosition(move, move);
            updateRotation();
        }
        //VÄNSTER NER
        else if (checkCollision(rotated, -move, move)) {
            rotated.updatePosition(-move, move);
            updateRotation();
        }
        //HÖGER 2xNER
        else if (checkCollision(rotated, move, 2 * move)) {
            rotated.updatePosition(move, 2 * move);
            updateRotation();
        }
        //VÄNSTER 2xNER
        else if (checkCollision(rotated, -move, 2 * move)) {
            rotated.updatePosition(-move, 2 * move);
            updateRotation();
        } else {
            rotateSound(false);
        }
        rotateSound(true);
    }

    private void updateRotation() {
        setShapeAndPosition(active, rotated);
        controlView.updateTetrimino(active);
        ghost.updateGhost(active);
        ghost.placeGhost();
    }

    private void rotateSound(boolean rotate) {
        controlSound.stopFXSound();
        if (rotate) {
            if (active.getChar() != 'O') {
                controlSound.playSound("rotate");
            }
        } else {
            controlSound.playSound("rotatefail");
        }
    }

    /**
     * Transpose mxm matrix
     * @param tetrimino
     * @return
     */
    private char[][] transpose(char[][] tetrimino) {
        char[][] transposed = tetrimino;
        for (int row = 0; row < transposed.length; row++) {
            for (int col = row + 1; col < transposed[0].length; col++) {
                char save = transposed[row][col];
                transposed[row][col] = transposed[col][row];
                transposed[col][row] = save;
            }
        }
        return transposed;
    }

    /**
     * Mirror mxm matrix
     * @param tetrimino
     * @return
     */
    private char[][] mirror(char[][] tetrimino) {
        char[][] rotated = tetrimino;
        for (int row = 0; row < rotated.length / 2; row++) {
            for (int col = 0; col < rotated[0].length; col++) {
                char temp = rotated[col][row];
                rotated[col][row] = rotated[col][rotated.length - 1 - row];
                rotated[col][rotated.length - 1 - row] = temp;
            }
        }
        return rotated;
    }

    private char[][] copyShape(char[][] tetrimino) {
        char[][] copy = new char[tetrimino.length][];
        for (int col = 0; col < tetrimino.length; col++) {
            copy[col] = tetrimino[col].clone();
        }
        return copy;
    }

    private void setShapeAndPosition(ITetrimino copy, ITetrimino copied) {
        copy.setShape(copied.getShape());
        copy.setPosition(copied.getX(), copied.getY());
    }

    private boolean checkChar(char c) {
        return c != ' ';
    }

    public void addIControlView(IControlView view) {
        this.controlView = view;
        ghost.addIControlView(view);
    }

    public void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }
}
