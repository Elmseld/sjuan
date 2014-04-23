package sjuan;
/**
 * This class controlls the logic of the game "Sjuan"
 * @author Tobbe
 *
 */
public class Controller {
	private Player player1, player2, player3, player4;

	private Deck deck;

	/**
	 * Constructs a controller 
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param deck takes in a deck
	 */
	public Controller(Player player1, Deck deck) {
		this.player1 = player1;
//		this.player2 = player2;
//		this.player3 = player3;
//		this.player4 = player4;
		this.deck = deck;
	}
	/**
	 * This method deals the deck to all players
	 */
	public void Deal() { 
		while (deck.getAllCards()!=0) {
			player1.setPlayerCards(deck.dealCard());
//			player2.setPlayerCards(deck.dealCard());
//			player3.setPlayerCards(deck.dealCard());
//			player4.setPlayerCards(deck.dealCard());

		}
	}
	public void dealCards(Player player) {
		for(int i = 0; i < 10; i++) {
			player.setPlayerCards(deck.dealCard());
		}
	}
	public Player getPlayer1() {
		return player1;
	}
}