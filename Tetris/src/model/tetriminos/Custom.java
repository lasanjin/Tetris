package model.tetriminos;

public class Custom implements ITetrimino {
    private int x, y;
    private char[][] custom = {
            {' ',' ',' ',' ',' ',' ','L', 'L', ' ', ' '},
            {' ',' ',' ',' ',' ',' ','L', ' ', ' ', ' '},
            {' ',' ',' ',' ',' ',' ','L', ' ', 'L', 'L'},
            {' ',' ',' ',' ',' ',' ','L', ' ', ' ', 'L'},
            {' ',' ','L','L','L','L','L', ' ', 'L', 'L'},
            {' ',' ','L','L','L','L','L', ' ', ' ', 'L'},
            {' ',' ',' ',' ','L','L','L', 'L', 'L', 'L'},
            {'L','L','L',' ','L','L','L', 'L', 'L', 'L'},
            {'L','L','L',' ','L','L','L', 'L', 'L', 'L'},
            {'L','L','L',' ',':','L','L', 'L', 'L', 'L'}
    };

    public Custom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void setShape(char[][] newShape) {
        custom = newShape;
    }

    public char[][] getShape() {
        return custom;
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
