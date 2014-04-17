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
		//		Player player2 = new Player();
		//		Player player3 = new Player();
		//		Player player4 = new Player();
		Deck deck = new Deck();

		Controller control = new Controller(player1, deck);
		//				control.Deal();
		//				System.out.println("Player1: ");
		//				player1.printCards();
		//				System.out.println("\nPlayer2: ");
		//				player2.printCards();
		//				System.out.println("\nPlayer3: ");
		//				player3.printCards();
		//				System.out.println("\nPlayer4: ");
		//				player4.printCards();
		//				System.out.println("\n");
		//			}
		control.dealCards(player1);
		String [] cards;
		cards = player1.getPlayerCardsToString();
		for (int i = 0; i < cards.length; i++) {
			if (i!=cards.length-1)
				System.out.print(cards[i] + ",");
			else
				System.out.print(cards[i]);
		}


	}
}
