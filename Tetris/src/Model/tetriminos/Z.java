package Model.tetriminos;

public class Z implements ITetrimino {
    private int x, y;
    private char[][] Z = {
            {'Z', 'Z', ' '},
            {' ', 'Z', 'Z'},
            {' ', ' ', ' '}
    };

    public Z(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        Z = newShape;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char[][] getShape() {
        return Z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getChar() {
        return 'Z';
    }

}
