import javax.swing.JTextArea;

/** ConsoleOutput.java    [ ser215-group-project-jfijewsk-patch-2 ]
 * by Ryan Migaud   < Rmigaud@asu.edu >  on: 9/30/2017   at: 2:07 PM
 * Prints the current Status to the user.*/
public class ConsoleOutput {
    static JTextArea console = new JTextArea("console\n");
    ConsoleOutput(String msg){
        ConsoleOutput.display(msg+"\n");
    }

    /** update(msg)
     *  appends a message and forces the focus to the bottom of the JTextArea*/
    public static void display( String msg){ // could maybe set to private

        console.append(msg);
        console.setCaretPosition(console.getDocument().getLength());
    }

}
