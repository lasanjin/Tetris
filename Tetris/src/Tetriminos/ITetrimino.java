package Tetriminos;

/**
 * Tetrimino Interface
 */
public interface ITetrimino {
    void updatePosition(int dx, int dy);
    void setPosition(int x, int y);
    char[][] getShape();
    void setShape(char[][] newShape);
    int getX();
    int getY();
    char getChar();
}
