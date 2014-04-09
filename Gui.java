package sjuan;

import java.awt.*;
import javax.swing.*;
/**
 * This class generates a Grafical User Interface to play the game
 */
public class Gui extends JPanel{
	private JPanel panel = new JPanel();
	private JFrame gameFrame = new JFrame("Sjuan");
	private JPanel playerPanel1 = new JPanel();
	private JPanel opponent1Panel = new JPanel();
	private JPanel opponent2Panel = new JPanel();
	private JPanel opponent3Panel = new JPanel();
	private JLabel pl1Card = new JLabel();
	/**
	 * Constructs the Gui
	 */
	public Gui() {
		GamePanel();
	}
	/**
	 * This Method creates the Gui Frame
	 */
	public void GamePanel(){
		gameFrame.setBounds(40, 200, 1000, 600);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new BorderLayout());
		gameFrame.add(panel(), BorderLayout.CENTER);
		gameFrame.add(player1Panel(), BorderLayout.SOUTH);
		gameFrame.add(opponent1Panel(), BorderLayout.WEST);
		gameFrame.add(opponent2Panel(), BorderLayout.NORTH);
		gameFrame.add(opponent3Panel(), BorderLayout.EAST);
		gameFrame.setVisible(true);
	}
	/**
	 * This method returns a panel
	 * @return panel returns a panel
	 */
	public JPanel panel() {
		panel.setPreferredSize(new Dimension(400, 100));
		panel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.setBackground(Color.GREEN.darker().darker());
		return panel;
	}
	/**
	 * This method returns a panel to represent the actual player panel
	 * @return player1Panel return the actual player panel
	 */
	public JPanel player1Panel() {
		playerPanel1.setPreferredSize(new Dimension(200, 100));
		playerPanel1.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
		playerPanel1.setBackground(Color.black);
		pl1Card.setIcon(readFiles());
		playerPanel1.add(pl1Card);
		return playerPanel1;
	}
	/**
	 * This method returns a panel to represent the opponent1
	 * @return opponent1Panel return a panel of opponent1
	 */
	public JPanel opponent1Panel() {
		opponent1Panel.setPreferredSize(new Dimension(100, 200));
		opponent1Panel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
		opponent1Panel.setBackground(Color.blue.darker());
		return opponent1Panel;
	}
	/**
	 * This method returns a panel to represent the opponent2
	 * @return opponent1Panel return a panel of opponent2
	 */
	public JPanel opponent2Panel() {
		opponent2Panel.setPreferredSize(new Dimension(200, 100));
		opponent2Panel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
		opponent2Panel.setBackground(Color.red);
		return opponent2Panel;
	}
	/**
	 * This method returns a panel to represent the opponent3
	 * @return opponent1Panel return a panel of opponent3
	 */
	public JPanel opponent3Panel() {
		opponent3Panel.setPreferredSize(new Dimension(100, 200));
		opponent3Panel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
		opponent3Panel.setBackground(Color.YELLOW);
		return opponent3Panel;
	}
	/**
	 * This metod reads a picture file and return it as a Icon Object
	 * @return icon returns a Icon Object
	 */
	public ImageIcon readFiles() {
		ImageIcon icon = new ImageIcon("src/sjuan/files/cards_png/ck.png");
		return icon;
	}
	/**
	 * Main method to test Gui-class
	 * @param args
	 */
	public static void main(String[] args) {
		Gui gui = new Gui();
	}
}
