/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser215_battleship_v3;

import java.util.Random;

/**
 *
 * @author Tim
 */
public class GamePlay {

    static String gameStatus;
    static int turnsTaken = 1;

    public GamePlay() {
        gameStatus = "players turn";
    }

    public static void switchTurns() {
        if (gameStatus.equals("players turn")) {
            gameStatus = "Opponents turn";
        }
        if (gameStatus.equals("Opponents turn")) {
            gameStatus = "players turn";
        }
        turnsTaken++;
        //ConsoleOutput.display(getTurnOrder());
    }

    public static void resetTurns() {
        GamePlay.turnsTaken = 1;
    }

    //public static String getTurnOrder() {
    //return String.format("Move %s ", turnsTaken) + gameStatus;
    //}
}
