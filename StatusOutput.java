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
 * StatusOutput.java [ ser215-group-project-jfijewsk-patch-2 ] by Ryan Migaud   < Rmigaud@asu.edu
 * > on: 9/30/2017 at: 1:18 PM Prints the current Status to the user.
 */
public class StatusOutput extends JTextArea {

    static JTextArea status = new JTextArea("status\n");

    StatusOutput(String msg) {
        StatusOutput.display(msg);//+"\n");
    }

    /**
     * update(msg) appends a message and forces the focus to the bottom of the
     * JTextArea
     */
    public static void display(String msg) { // could maybe set to private
        status.setText("");
        status.append(msg);
        status.setCaretPosition(status.getDocument().getLength());
    }
}
