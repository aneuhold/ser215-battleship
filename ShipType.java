
public enum ShipType {

	// Ship (Size, Health)
	BATTLESHIP (4, 4),
	CRUISER (3,3),
	SUBMARINE (3,3),
	DESTROYER (2,2),
	CARRIER (5,5);

int size;
int health;

// private constructor for enum ShipType
// use ShipPiece constructor if wanting to create a ship object
private ShipType (int size, int health) {
	 this.size = size;
	 this.health = health;
	}

} 
