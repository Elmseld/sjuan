package sjuan;
/**
 * This class control the logic of the game "Sjuan"
 * @author Tobbe
 *
 */
public class Controller {

	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();
	private Player player4 = new Player();
	private Deck deck = new Deck();
	private int clientID;

	private Server server;


	/**
	 * Constructs a controller 
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param deck takes in a deck
	 */
	public Controller() {
		server = new Server(7766, player1, player2, player3, player4, this);

	}
	/**
	 * This method deals the deck to all players
	 */

	public void Deal() { 
		while (deck.getAllCards()!=0) {
			player1.setPlayerCards(deck.dealCard());
			if (deck.getAllCards()>0)
				player2.setPlayerCards(deck.dealCard());
			if (deck.getAllCards()>0)
				player3.setPlayerCards(deck.dealCard());
			if (deck.getAllCards()>0)
				player4.setPlayerCards(deck.dealCard());

		}
	}
//	public void NewRequest(ServerConnection connection, Request request) {
//		if (request.getRequest().equals("new")) {
//			clientID = server.getClientID();
//			if (clientID==1)
//				connection.newResponse(new Response(player1.getPlayerCardList(),
//						player2.getPlayerCardSize(),
//						player3.getPlayerCardSize(),
//						player4.getPlayerCardSize(), "new", clientID));
//			else if (clientID==2)
//				connection.newResponse(new Response(player2.getPlayerCardList(),
//						player3.getPlayerCardSize(),
//						player4.getPlayerCardSize(),
//						player1.getPlayerCardSize(), "new", clientID));
//			else if (clientID==3)
//				connection.newResponse(new Response(player3.getPlayerCardList(),
//						player4.getPlayerCardSize(),
//						player1.getPlayerCardSize(),
//						player2.getPlayerCardSize(), "new", clientID));
//			else if (clientID==4)
//				connection.newResponse(new Response(player4.getPlayerCardList(),
//						player1.getPlayerCardSize(),
//						player2.getPlayerCardSize(),
//						player3.getPlayerCardSize(), "new", clientID));
//			else 
//				System.out.println("clientID stämmer inte");
//		}
//		else if(request.getRequest().equals("pass")) {
//			connection.newResponse(new Response("pass"));
//		}	

		//	public void dealCards(Player player) {
		//		for(int i = 0; i < 10; i++) {
		//			player.setPlayerCards(deck.dealCard());
		//		}
		//	}

//	}
}