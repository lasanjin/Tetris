package model.tetriminos;

public class L implements ITetrimino {
    private int x, y;
    private char[][] L = {
            {' ', ' ', 'L'},
            {'L', 'L', 'L'},
            {' ', ' ', ' '}
    };

    public L(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        L = newShape;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char[][] getShape() {
        return L;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getChar() {
        return 'L';
    }
}
