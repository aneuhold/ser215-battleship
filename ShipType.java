
public enum ShipType {


    // Ship (Size, Health)
    BATTLESHIP (4, 4,"Battleship"),
    CRUISER (3,3,"Cruiser"),
    SUBMARINE (3,3,"Submarine"),
    DESTROYER (2,2,"Destroyer"),
    CARRIER (5,5,"Carrier");

    int size;
    int health;
    String name = "";

    // private constructor for enum ShipType
// use ShipPiece constructor if wanting to create a ship object
    private ShipType (int size, int health, String name) {
        this.size = size;
        this.health = health;
        this.name = name;
    }

}
