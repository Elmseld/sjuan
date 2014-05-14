package sjuan;

public class Lobby {
	private int client1, client2, client3, client4;

	public void waitingRoom(int clientID, Server server) {
		if (client1 == 0 || client1 == clientID) {
			client1 = clientID;
			System.out.println("client1 är redo");
		}
		else if (client2 == 0 || client2 == clientID) {
			client2 = clientID;
			System.out.println("client2 är redo");
		}
		else if (client3 == 0 || client3 == clientID) {
			client3 = clientID;
			System.out.println("client3 är redo");
		}
		else if (client4 == 0 || client4 == clientID) {
			client4 = clientID;
			System.out.println("client4 är redo");
			server.createGame(client1, client2, client3, client4);
			resetClients();

		}
	}

	private void resetClients() {
		client1=0;
		client2=0;
		client3=0;
		client4=0;
	}
}
