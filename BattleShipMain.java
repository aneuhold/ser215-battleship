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
public class BattleShipMain {

    BattleShipFrame bsf = new BattleShipFrame();

    // Start of game
    public static void main(String[] args) {

        boolean gameOver = false;

        newGame();

        // while (gameOver = false) {
        // }
    }

    // Restarting game
    public static void newGame() {

        BattleShipFrame bsf = new BattleShipFrame();
        bsf.loadBattleshipFrame();
    }

}
