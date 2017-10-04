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

public class PlaceShips {

    GameBoardArray temp = new GameBoardArray();
    int row;
    int column;
    int shipPlacedCounter;

    // AI Ship Construtor
    public PlaceShips(GameBoardArray board) {
        temp = board;
    }

    // Player Ship Constructor - variable is where they clicked
    public PlaceShips(int[] clickedLocation) {
        column = clickedLocation[0];
        row = clickedLocation[1];
        shipPlacedCounter = 0;
    }

    // hardcoded method for user ship placement for testing game turns
    // need to remove once user ship placement is used
    public void PlayerShipPlacing() {

    }

    public void placeUserShips(GameBoardArray board, String ship, String orientation) {
        ShipPiece playerBattleship = new ShipPiece(ShipType.BATTLESHIP);
        ShipPiece playerCruiser = new ShipPiece(ShipType.CRUISER);
        ShipPiece playerSubmarine = new ShipPiece(ShipType.SUBMARINE);
        ShipPiece playerDestroyer = new ShipPiece(ShipType.DESTROYER);
        ShipPiece playerCarrier = new ShipPiece(ShipType.CARRIER);

        //HORIZONTAL PLACEMENT OF SHIPS
        //placement ships
        // carrier = 1, battleship = 2, cruiser = 3, sub = 4, destroyer = 5
        if (orientation.equals("Horz")) {
            switch (ship) {
                case "":
                    break;

                case "carrier":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 1) {
                                board.array[j][k] = 0;
                            }
                        }
                    }

                    if ((row < 7) && (checkForOtherShipsBeforePlacingHorz(board, playerCarrier) == false)) {
                        if (row < 10 - 4) {
                            for (int i = 0; i < playerCarrier.getSize(); i++) {
                                board.array[column][row + i] = 1;
                            }
                        }
                    }
                    break;

                case "battleship":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 2) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((row < 10) && (checkForOtherShipsBeforePlacingHorz(board, playerBattleship) == false)) {
                        if (row < 10 - 3) {
                            for (int i = 0; i < playerBattleship.getSize(); i++) {
                                board.array[column][row + i] = 2;
                            }
                        }
                    }
                    break;

                case "cruiser":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 3) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((row < 9) && (checkForOtherShipsBeforePlacingHorz(board, playerCruiser) == false)) {
                        if (row < 10 - 2) {
                            for (int i = 0; i < playerCruiser.getSize(); i++) {
                                board.array[column][row + i] = 3;
                            }
                        }
                    }
                    break;

                case "submarine":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 4) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((row < 9) && (checkForOtherShipsBeforePlacingHorz(board, playerSubmarine) == false)) {
                        if (row < 10 - 2) {
                            for (int i = 0; i < playerSubmarine.getSize(); i++) {
                                board.array[column][row + i] = 4;
                            }
                        }
                    }
                    break;

                case "destroyer":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 5) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((row < 10) && (checkForOtherShipsBeforePlacingHorz(board, playerDestroyer) == false)) {
                        if (row < 10 - 1) {
                            for (int i = 0; i < playerDestroyer.getSize(); i++) {
                                board.array[column][row + i] = 5;
                            }
                        }
                    }
                    break;

            }
        }

        //Vertical PLACEMENT OF SHIPS
        //placement ships
        // carrier = 1, battleship = 2, cruiser = 3, sub = 4, destroyer = 5
        if (orientation.equals("Vert")) {
            switch (ship) {
                case "":
                    break;

                case "carrier":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 1) {
                                board.array[j][k] = 0;
                            }
                        }
                    }

                    if ((column < 7) && (checkForOtherShipsBeforePlacingVert(board, playerCarrier) == false)) {
                        if (column < 10 - 4) {
                            for (int i = 0; i < playerCarrier.getSize(); i++) {
                                board.array[column + i][row] = 1;
                            }
                        }
                    }
                    break;

                case "battleship":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 2) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((column < 10) && (checkForOtherShipsBeforePlacingVert(board, playerBattleship) == false)) {
                        if (column < 10 - 3) {
                            for (int i = 0; i < playerBattleship.getSize(); i++) {
                                board.array[column + i][row] = 2;
                            }
                        }
                    }
                    break;

                case "cruiser":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 3) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((column < 9) && (checkForOtherShipsBeforePlacingVert(board, playerCruiser) == false)) {
                        if (column < 10 - 2) {
                            for (int i = 0; i < playerCruiser.getSize(); i++) {
                                board.array[column + i][row] = 3;
                            }
                        }
                    }
                    break;

                case "submarine":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 4) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((column < 9) && (checkForOtherShipsBeforePlacingVert(board, playerSubmarine) == false)) {
                        if (column < 10 - 2) {
                            for (int i = 0; i < playerSubmarine.getSize(); i++) {
                                board.array[column + i][row] = 4;
                            }
                        }
                    }
                    break;

                case "destroyer":
                    for (int j = 0; j < 10; j++) {
                        for (int k = 0; k < 10; k++) {
                            if (board.array[j][k] == 5) {
                                board.array[j][k] = 0;
                            }
                        }
                    }
                    if ((column < 10) && (checkForOtherShipsBeforePlacingVert(board, playerDestroyer) == false)) {
                        if (column < 10 - 1) {
                            for (int i = 0; i < playerDestroyer.getSize(); i++) {
                                board.array[column + i][row] = 5;
                            }
                        }
                    }
                    break;

            }
        }
    }

    // checks for ships in horizonatal orientation before placing
    private boolean checkForOtherShipsBeforePlacingHorz(GameBoardArray board, ShipPiece ship) {
        boolean shipPresent = false;
        for (int i = 0; i < ship.getSize(); i++) {
            if (row < 10 - ship.getSize()) {
                if (board.array[column][row + i] > 0) {
                    return shipPresent = true;
                }
            }
        }
        return false;

    }

    // checks for ships in horizonatal orientation before placing
    private boolean checkForOtherShipsBeforePlacingVert(GameBoardArray board, ShipPiece ship) {
        boolean shipPresent = false;
        for (int i = 0; i < ship.getSize(); i++) {
            if (column < 10 - ship.getSize()) {
                if (board.array[column + i][row] > 0) {
                    return shipPresent = true;
                }
            }
        }
        return false;

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

            clear = clearPath(board.array, shipSize, orientation, chooseColumn, chooseRow);
        } while (!clear);

        if (orientation.equals("Horz")) {   // Horizontal placement
            for (int itr = 0; itr < shipSize; itr++) {
                board.array[chooseColumn][chooseRow + itr] = shipNum + itr;
            }
        } else if (orientation.equals("Vert")) {    // vertical implementation
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
                    StatusOutput.display(errMsg);
                    //      printTo(console, errMsg);
                }
            }
        } else if (orientation.equals("Vert")) {
            for (int itr = 0; itr < shipSize; itr++) {
                try {
                    total += onBoard[chooseColumn + itr][chooseRow];
                } catch (ArrayIndexOutOfBoundsException e) {
                    StatusOutput.display(errMsg);
                    //     printTo(console, errMsg);
                    total += 9;
                }
            }
        }
        ans = total <= 0;
        return ans;
    }

}
