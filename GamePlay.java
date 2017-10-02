
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

    public static void switchTurns() {
        if (gameStatus.equals("players turn")) {
            gameStatus = "Opponents turn";
        }
        if (gameStatus.equals("Opponents turn")) {
            gameStatus = "players turn";
        }
    }

    public String getTurnOrder() {
        return gameStatus;
    }
}
