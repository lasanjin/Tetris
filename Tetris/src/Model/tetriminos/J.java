package Model.tetriminos;

public class J implements ITetrimino {
    private int x, y;
    private char[][] J = {
            {'J', ' ', ' '},
            {'J', 'J', 'J'},
            {' ', ' ', ' '}
    };

    public J(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        J = newShape;
    }

    public char[][] getShape() {
        return J;
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
        return 'J';
    }
}
