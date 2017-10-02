
import java.util.Random;

public class AI {

    //-----------------------------------------------------------------------------------
    // AI
    //----------------------------------------------------------------------------------/
    private Random gen = new Random();
    private static int lastShotRow;
    private static int lastShotCol;
    private int pickedCol;
    private int pickedRow;
    private boolean hasShot = false;


    /* AI's turn to shoot*/
    public void pickAsquare(GameBoardArray board) {
        pickedRow = gen.nextInt(board.array.length);
        pickedCol = gen.nextInt(board.array.length);
        hasShot = false;

        while (hasShot == false) {
            // if the AI hit a player ship
            if (board.array[pickedCol][pickedRow] > 0) {
                ConsoleOutput.display(String.format("AI fired on %s%s and hit!", pickedCol, pickedRow));
                board.array[pickedCol][pickedRow] = -1;
                hasShot = true;
            }
            // if the AI missed a players ship
            if (board.array[pickedCol][pickedRow] == 0) {
                ConsoleOutput.display(String.format("AI fired on %s%s and missed!", pickedCol, pickedRow));
                board.array[pickedCol][pickedRow] = -2;
                hasShot = true;
            }
            //if the AI shot the same spot before
            if (board.array[pickedCol][pickedRow] < 0) {
                pickedRow = gen.nextInt(board.array.length);
                pickedCol = gen.nextInt(board.array.length);
            }

        }
    }

    public static String getLastOpponentShot() {
        return String.format("%s%s", lastShotRow, lastShotCol);
    }
}
