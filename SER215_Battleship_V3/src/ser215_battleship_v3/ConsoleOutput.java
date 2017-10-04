/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ser215_battleship_v3;

/**
 *
 * @author Tim
 */
import javax.swing.JTextArea;

/**
 * ConsoleOutput.java [ ser215-group-project-jfijewsk-patch-2 ] by Ryan Migaud   < Rmigaud@asu.edu
 * > on: 9/30/2017 at: 2:07 PM Prints the current Status to the user.
 */
public class ConsoleOutput {

    static String msg = "Choose a ship to place on the left,\nWhen all ships are placed press Start Game.\n";
    static JTextArea console = new JTextArea(msg);

    ConsoleOutput(String msg) {
        ConsoleOutput.display(msg + "\n");
    }

    /**
     * update(msg) appends a message and forces the focus to the bottom of the
     * JTextArea
     */
    public static void display(String msg) { // could maybe set to private

        console.append(msg + "\n");
        console.setCaretPosition(console.getDocument().getLength());
    }
}
