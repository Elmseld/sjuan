package sjuan;
/**
 * This class controlls the logic of the game "Sjuan"
 * @author Tobbe
 *
 */
public class Controller {

	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();
	private Player player4 = new Player();
	private Deck deck = new Deck();


	/**
	 * Constructs a controller 
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param deck takes in a deck
	 */
	public Controller() {
		new Server(7766,player1, player2, player3,player4, this);
	}
	/**
	 * This method deals the deck to all players
	 */
	public void Deal() { 
		while (deck.getAllCards()!=0) {
			player1.setPlayerCards(deck.dealCard());
			player2.setPlayerCards(deck.dealCard());
			player3.setPlayerCards(deck.dealCard());
			player4.setPlayerCards(deck.dealCard());

		}
	}
	public void dealCards(Player player) {
		for(int i = 0; i < 10; i++) {
			player.setPlayerCards(deck.dealCard());
		}
	}
}