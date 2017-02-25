package Tetriminos;

public class S implements ITetrimino {
    private int x, y;
    private char[][] S = {
            {' ', 'S', 'S'},
            {'S', 'S', ' '},
            {' ', ' ', ' '}
    };

    public S(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        S = newShape;
    }

    public char[][] getShape() {
        return S;
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
        return 'S';
    }

}
