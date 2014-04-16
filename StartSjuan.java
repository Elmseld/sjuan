package sjuan;
/**
 * This class starts the game "Sjuan"
 * @author Tobbe
 *
 */
public class StartSjuan {

//	public StartSjuan() {
//		Player player1 = new Player();
//		Player player2 = new Player();
//		Player player3 = new Player();
//		Player player4 = new Player();
//		Deck deck = new Deck();
//
//		Controller control = new Controller(player1, player2, player3, player4, deck);
//		control.Deal();
		//		System.out.println("Player1: ");
		//		player1.printCards();
		//		System.out.println("\nPlayer2: ");
		//		player2.printCards();
		//		System.out.println("\nPlayer3: ");
		//		player3.printCards();
		//		System.out.println("\nPlayer4: ");
		//		player4.printCards();
		//		System.out.println("\n");
		//	}
		// right now only for test
//	}
	public static void main (String[] args) {
		Player player1 = new Player();
//		Player player2 = new Player();
//		Player player3 = new Player();
//		Player player4 = new Player();
		Deck deck = new Deck();

		Controller control = new Controller(player1, deck);
		control.Deal();
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

		//		String [] cards;
		//		cards = player1.getPlayerCardsToString();
		//		for (int i = 0; i < cards.length; i++) {
		//			System.out.println(cards[i]);
		//		}

	}
}
