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
public enum ShipType {

    // Ship (Size, Health)
    BATTLESHIP(4, 4, "Battleship", "Horz"),
    CRUISER(3, 3, "Cruiser", "Horz"),
    SUBMARINE(3, 3, "Submarine", "Horz"),
    DESTROYER(2, 2, "Destroyer", "Horz"),
    CARRIER(5, 5, "Carrier", "Horz");

    int size;
    int health;
    String name = "";
    String orientation;

    // private constructor for enum ShipType
// use ShipPiece constructor if wanting to create a ship object
    private ShipType(int size, int health, String name, String orientation) {
        this.size = size;
        this.health = health;
        this.name = name;
        this.orientation = orientation;
    }

}
