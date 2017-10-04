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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BattleShipFrame extends JFrame {

    private final String BATTLESHIP_LOGO_FILE = "Battleship-Logo.png";
    private final int FRAME_HEIGHT = 550;
    private final int FRAME_WIDTH = 1000;

    private JPanel logoPanel;
    private JPanel gameOptionsPanel;
    private JPanel gameStatusPanel;

    private JPanel centerPanel;
    private JPanel gridsPanel;
    private JPanel playerGridPanel;
    private JPanel opponentGridPanel;
    private JPanel gameReadoutPanel;
    private JPanel shipGridPanel;

    private JLabel battleShipLogoLabel;
    private GamePlay turnOrder = new GamePlay();
    private JButton playerButtonArray[][] = new JButton[10][10];
    private String selectedShip = "";
    private String selectedOrientation = "Horz";
    private GameBoardArray playerBoard = new GameBoardArray();
    private boolean gameHasStarted = false;
    private AI ai = new AI();
    private int userHits = 0;
    private JFrame frame;

    /**
     * Builds all components for the Battleship frame and makes it visible.
     */
    public void loadBattleshipFrame() {

        // Logo Panel
        loadBattleShipLogo();
        this.add(logoPanel, BorderLayout.NORTH);

        // Game Status
        loadGameStatus();
        //this.add(gameStatusPanel, BorderLayout.EAST);
        // Game Options
        loadGameOptions();
        this.add(gameOptionsPanel, BorderLayout.WEST);

        // Center panel to hold grids and readout
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));
        this.add(centerPanel, BorderLayout.CENTER);
        gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 5, 0));
        centerPanel.add(gridsPanel);

        // Player Grid Panel
        updatePlayerGUI(playerBoard);
        gridsPanel.add(playerGridPanel);

        // Opponent Grid Panel
        GameBoardArray opponentBoard = new GameBoardArray();
        PlaceShips ships = new PlaceShips(opponentBoard);
        ships.opponentShipPlacing();
        updateAiGUI(opponentBoard);
        gridsPanel.add(opponentGridPanel);

        // Game Readout
        loadGameReadout();
        centerPanel.add(gameReadoutPanel);

        // Frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
    }

    /**
     * Loads the logo from the built-in battleship logo file path.
     */
    public void loadBattleShipLogo() {
        logoPanel = new JPanel();
        BufferedImage battleShipImg = null;
        try {
            battleShipImg = ImageIO.read(getClass().getResource(BATTLESHIP_LOGO_FILE));
            battleShipLogoLabel = new JLabel(new ImageIcon(battleShipImg));
            logoPanel.add(battleShipLogoLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(logoPanel, BorderLayout.NORTH);
    }

    public void loadGameStatus() {
        gameStatusPanel = new JPanel();
        gameStatusPanel.add(new JLabel("Game Status"));
        gameStatusPanel.setBorder(new TitledBorder(new EtchedBorder()));
        gameStatusPanel.add(StatusOutput.status);
    }

    public void updateGameStatus(GamePlay turnOrder) {
        JLabel gameStatus = new JLabel();
        if (turnOrder.gameStatus.equals("Opponents turn")) {
            //gameStatus.setText("players turn");Thread.sleep(5000)
            ConsoleOutput.display("It's the Opponent's turn,\n");
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("We tried to sleep and failed..");
            }
            ConsoleOutput.display("they have fired on location "
                    + AI.getLastOpponentShot() + ".");
        }
        if (turnOrder.gameStatus.equals("players turn")) {
            gameStatus.setText("opponenets turn");
            ConsoleOutput.display("\n");
        }
        gameStatusPanel.add(gameStatus);
    }

    public void loadGameOptions() {
        int buttonWidth = 100;
        int buttonHeight = 20;
        gameOptionsPanel = new JPanel();
        gameOptionsPanel.setLayout(new BorderLayout());

        //orientation button
        JButton orientation = new JButton("<html>"
                + "Change<br>Orientation"
                + "</html>");
        orientation.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        orientation.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        orientation.addActionListener(e -> {
            if (selectedOrientation.equals("Horz")) {
                selectedOrientation = "Vert";
            } else if (selectedOrientation.equals("Vert")) {
                selectedOrientation = "Horz";
            }

        });

        //ship buttons
        //carrier button
        JButton carrier = new JButton("Carrier");
        carrier.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        carrier.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        carrier.addActionListener(e -> {
            selectedShip = "carrier";
            ConsoleOutput.display("The Carrier has size of " + ShipType.CARRIER.size);

        });

        //battleship button
        JButton battleship = new JButton("BattleShip");
        battleship.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        battleship.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        battleship.addActionListener(e -> {
            selectedShip = "battleship";
            ConsoleOutput.display("The BattleShip has size of " + ShipType.BATTLESHIP.size);
        });

        // cruiser button
        JButton cruiser = new JButton("Cruiser");
        cruiser.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        cruiser.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        cruiser.addActionListener(e -> {
            selectedShip = "cruiser";
            ConsoleOutput.display("Cruiser ship has size of " + ShipType.CRUISER.size);
        });

        // submarine button
        JButton submarine = new JButton("Submarine");
        submarine.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        submarine.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        submarine.addActionListener(e -> {
            selectedShip = "submarine";
            ConsoleOutput.display("The Submarine has size of " + ShipType.SUBMARINE.size);
        });

        // destroyer button
        JButton destroyer = new JButton("Destroyer");
        destroyer.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        destroyer.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        destroyer.addActionListener(e -> {
            selectedShip = "destroyer";
            ConsoleOutput.display("The Destroyer has size of " + ShipType.DESTROYER.size);
        });

        shipGridPanel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        shipGridPanel.setLayout(layout);

        shipGridPanel.add(orientation);
        shipGridPanel.add(carrier);
        shipGridPanel.add(battleship);
        shipGridPanel.add(cruiser);
        shipGridPanel.add(submarine);
        shipGridPanel.add(destroyer);

        gameOptionsPanel.add(shipGridPanel, BorderLayout.CENTER);

        // New Game
        JButton newGame = new JButton("New Game");
        newGame.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        newGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        newGame.addActionListener(e -> {
            // newGame Actions:
            dispose();
            BattleShipMain.newGame();
        });
        gameOptionsPanel.add(newGame, BorderLayout.BEFORE_FIRST_LINE);

        // Start Game Button and removes buttons after game has started
        JButton startGame = new JButton("Start Game");
        Dimension btnSize = new Dimension(buttonWidth, buttonHeight);
        startGame.setPreferredSize(btnSize);
        startGame.setMaximumSize(btnSize);

        startGame.addActionListener(e -> {
            boolean placedCarrier = false;
            boolean placedBattleship = false;
            boolean placedCruiser = false;
            boolean placedSubmarine = false;
            boolean placedDestroyer = false;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (playerBoard.array[i][j] == 1) {
                        placedCarrier = true;
                    }
                    if (playerBoard.array[i][j] == 2) {
                        placedBattleship = true;
                    }
                    if (playerBoard.array[i][j] == 3) {
                        placedCruiser = true;
                    }
                    if (playerBoard.array[i][j] == 4) {
                        placedSubmarine = true;
                    }
                    if (playerBoard.array[i][j] == 5) {
                        placedDestroyer = true;
                    }
                }
            }

            if ((placedCarrier) && (placedBattleship) && (placedCruiser) && (placedSubmarine) && (placedDestroyer)) {
                gameOptionsPanel.remove(shipGridPanel);
                gameOptionsPanel.repaint();
                selectedShip = "";
                gameHasStarted = true;
                ConsoleOutput.display("\nAn Enemy Fleet has been spotted!\n"
                        + "Choose a position to attack on the right.\n");
            }
        });
        gameOptionsPanel.add(startGame, BorderLayout.SOUTH);
        gameOptionsPanel.setBorder(new TitledBorder(new EtchedBorder()));
    }

    public void loadGameReadout() {
        gameReadoutPanel = new JPanel();
        // Game Readout code
        gameReadoutPanel.add(new JLabel("Game Readout"));
        JScrollPane consolePane = new JScrollPane(ConsoleOutput.console);
        consolePane.setPreferredSize(new Dimension(450, 200));
        gameReadoutPanel.add(consolePane);
        gameReadoutPanel.setBorder(new TitledBorder(new EtchedBorder()));
    }

    /**
     * Updates the Oponnent's GUI after an event
     */
    public void updateAiGUI(GameBoardArray board) {
        opponentGridPanel = new JPanel();
        GridLayout layout = new GridLayout(0, 10);
        int squareSize = 30;
        opponentGridPanel.setLayout(layout);

        // Opponent Grid code
        int column, row;
        for (column = 0; column < 10; column++) {
            for (row = 0; row < 10; row++) {
                String boardPOS = String.format("%s%s", asChar(column), row + 1);
                JButton square = new JButton(boardPOS);
                square.setPreferredSize(new Dimension(squareSize, squareSize));
                square.setMaximumSize(new Dimension(squareSize, squareSize));

                int thisColumn = column;
                int thisRow = row;

                /* int[] userHits single int stored as a final array in order to be
                 accessed by our lambda variable action listener.  'e ->'  */
                square.addActionListener(e -> {
                    // square Actions:

                    if (GamePlay.gameStatus.equals("players turn") && (gameHasStarted == true)) {
                        String pos = "You've selected " + boardPOS + ".";
                        String msg = "";
                        if (board.array[thisColumn][thisRow] == 0) {
                            ConsoleOutput.display(String.format("%s was a miss!", boardPOS));
                            square.setBackground(Color.GRAY);
                            square.setEnabled(false);
                        } else if (board.array[thisColumn][thisRow] > 0) {
                            ConsoleOutput.display(String.format("%s was a hit!", boardPOS));
                            square.setBackground(Color.RED);
                            userHits++;
                            square.setEnabled(false);
                        }
                        updateGameStatus(turnOrder);
                        updateAiGUI(board);
                        ai.pickAsquare(playerBoard);
                        changeButtonColor(playerBoard);
                        //Tested by winning the game
                        if (userHits == 17) {
                            JOptionPane.showMessageDialog(frame,
                                    "You Win!!! Hit New Game to keep playing or "
                                    + "close the program!!!",
                                    "",
                                    JOptionPane.PLAIN_MESSAGE);
                        } else {
                            turnOrder.switchTurns();
                        }
                    }
                    //printTo(console, msg);
                });
                opponentGridPanel.add(square);
            }
        }
    }

    // call this to update player GUI after event
    public void updatePlayerGUI(GameBoardArray board) {

        playerGridPanel = new JPanel();
        GridLayout layout = new GridLayout(0, 10);
        int squareSize = 30;
        playerGridPanel.setLayout(layout);

        // Player Grid code
        int column, row;
        for (column = 0; column < 10; column++) {
            for (row = 0; row < 10; row++) {

                String boardPOS = String.format("%s%s", asChar(column), row + 1);
                playerButtonArray[column][row] = new JButton(boardPOS);
                playerButtonArray[column][row].setPreferredSize(new Dimension(squareSize, squareSize));
                playerButtonArray[column][row].setMaximumSize(new Dimension(squareSize, squareSize));

                int thisColumn = column;
                int thisRow = row;

                // Show ship placed
                playerButtonArray[column][row].addActionListener(e -> {
                    int[] clickedLocation = new int[2];
                    clickedLocation[0] = thisColumn;
                    clickedLocation[1] = thisRow;

                    PlaceShips newShip = new PlaceShips(clickedLocation);
                    newShip.placeUserShips(board, selectedShip, selectedOrientation);
                    changeButtonColor(board);
                });
                playerGridPanel.add(playerButtonArray[column][row]);
            }
        }
    }

    // This is the method to get the players board GUI to update using the values stored in playerBoard
    public void changeButtonColor(GameBoardArray board) {
        int column, row;
        for (column = 0; column < 10; column++) {
            for (row = 0; row < 10; row++) {
                if (board.array[column][row] >= 1) {
                    playerButtonArray[column][row].setBackground(Color.GREEN);
                }
                if (board.array[column][row] == 0) {
                    playerButtonArray[column][row].setBackground(null);
                }
                if (board.array[column][row] == -1) {
                    playerButtonArray[column][row].setBackground(Color.RED);
                }
                if (board.array[column][row] == -2) {
                    playerButtonArray[column][row].setBackground(Color.GRAY);
                }
            }
        }
    }
    // this method is just used to display letters onto GUI board buttons

    public static String asChar(int val) {
        String ans = "";
        switch (val) {
            case 0:
                ans = "A";
                break;
            case 1:
                ans = "B";
                break;
            case 2:
                ans = "C";
                break;
            case 3:
                ans = "D";
                break;
            case 4:
                ans = "E";
                break;
            case 5:
                ans = "F";
                break;
            case 6:
                ans = "G";
                break;
            case 7:
                ans = "H";
                break;
            case 8:
                ans = "I";
                break;
            case 9:
                ans = "J";
                break;
        }
        return ans;
    }

}
