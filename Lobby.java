package sjuan;

import java.util.HashMap;

/**
 * this class handles if a player is ready to play
 * @author Tobbe
 *
 */
public class Lobby {
	private int client1, client2, client3, client4, gameID = 0;
	private HashMap <Integer, Integer> gameList = new HashMap <Integer, Integer>();

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
			gameID++;
			gameList.put(client1, gameID);
			gameList.put(client2, gameID);
			gameList.put(client3, gameID);
			gameList.put(client4, gameID);
			server.createGame(client1, client2, client3, client4, gameID);
			resetClients();
		}
	}

	private void resetClients() {
		client1=0;
		client2=0;
		client3=0;
		client4=0;
		gameID=0;
	}

	public int getGameID(int clientID) {
		return gameList.get(clientID);
	}
}
