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
    static boolean gameOver = false;

    // Start of game
    public static void main(String[] args) {

        newGame();
    }

    // Restarting game
    public static void newGame() {

        GamePlay.resetTurns();
        AI.resetAI();

        BattleShipFrame bsf = new BattleShipFrame();
        bsf.loadBattleshipFrame();
        ConsoleOutput.resetConsole();
    }

}
