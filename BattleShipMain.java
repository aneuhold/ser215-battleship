
public class BattleShipMain {

    BattleShipFrame bsf = new BattleShipFrame();

    // Start of game
    public static void main(String[] args) {

        boolean gameOver = false;

        newGame();

        // while (gameOver = false) {
        // }
    }

    // Restarting game
    public static void newGame() {

        BattleShipFrame bsf = new BattleShipFrame();
        bsf.loadBattleshipFrame();
    }

}
