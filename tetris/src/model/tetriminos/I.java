package model.tetriminos;

public class I implements ITetrimino {
    private int x, y;
    private char[][] I = {
            {' ', ' ', ' ', ' '},
            {'I', 'I', 'I', 'I'},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '}
    };

    public I(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        I = newShape;
    }

    public char[][] getShape() {
        return I;
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
        return 'I';
    }
}
