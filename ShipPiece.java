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
public class ShipPiece {

    private ShipType shipType;
    private int health;
    private int size;
    private String orientation;

    /**
     * ******************************************************
     *
     * USE THIS CALL TO CREATE A SHIP: |name|	|shipType| ShipPiece battleship =
     * new ShipPiece(ShipType.BATTLESHIP);
     *
     ********************************************************
     */
    //Constructor for ships. Uses enums from ShipTypes for values
    public ShipPiece(ShipType shipType) {
        this.shipType = shipType;
        this.health = shipType.health;
        this.size = shipType.size;
        this.orientation = shipType.orientation;

    }

    // returns true or false for ship being sunk
    public boolean shipSunk() {
        if (getHealth() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // returns ships current health
    public int getHealth() {
        return health;
    }

    // returns ships game board size from 100% health
    public int getSize() {
        return size;
    }

    // marks ship as hit and subtracts a health point
    public void onHit() {
        health = getHealth() - 1;
    }

    public void changeOrientation() {
        if (orientation.equals("Horz")) {
            orientation = "Vert";
        } else if (orientation.equals("Horz")) {
            orientation = "Vert";
        }
    }

}
