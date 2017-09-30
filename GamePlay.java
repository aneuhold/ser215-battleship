/**
 * @author James
 */
public class GamePlay {

    static String gameStatus;

    public GamePlay() {
        gameStatus = "players turn";
    }

    public void takingTurns() {
        //    if (gameStatus == 2) {
    }

    public static void switchTurns(GamePlay turnOrder) {
        if (turnOrder.gameStatus.equals("players turn")) {
            turnOrder.gameStatus = "Opponents turn";
        }
        if (gameStatus.equals("Opponents turn")) {
            gameStatus = "players turn";
        }
    }

    public String getTurnOrder() {
        return gameStatus;
    }
}
