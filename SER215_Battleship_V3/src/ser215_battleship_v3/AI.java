/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser215_battleship_v3;

import java.util.Random;

public class AI {

    private Random gen = new Random();
    private static int lastShotRow;
    private static int lastShotCol;
    private int pickedCol;
    private int pickedRow;
    private boolean hasShot = false;
    private String difficulty = "Hard";
    public int totalAIHits = 0;
    public int shotsTaken = 0;

    /* AI's turn to shoot*/
    public void pickAsquare(GameBoardArray board) {

        pickedRow = gen.nextInt(board.array.length);
        pickedCol = gen.nextInt(board.array.length);
        hasShot = false;
        boolean lastShotHit = false;
        String difficulty = "Hard";

        while (!hasShot) {
            // make the AI harder
            if (lastShotHit && difficulty.equals("Hard")) {
                pickedCol = lastShotCol - gen.nextInt(2);
                if (pickedCol == lastShotCol) {
                    pickedRow = lastShotRow - gen.nextInt(2);
                }/*else {pickedRow = lastShotRow;}*/
            }
            // if the AI hit a player ship
            if (board.array[pickedCol][pickedRow] > 0) {
                ConsoleOutput.display(String.format("AI fired on %s%s and hit!",
                        BattleShipFrame.asChar(pickedCol), pickedRow + 1));
                board.array[pickedCol][pickedRow] = -1;
                lastShotHit = true;
                totalAIHits++;
                hasShot = true;
            }
            // if the AI missed a players ship
            if (board.array[pickedCol][pickedRow] == 0) {
                ConsoleOutput.display(String.format("AI fired on %s%s and missed!",
                        BattleShipFrame.asChar(pickedCol), pickedRow + 1));
                board.array[pickedCol][pickedRow] = -2;
                lastShotHit = false;
                hasShot = true;
            }
            //if the AI shot the same spot before
            if (board.array[pickedCol][pickedRow] < 0) {
                pickedRow = gen.nextInt(board.array.length);
                pickedCol = gen.nextInt(board.array.length);
            }
        }
        shotsTaken++;
        lastShotCol = pickedCol;
        lastShotRow = pickedRow;
        if (totalAIHits == 17) {
            ConsoleOutput.display("You've been defeated! "
                    + "the computer took " + shotsTaken + " shots with an accuracy of " + (totalAIHits / shotsTaken));
        }
    }

    public static String getLastOpponentShot() {
        return String.format("%s%s", lastShotRow, lastShotCol);
    }

    public static void resetAI() {
        Random gen = new Random();
        int lastShotRow = 0;
        int lastShotCol = 0;
        int pickedCol = 0;
        int pickedRow = 0;
        boolean hasShot = false;
        String difficulty = "Hard";
        int totalAIHits = 0;
        int shotsTaken = 0;

    }
}
