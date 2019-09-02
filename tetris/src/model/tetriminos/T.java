package model.tetriminos;

public class T implements ITetrimino {
    private int x, y;
    private char[][] T = {
            {' ', 'T', ' '},
            {'T', 'T', 'T'},
            {' ', ' ', ' '}
    };

    public T(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        T = newShape;
    }

    public char[][] getShape() {
        return T;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getChar() {
        return 'T';
    }

}
