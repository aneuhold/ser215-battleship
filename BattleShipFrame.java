import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
  
  private JLabel battleShipLogoLabel;
  
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
    loadPlayerGrid();
    gridsPanel.add(playerGridPanel);
    
    // Opponent Grid Panel
    loadOpponentGrid();
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
  private void loadBattleShipLogo() {
    logoPanel = new JPanel();
    BufferedImage battleShipImg = null;
    try {
      battleShipImg = ImageIO.read(getClass().getResource(BATTLESHIP_LOGO_FILE));
      battleShipLogoLabel = new JLabel(new ImageIcon(battleShipImg));
      logoPanel.add(battleShipLogoLabel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void loadGameStatus() {
    gameStatusPanel = new JPanel();
    // Game Status code
    gameStatusPanel.add(new JLabel("Game Status"));
    gameStatusPanel.setBorder(new TitledBorder(new EtchedBorder()));
  }
  
  private void loadGameOptions() {
    gameOptionsPanel = new JPanel();
    // Game Options code
    gameOptionsPanel.add(new JLabel("Game Options"));
    gameOptionsPanel.setBorder(new TitledBorder(new EtchedBorder()));
  }
  
  private void loadGameReadout() {
    gameReadoutPanel = new JPanel();
    // Game Readout code
    gameReadoutPanel.add(new JLabel("Game Readout"));
    gameReadoutPanel.setBorder(new TitledBorder(new EtchedBorder()));
  }
  
  private void loadPlayerGrid() {
    playerGridPanel = new JPanel();
    // Player Grid code
    playerGridPanel.add(new JLabel("Player Grid"));
    playerGridPanel.setBorder(new TitledBorder(new EtchedBorder()));
  }
  
  private void loadOpponentGrid() {
    opponentGridPanel = new JPanel();
    // Opponent Grid code
    opponentGridPanel.add(new JLabel("Opponent Grid"));
    opponentGridPanel.setBorder(new TitledBorder(new EtchedBorder()));
  }
}
