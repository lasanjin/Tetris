package Tetris;

import Tetriminos.ITetrimino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates a shuffled ArrayList with all seven tetriminos.
 */
public class RandomFactory {
    private List<ITetrimino> bag;

    public RandomFactory() {
        bag = new ArrayList<>(7);
        fillBag();
    }

    private void fillBag() {
        for (int i = 0; i < 7; i++) {
            bag.add(TetriminoFactory.createTetrimino(i));
        }
        Collections.shuffle(bag);
    }

    public ITetrimino getNextTetrimino() {
        ITetrimino next;
        if (bag.size() == 0) {
            fillBag();
        }
        next = bag.get(0);
        bag.remove(0);
        return next;
    }

}
