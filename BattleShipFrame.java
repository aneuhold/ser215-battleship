
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BattleShipFrame extends JFrame {

    private final String BATTLESHIP_LOGO_FILE = "Battleship-Logo.png";
    private final int FRAME_HEIGHT = 550;
    private final int FRAME_WIDTH = 800;

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

    /**
     * Builds all components for the Battleship frame and makes it visible.
     */
    public void loadBattleshipFrame() {

        // Logo Panel
        loadBattleShipLogo();
        this.add(logoPanel, BorderLayout.NORTH);
        // Game Status
        loadGameStatus();
        this.add(gameStatusPanel, BorderLayout.EAST);
        // Game Options
        loadGameOptions();
        this.add(gameOptionsPanel, BorderLayout.WEST);
        // Center panel to hold grids and readout
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));
        this.add(centerPanel, BorderLayout.CENTER);
        gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(gridsPanel);

        // Player Grid Panel
        GameBoardArray playerBoard = new GameBoardArray();
        loadPlayerGrid();
        updatePlayerGUI(playerBoard);

        gridsPanel.add(playerGridPanel);

        // Opponent Grid Panel
        GameBoardArray opponentBoard = new GameBoardArray();
        loadOpponentGrid();
        PlaceShips ships = new PlaceShips(opponentBoard);
        ships.opponentShipPlacing();
        updateAiGUI(opponentBoard);
        gridsPanel.add(opponentGridPanel);

        // Game Readout
        loadGameReadout();
        centerPanel.add(gameReadoutPanel);
        gameReadoutPanel.add(ConsoleOutput.console);

        // Frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(true);
        this.setVisible(true);

        //creating game turn order
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
        if (turnOrder.gameStatus.equals("opponenets turn")) {
            //gameStatus.setText("players turn");
            ConsoleOutput.display("It's the Opponent's turn,\n"
                    + "they have fired on grid " + AI.getLastOpponentShot() + "on the Opponent Grid to the right.");
        }
        if (turnOrder.gameStatus.endsWith("players turn")) {
            //gameStatus.setText("opponenets turn");
            ConsoleOutput.display("It's your turn,\nPlace a ship"
                    + "by clicking a button on the Opponent Grid to the right.");
        }
        gameStatusPanel.add(gameStatus);
    }

    public void loadGameOptions() {
        int buttonWidth = 100;
        int buttonHeight = 20;
        gameOptionsPanel = new JPanel();
        gameOptionsPanel.setLayout(new BorderLayout());
        gameOptionsPanel.add(new JLabel("Game Options"));

        //orientation button
        JButton orientation = new JButton("Change Ship Orientation");
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

        });

        //battleship button
        JButton battleship = new JButton("BattleShip");
        battleship.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        battleship.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        battleship.addActionListener(e -> {
            selectedShip = "battleship";

        });

        // cruiser button
        JButton cruiser = new JButton("Cruiser");
        cruiser.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        cruiser.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        cruiser.addActionListener(e -> {
            selectedShip = "cruiser";

        });

        // submarine button
        JButton submarine = new JButton("Submarine");
        submarine.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        submarine.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        submarine.addActionListener(e -> {
            selectedShip = "submarine";

        });

        // destroyer button
        JButton destroyer = new JButton("Destroyer");
        destroyer.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        destroyer.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        destroyer.addActionListener(e -> {
            selectedShip = "destroyer";

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
        // Options
        JButton options = new JButton("Options");
        options.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        options.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        options.addActionListener(e -> {
            // Options Actions:
        });
        gameOptionsPanel.add(options, BorderLayout.SOUTH);
        gameOptionsPanel.setBorder(new TitledBorder(new EtchedBorder()));
    }

    public void loadGameReadout() {
        gameReadoutPanel = new JPanel();
        // Game Readout code
        gameReadoutPanel.add(new JLabel("Game Readout"));
        gameReadoutPanel.setBorder(new TitledBorder(new EtchedBorder()));
    }

    public void loadPlayerGrid() {
        playerGridPanel = new JPanel();
        // Player Grid code
        playerGridPanel.add(new JLabel("Player Grid"));
        playerGridPanel.setBorder(new TitledBorder(new EtchedBorder()));
    }

    public void loadOpponentGrid() {
        opponentGridPanel = new JPanel();
        // Opponent Grid code
        opponentGridPanel.add(new JLabel("Opponent Grid"));
        opponentGridPanel.setBorder(new TitledBorder(new EtchedBorder()));

    }

    // call this to update opponenent GUI after event
    public void updateAiGUI(GameBoardArray board) {
        opponentGridPanel = new JPanel();
        GridLayout layout = new GridLayout(0, 10);
        int squareSize = 30;
        opponentGridPanel.setLayout(layout);
        // Opponent Grid code

        turnOrder.switchTurns(turnOrder);
        int column, row;
        for (column = 0; column < 10; column++) {
            for (row = 0; row < 10; row++) {
                String boardPOS = String.format("%s%s", asChar(column), row + 1);
                JButton square = new JButton(boardPOS);
                square.setPreferredSize(new Dimension(squareSize, squareSize));
                square.setMaximumSize(new Dimension(squareSize, squareSize));

                int thisColumn = column;
                int thisRow = row;

                square.addActionListener(e -> {
                    // square Actions:

                    if (turnOrder.gameStatus.equals("players turn")) {
                        String msg = boardPOS + " Pressed\n";
                        if (board.array[thisColumn][thisRow] == 0) {
                            msg = String.format("%s was a miss!\n", boardPOS);
                            square.setBackground(Color.RED);
                            square.setEnabled(false);
                        } else if (board.array[thisColumn][thisRow] > 0) {
                            msg = String.format("%s was a hit!\n", boardPOS);
                            square.setBackground(Color.GREEN);
                            square.setEnabled(false);
                        }
                        updateGameStatus(turnOrder);
                        updateAiGUI(board);
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

            }

        }
    }

    /*
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
                JButton square = new JButton(boardPOS);
                square.setPreferredSize(new Dimension(squareSize, squareSize));
                square.setMaximumSize(new Dimension(squareSize, squareSize));

                int thisColumn = column;
                int thisRow = row;

                // Show ship placed
                square.addActionListener(e -> {
                    int[] clickedLocation = new int[2];
                    clickedLocation[0] = thisColumn;
                    clickedLocation[1] = thisRow;

                    PlaceShips newShip = new PlaceShips(clickedLocation);
                    newShip.placeUserShips(board);
                    System.out.println(thisColumn + " Column");
                    System.out.println(thisRow + " Row");
                    System.out.println(board.array[thisColumn][thisRow]);

                    //  square.revaidate();
                    square.repaint();
                    updatePlayerGUI(board);

                });

                // 0 means no shots attempted at this loaction, -1 means hit, -2 means miss,
                // refresh board if location was a hit
                if (board.array[thisColumn][thisRow] == -1) {
                    square.setBackground(Color.RED);
                    square.setEnabled(false);
                }

                // refresh board if location was a miss
                if (board.array[thisColumn][thisRow] == -2) {
                    square.setBackground(Color.BLUE);
                    square.setEnabled(false);
                }

                // Show ship placed
                if (board.array[thisColumn][thisRow] >= 1) {


                    square.setBackground(Color.GREEN);
                    square.setOpaque(true);
                    square.setEnabled(false);
                    square.setBorderPainted(false);
                    System.out.println("WHY DID I NOT TURN GREEN BACKUP");

                }
                playerGridPanel.revalidate();
                playerGridPanel.repaint();
                playerGridPanel.remove(square);
                playerGridPanel.add(square);

            }
        }
    } */
    // this method is just used to display letters onto GUI board buttons
    private String asChar(int val) {
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

    /*private int getBtnRow(String text) {
        String storedValue = "";
        if (text.length() == 2) {
            storedValue = text.substring(text.length() - 1, text.length());
        } else if (text.length() == 3) {
            storedValue = text.substring(text.length() - 2, text.length());
        }
        int ans = Integer.parseInt(storedValue) - 1; // start counting from 0 instead of 1
        return ans;
    }

    //this method is just used to display GUI button numbers
    private int getBtnColumn(String text) {
        String firstChar = text.substring(0, 1);
        int ans = 0;
        switch (firstChar) {
            case "A": ans = 0; break;
            case "B": ans = 1; break;
            case "C": ans = 2; break;
            case "D": ans = 3; break;
            case "E": ans = 4; break;
            case "F": ans = 5; break;
            case "G": ans = 6; break;
            case "H": ans = 7; break;
            case "I": ans = 8; break;
            case "J": ans = 9; break;
        }
        return ans;
    }*/
}