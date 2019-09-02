package model.tetriminos;

public class G implements ITetrimino {
    private int x, y;
    private char[][] G = {
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
    };

    public G(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        G = newShape;
    }

    public char[][] getShape() {
        return G;
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
        return 'G';
    }
}
