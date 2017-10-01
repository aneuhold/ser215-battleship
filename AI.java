import java.util.Random;

/****************************************************************************************
 // AI.java    [ ser215-group-project-jfijewsk-patch-2 ]
 // by Ryan Migaud   < Rmigaud@asu.edu >  on: 9/30/2017   at: 2:33 PM
 //
 //**************************************************************************************/

public class AI {
    //-----------------------------------------------------------------------------------
    // AI 
    //----------------------------------------------------------------------------------/
    public static int[][] firedUpon = new int[10][10];
    public static Random gen = new Random();
    public static int lastShotRow;
    public static int lastShotCol;

    AI() { AI.init(); }
    /* init()   initializes the firstUpon map to zero*/
    public static void init() {
        for (int i = 0; i < firedUpon.length; i++) {
            for (int j = 0; j < firedUpon[1].length; j++) {
                firedUpon[i][j] = 0;
            }
        }
    }

    /* AI's turn to shoot*/
    public void pickAsquare(){
        int pickedRow = gen.nextInt(firedUpon.length);
        int pickedCol= gen.nextInt(firedUpon.length);

        if(firedUpon[pickedCol][pickedRow] == 0){
            ConsoleOutput.display(String.format("AI fired on %s%s",pickedCol,pickedRow));
            //GamePlay.switchTurns();
            firedUpon[pickedCol][pickedRow] = 1; // indicate we've fired on this square

        } else {pickAsquare();} // pick a different square
    }

    public static String getLastOpponentShot() {
        return String.format("%s%s",lastShotRow, lastShotCol);
    }
}
