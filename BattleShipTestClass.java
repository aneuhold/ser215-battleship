
public class BattleShipTestClass {

  BattleShipFrame bsf = new BattleShipFrame();

  // Start of game
  public static void main(String[] args) {
    newGame();
  }

  // Restarting game
  public static void newGame() {

    BattleShipFrame bsf = new BattleShipFrame();
    bsf.loadBattleshipFrame();
  }

}