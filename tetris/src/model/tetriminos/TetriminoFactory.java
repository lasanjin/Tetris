package model.tetriminos;

import utils.Constants;

import java.awt.*;

/**
 * Factory class. Generating model.tetriminos.
 */
public abstract class TetriminoFactory {
    private final static Dimension TETRIMINO_SIZE = Constants.getTetriminoSize();

    private static O createO(int x, int y) {
        return new O(x, y);
    }

    private static J createJ(int x, int y) {
        return new J(x, y);
    }

    private static L createL(int x, int y) {
        return new L(x, y);
    }

    private static Z createZ(int x, int y) {
        return new Z(x, y);
    }

    private static S createS(int x, int y) {
        return new S(x, y);
    }

    private static I createI(int x, int y) {
        return new I(x, y);
    }

    private static T createT(int x, int y) {
        return new T(x, y);
    }

    public static G createGhost(int x, int y) {
        return new G(x, y);
    }

    public static ITetrimino createTetrimino(int i) {
        int xPos = 3 * TETRIMINO_SIZE.width;
        int yPos = -2 * TETRIMINO_SIZE.height;
        switch (i) {
            case 0:
                return createO(4 * TETRIMINO_SIZE.width, yPos);
            case 1:
                return createL(xPos, yPos);
            case 2:
                return createZ(xPos, yPos);
            case 3:
                return createJ(xPos, yPos);
            case 4:
                return createI(xPos, yPos);
            case 5:
                return createS(xPos, yPos);
            case 6:
                return createT(xPos, yPos);
        }
        return null;
    }

    public static ITetrimino createTetrimino(char c) {
        switch (c) {
            case 'O':
                return new O(0, 0);
            case 'J':
                return new J(0, 0);
            case 'L':
                return new L(0, 0);
            case 'Z':
                return new Z(0, 0);
            case 'S':
                return new S(0, 0);
            case 'I':
                return new I(0, 0);
            case 'T':
                return new T(0, 0);
        }
        return null;
    }
}
