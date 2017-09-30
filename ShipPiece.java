
public class ShipPiece {

    private ShipType shipType;
    private int health;
    private int size;

    /********************************************************

     USE THIS CALL TO CREATE A SHIP:
     |name|							  |shipType|
     ShipPiece battleship = new ShipPiece(ShipType.BATTLESHIP);

     *********************************************************/

    //Constructor for ships. Uses enums from ShipTypes for values
    public ShipPiece (ShipType shipType) {
        this.shipType = shipType;
        this.health = shipType.health;
        this.size = shipType.size;

    }

    // returns true or false for ship being sunk
    public boolean shipSunk() {
        if (getHealth() == 0) {
            return true;
        }
        else
            return false;
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
        health = getHealth()-1;
    }

}
	
	
