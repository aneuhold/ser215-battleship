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
import java.awt.Color;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class GameBoardArray {

    //this is the class that creates the logical game board for game functions
    int[][] array;

    public GameBoardArray() {
        int width = 10;
        int height = 10;

        array = new int[width][height];

    }

    public boolean isShipPresent(int row, int column) {
        if ((array[row][column] > 0) && (array[row][column]) < 11) {
            return true;
        } else {
            return false;
        }
    }

    public void updateOpponentArrayAfterShipPlacement(int[][] updatedArray) {
        array = updatedArray;
    }

    public void updateGrid() {

    }

}
