package sjuan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectToServer {
	private Server server;
	private int port;


	public ConnectToServer(Server server, int port) {
		this.server = server;
		this.port = port;
		Thread thread = new Thread(new Connect());
		thread.start();
	}

	private class Connect implements Runnable {
		public void run() {
			ServerSocket serverSocket = null;
			Socket socket;
			try {
				serverSocket = new ServerSocket(port);
				while (true) {
					socket = serverSocket.accept();
					System.out.println("New client");
					server.newClient(new ServerConnection(server,socket));
				}
			} catch (IOException e1) {
				System.out.println(e1);
			}
			try {
				serverSocket.close();
			} catch (Exception e) {
			}
		}
	}
}
