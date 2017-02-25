package Tetriminos;

public class O implements ITetrimino {
    private int x, y;
    private char[][] O = {
            {'O', 'O'},
            {'O', 'O'},
    };

    public O(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        O= newShape;
    }

    public char[][] getShape() {
        return O;
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
        return 'O';
    }

}
