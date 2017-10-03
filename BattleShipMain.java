
public class BattleShipMain {

    BattleShipFrame bsf = new BattleShipFrame();
    static boolean gameOver = false;
    // Start of game
    public static void main(String[] args) {

        newGame();
    }

    // Restarting game
    public static void newGame() {

        GamePlay.resetTurns();
        AI.resetAI();

        BattleShipFrame bsf = new BattleShipFrame();
        bsf.loadBattleshipFrame();
    }

}
