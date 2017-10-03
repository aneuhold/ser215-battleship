/**
 * @author James
 */
public class GamePlay {

    static String gameStatus;
    static int turnsTaken = 1;

    public GamePlay() {
        gameStatus = "players turn";
    }

    public static void switchTurns() {
        if (gameStatus.equals("players turn")) {
            gameStatus = "Opponents turn";
        }
        if (gameStatus.equals("Opponents turn")) {
            gameStatus = "players turn";
        }
        turnsTaken++;
        ConsoleOutput.display(getTurnOrder());
    }
    public static void resetTurns() {GamePlay.turnsTaken = 1;}


    public static String getTurnOrder() {
        return String.format("Move %s ",turnsTaken)+ gameStatus;
    }
}
