import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/* http://docs.oracle.com/javase/tutorial/uiswing/components/button.html#abstractbutton*/
        /* Using  a [10 x 10] grid size and ships with dimensions:
        num     shipType        size
        1	    Carrier         5
        2	    Battleship	    4
        3	    Cruiser	        3
        4	    Submarine	    3
        5	    Destroyer	    2
         */
public class Battleship {
/** Version one of an example battleship grid*/
    private Battleship() {
        // grid image file paths
        String white = "WHITE.png";
        String red = "RED.png";
        String green = "GREEN.png";

        // default frame size
        final int FRAME_WIDTH = 1024, FRAME_HEIGHT = 768;

        // grids are [10x10] cells hold 0-2 values representing 0=white, 1=green, 2=red

        int[][] gameBoard = new int[10][10];
        int[][] opponentBoard = new int[10][10];
        int[][] playerBoard = new int[10][10];

        /* Using the Wikipedia model:
        num     shipType        size
        1	    Carrier         5
        2	    Battleship	    4
        3	    Cruiser	        3
        4	    Submarine	    3
        5	    Destroyer	    2
         */

        // Board piece placement

        for (int eachRow=0; eachRow < gameBoard[0].length;eachRow++ ) {
            for (int eachCol = 0; eachCol < gameBoard[0].length; eachCol++) {
                //gameBoard[eachCol][eachRow] = 0;
                opponentBoard[ eachCol][eachRow] = eachRow * eachCol % 3;
                playerBoard[ eachCol][eachRow] = eachRow % 5 +1;
            }
        }
        // Build GUI

        int bannerHeight = 100;
        int leftGrid = 200;

        int val = 30; // Button size
        int rightGrid = leftGrid + 350;

        int width=val, height=val, increase = val, column=0, row=0;
        JFrame frame = new JFrame("Battleship!");
        // Logo
        JLabel label = new JLabel( "Battleship!");
        //label.setBounds(512,15,100,15);
        label.setLocation(512, 15);
        frame.add( label);
        frame.pack();

        // New Game
        JButton newGame = new JButton("New Game");
        newGame.setBounds(50,50,100,30);

        frame.add(newGame);

        //Status
        JTextArea status = new JTextArea("status ",3, 100);
        frame.setLayout(null);
        status.setBounds(leftGrid,600,400,50);

        frame.add(status);
        frame.pack();

        // left grid title
        JLabel leftTitle = new JLabel("targetGrid");
        leftTitle.setBounds(leftGrid - 15,bannerHeight - 15,80,15);
        frame.add(leftTitle);
        frame.pack();

        // right grid title
        JLabel rightTitle = new JLabel("OceanGrid");
        leftTitle.setBounds(rightGrid - 15,bannerHeight - 15,80,15);
        frame.add(rightTitle);
        frame.pack();

        // Left Grid
        for( column = 0; column < 10; column++) {
            for ( row = 0; row < 10; row++) {
                //determine color:
                String imgPath = white;
                if (gameBoard[column][row] == 0){  // color logic
                    imgPath = white; // square has not been interacted with
                } else if (gameBoard[column][row] == 1){
                    imgPath = green; // square was a hit!
                } else if (gameBoard[column][row] == 2) {
                    imgPath = red; // square has a miss!
                }

                String arrayPOS = String.format("%s%s",column,row);
                String boardPOS = String.format("%s%s",asChar(row),column + 1);

                JButton square = new JButton(boardPOS);
                square.setBackground(Color.WHITE);
                // dynamically swap colors of our button icon
                try {
                    Image img = ImageIO.read(getClass().getResource(imgPath));
                    square.setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                square.setBounds(leftGrid +column*increase, bannerHeight+row*increase,
                        width, height);
                square.setToolTipText(boardPOS);

                int thisColumn = column;
                int thisRow = row;
                //String thisColor = color;
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonPressed();
                    }

                    private void buttonPressed() {
                        // on button press reveal opponent map
                        if(opponentBoard[thisColumn][thisRow] == 0) {
                            gameBoard[thisColumn][thisRow] = 0;
                            square.setBackground(Color.WHITE);
                            square.setEnabled(false); // disable square

                            String out = boardPOS+" was a miss!\n";
                            System.out.print(out);
                            status.insert(out,0);

                            try { // This Will allow us to swap images
                                Image img = ImageIO.read(getClass().getResource(white));
                                square.setIcon(new ImageIcon(img));
                            } catch(Exception ex) {ex.printStackTrace();}

                        } else if (opponentBoard[thisColumn][thisRow] == 1) {
                            gameBoard[thisColumn][thisRow] = 1;
                            square.setBackground(Color.GREEN);

                            String out = boardPOS+" on opponent board was holding 1 "+
                                    "lets turn it green!\n";
                            System.out.print(out);
                            status.insert(out,0);

                            try {// This Will allow us to swap images
                                Image img = ImageIO.read(getClass().getResource(green));
                                square.setIcon(new ImageIcon(img));

                            } catch(IOException ex) {ex.printStackTrace();}

                        } else if (opponentBoard[thisColumn][thisRow] == 2) {
                            gameBoard[thisColumn][thisRow] = 2;
                            square.setBackground(Color.RED);

                            String out = boardPOS+" on opponent board was holding 2 "+
                                    "lets turn it red!\n";
                            System.out.print(out);
                            status.insert(out,0);

                            try {// This Will allow us to swap images
                                Image img = ImageIO.read(getClass().getResource(red));
                                square.setIcon(new ImageIcon(img));
                                square.setBackground(Color.RED);
                            } catch(IOException ex) {ex.printStackTrace();}
                        }

                        //square.setEnabled(false); // cheap way to block input
                        /*
                        String out = arrayPOS +" was the array value selected," +
                                " which corresponds to "+ boardPOS+" on the board\n";

                        System.out.print(out);
                        status.insert(out,0);*/
                    }
                });
                // Adding button onto the frame
                frame.add(square);
            }
        }

        // Right Grid
        /* Using  a [10 x 10] grid size and ships with dimensions:
        num     shipType        size
        1	    Carrier         5
        2	    Battleship	    4
        3	    Cruiser	        3
        4	    Submarine	    3
        5	    Destroyer	    2
         */
        for( column = 0; column < 10; column++) {
            for ( row = 0; row < 10; row++) {

                String imgPath = white;
                String orientation = /*"Vert";*/ "Horz";
                String shipType = "";
                int size = 1;

                if (playerBoard[column][row] == 0){  // color logic
                    //imgPath = white; // square has not been interacted with
                } else if (playerBoard[column][row] == 1){
                    shipType = "Carrier";
                    size = 5;
                } else if (playerBoard[column][row] == 2) {
                    shipType = "Battleship";
                    size = 4;
                } else if (playerBoard[column][row] == 3) {
                    shipType = "Cruiser";
                    size = 3;
                } else if (playerBoard[column][row] == 4) {
                    shipType = "Submarine";
                    size = 3;
                } else if (playerBoard[column][row] == 5) {
                    shipType = "Destroyer";
                    size = 2;
                }

                if (playerBoard[column][row] == 0) { imgPath = white;}
                else {

                    // for vertical
                    if (orientation.equals("Vert")) {
                        for (int i = 1; i <= size; i++) {
                            imgPath = "/img/"+orientation+ shipType +1+".png";
                        }
                        // for Horizontal
                    } else if (orientation.equals("Horz")) {
                        for (int i = 1; i <= size; i++) {
                            imgPath = "/img/"+orientation+ shipType + i +".png";
                        }
                    }
                }

                String arrayPOS = String.format("%s%s",column,row);
                String boardPOS = String.format("%s%s",asChar(row),column + 1);

                JButton square = new JButton(boardPOS);
                square.setBackground(Color.WHITE);
                // dynamically swap colors of our button icon

                try {
                    Image img = ImageIO.read(getClass().getResource(imgPath));
                    square.setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                square.setBounds(rightGrid +column*increase, bannerHeight+row*increase,
                        width, height);
                square.setToolTipText(boardPOS);

                int thisColumn = column;
                int thisRow = row;
                String thisImgPath = imgPath;

                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonPressed();
                    }

                    private void buttonPressed() {
                        // on button press reveal opponent map
                        int currentShip = 1;
                        int shipSize = 5;
                        if(playerBoard[thisColumn][thisRow] == 0) {

                            square.setBackground(Color.WHITE);
                            //square.setEnabled(false); // disable square
                            String out = boardPOS+" was a miss!\n";
                            System.out.print(out);
                            status.insert(out,0);

                            try { // This Will allow us to swap images
                                Image img = ImageIO.read(getClass().getResource(thisImgPath));
                                square.setIcon(new ImageIcon(img));
                            } catch(Exception ex) {ex.printStackTrace();}

                        } else if (playerBoard[thisColumn][thisRow] == 1) {
                            gameBoard[thisColumn][thisRow] = 1;
                            String out = boardPOS+" on opponent board was holding 1 "+
                                    "lets turn it green!\n";
                            System.out.print(out);
                            status.insert(out,0);

                            try {// This Will allow us to swap images
                                Image img = ImageIO.read(getClass().getResource(green));
                                square.setIcon(new ImageIcon(img));

                            } catch(IOException ex) {ex.printStackTrace();}

                        } else if (playerBoard[thisColumn][thisRow] == 2) {

                            try {// This Will allow us to swap images
                                Image img = ImageIO.read(getClass().getResource(red));
                                square.setIcon(new ImageIcon(img));
                                square.setBackground(Color.RED);
                            } catch(IOException ex) {ex.printStackTrace();}
                        }

                        //square.setEnabled(false); // cheap way to block input
                        /*
                        String out = arrayPOS +" was the array value selected," +
                                " which corresponds to "+ boardPOS+" on the board\n";

                        System.out.print(out);
                        status.insert(out,0);*/
                    }
                });
                // Adding button onto the frame
                frame.add(square);
            }
        }
        // Setting frame size. this is the window size.
        frame.setSize(1024, 768);
        frame.setLayout(null); // null forces absolute positioning.
        frame.setVisible( true );
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void newGame(ActionEvent actionEvent) {
        System.out.println("We should probably do something");
    }

    // converts row num to String value
    private String asChar(int val) {
        String ans = "";
        switch (val) {
            case 0: ans = "A"; break;
            case 1: ans = "B"; break;
            case 2: ans = "C"; break;
            case 3: ans = "D"; break;
            case 4: ans = "E"; break;
            case 5: ans = "F"; break;
            case 6: ans = "G"; break;
            case 7: ans = "H"; break;
            case 8: ans = "I"; break;
            case 9: ans = "J"; break;
        } return ans;
    }

    public static void main(String[] args) {
        new Battleship();

        //UIManager.put("ButtonUI", "javax.swing.plaf.basic.BasicButtonUI");
    }
}
