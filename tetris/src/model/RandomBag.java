package model;

import model.tetriminos.ITetrimino;
import model.tetriminos.TetriminoFactory;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class generates a shuffled List<ITetrimino> with all seven tetriminos.
 */
public class RandomBag extends ArrayList<ITetrimino> {

    public RandomBag() {
        fillBag();
    }

    private void fillBag() {
        for (int i = 0; i < 7; i++) {
            this.add(TetriminoFactory.createTetrimino(i));
        }
        Collections.shuffle(this);
    }

    public ITetrimino getNextTetrimino() {
        ITetrimino next;
        if (this.size() == 0) {
            fillBag();
        }
        next = this.get(0);
        this.remove(0);
        return next;
    }
}