
public class GameBoardArray {

    //this is the class that creates the logical game board for game functions
    int[][] array;

    public GameBoardArray() { array = new int[10][10]; }

    public void updateOpponentArrayAfterShipPlacement(int[][] updatedArray) {
        array = updatedArray;
    }
}
