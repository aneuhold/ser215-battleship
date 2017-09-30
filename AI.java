/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser215_battleship_v3;

/**
 *
 * @author Tim
 */
import java.util.Random;

public class AI {

    private final boolean[][] guessed;
    private final Random RNG;
    private int shipRow;
    private int shipCol;

    public AI() {
        this(10, 10);
    }

    public AI(int boardHeight, int boardWidth) {
        this.guessed = new boolean[boardHeight + 1][boardWidth + 1];
        this.RNG = new Random();
    }

    public Coordinate getShipPlacement() {
        this.shipRow = RNG.nextInt(10) + 1;
        this.shipCol = RNG.nextInt(10) + 1;

        return new Coordinate(this.shipRow, this.shipCol);
    }

    public char getShipOrientation() {
        return (RNG.nextInt(2) == 0) ? 'h' : 'v';
    }

    public Coordinate fire() {
        int row = RNG.nextInt(10) + 1;
        int col = RNG.nextInt(10) + 1;

        while (guessed[row][col]) {
            row = RNG.nextInt(10) + 1;
            col = RNG.nextInt(10) + 1;
        }

        guessed[row][col] = true; // dont shoot there again
        return new Coordinate(row, col);
    }
}
