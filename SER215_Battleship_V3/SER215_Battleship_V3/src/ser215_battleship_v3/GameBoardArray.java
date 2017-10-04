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
public class GameBoardArray {

    //this is the class that creates the logical game board for game functions
    int[][] array;

    public GameBoardArray() {
        array = new int[10][10];
    }

    public void updateOpponentArrayAfterShipPlacement(int[][] updatedArray) {
        array = updatedArray;
    }
}
