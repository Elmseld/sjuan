package sjuan;
/**
 * This class starts the game "Sjuan"
 * @author Tobbe
 *
 */
public class StartSjuan {
	// right now only for test
	public static void main (String[] args) {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Deck deck = new Deck();

		Controller control = new Controller(player1, player2, player3, player4, deck);
		control.Deal();
		player1.printCards();

	}
}
