package sjuan;

public class Server {
	private Player player1, player2, player3, player4;



	public Server(int port, Player player1, Player player2, Player player3, Player player4, Controller control) {
		try {
			control.Deal();
			this.player1 = player1;
			this.player2 = player2;
			this.player3 = player3;
			this.player4 = player4;

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
		connection.newResponse(new Response(request.getRequest(),player1.getPlayerCardList()));
		connection.newResponse(new Response(request.getRequest(),player2.getPlayerCardList()));
		connection.newResponse(new Response(request.getRequest(),player3.getPlayerCardList()));
		connection.newResponse(new Response(request.getRequest(),player4.getPlayerCardList()));


	}

	//	public static void main(String[] args) {
	//		Player player1 = new Player();
	//		Deck deck = new Deck();
	//		Controller control = new Controller(player1, deck);
	//		control.dealCards(player1);
	//		player1.printCards();
	//		new Server(7766, player1);
	//	}
}