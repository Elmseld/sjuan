package sjuan;

public class Server {
	private Card[] playerCards;

	public Server(int port, Player player) {
		try {
			this.playerCards = player.getPlayerCardList();
			new ConnectToServer(this,port);

		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
		}
	}
	public void newClient(ServerConnection connection) {
		// om servern behöver lagra referens till klienterna
	}

	public synchronized void newRequest(ServerConnection connection, Request request) {
		connection.newResponse(new Response(request.getRequest(),playerCards));
	}

	public static void main(String[] args) {
		Player player1 = new Player();
		Deck deck = new Deck();
		Controller control = new Controller(player1, deck);
		control.dealCards(player1);
		player1.printCards();
		new Server(7766, player1);
	}
}