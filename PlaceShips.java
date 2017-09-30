
import java.util.Random;

public class PlaceShips {

    GameBoardArray temp = new GameBoardArray();
    boolean DEBUG = true;

    public PlaceShips(GameBoardArray board) {
        temp = board;
    }

    // hardcoded method for user ship placement for testing game turns
    // need to remove once user ship placement is used
    public void PlaeyerShipPlacing() {
        ShipPiece aiBattleship = new ShipPiece(ShipType.BATTLESHIP);
        ShipPiece aiCruiser = new ShipPiece(ShipType.CRUISER);
        ShipPiece aiSubmarine = new ShipPiece(ShipType.SUBMARINE);
        ShipPiece aiDestroyer = new ShipPiece(ShipType.DESTROYER);
        ShipPiece aiCarrier = new ShipPiece(ShipType.CARRIER);

        temp.array[1][2] = 4;

        temp.updateOpponentArrayAfterShipPlacement(temp.array);

    }

    // calls helper method after ships objects are created
    public void opponentShipPlacing() {
        ShipPiece aiBattleship = new ShipPiece(ShipType.BATTLESHIP);
        ShipPiece aiCruiser = new ShipPiece(ShipType.CRUISER);
        ShipPiece aiSubmarine = new ShipPiece(ShipType.SUBMARINE);
        ShipPiece aiDestroyer = new ShipPiece(ShipType.DESTROYER);
        ShipPiece aiCarrier = new ShipPiece(ShipType.CARRIER);

        placeOpponentShips(temp, aiBattleship.getSize(), 1);
        placeOpponentShips(temp, aiCruiser.getSize(), 2);
        placeOpponentShips(temp, aiSubmarine.getSize(), 3);
        placeOpponentShips(temp, aiDestroyer.getSize(), 4);
        placeOpponentShips(temp, aiCarrier.getSize(), 5);

    }

    // helper method for opponentShipPlacing
    private void placeOpponentShips(GameBoardArray board, int shipSize, int shipNum) {

        if (DEBUG) {
            System.out.println("Looking to place a ship of size" + shipSize);
        }
        shipNum = 10 * shipNum;
        boolean clear;
        int chooseRow;
        int chooseColumn;
        String orientation;
        String[] choice = {"Vert", "Horz"};
        do {
            Random gen = new Random();
            chooseColumn = gen.nextInt(10 - shipSize) + 1;
            chooseRow = gen.nextInt(10 - shipSize) + 1;
            orientation = choice[gen.nextInt(choice.length)];
            // Horizontal placement
            clear = clearPath(board.array, shipSize, orientation, chooseColumn, chooseRow);
            if (DEBUG) {
                String msg = String.format("check for ships at pos: [%s, %s], orientation %s, test was %s",
                        chooseColumn, chooseRow, orientation, clear);
                System.out.println(msg);
            }
        } while (!clear);

        if (orientation.equals("Horz")) {
            for (int itr = 0; itr < shipSize; itr++) {
                // tens place Carrier for now
                board.array[chooseColumn][chooseRow + itr] = shipNum + itr;
            }
        } else if (orientation.equals("Vert")) {
            // vertical implementation
            for (int itr = 0; itr < shipSize; itr++) {
                board.array[chooseColumn + itr][chooseRow] = shipNum + itr;
            }
        }
        board.updateOpponentArrayAfterShipPlacement(board.array);
    }

    private boolean clearPath(int[][] onBoard, int shipSize, String orientation, int chooseColumn, int chooseRow) {
        boolean ans = false;
        String errMsg = "Ship too long for that position\n";
        int total = 0;
        if (orientation.equals("Horz")) {
            for (int itr = 0; itr < shipSize; itr++) {
                try {
                    total += onBoard[chooseColumn][chooseRow + itr];
                } catch (ArrayIndexOutOfBoundsException e) {
                    //      printTo(console, errMsg);
                }
            }
        } else if (orientation.equals("Vert")) {
            for (int itr = 0; itr < shipSize; itr++) {
                try {
                    total += onBoard[chooseColumn + itr][chooseRow];
                } catch (ArrayIndexOutOfBoundsException e) {
                    //     printTo(console, errMsg);
                    total += 9;
                }
            }
        }
        ans = total <= 0;
        return ans;
    }

}
