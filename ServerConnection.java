package sjuan;

import java.io.*;
import java.net.*;

public class ServerConnection {
    private Server server;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    public ServerConnection(Server server, Socket socket) throws IOException {
        this.server = server;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        Thread thread = new Thread(new RequestHandler());
        thread.start();
    }
    
    public void newResponse(Response response) {
        try {
            output.writeObject(response);
            output.flush();
        }catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private class RequestHandler implements Runnable {
        public void run() {
            Request request;
            try {
                while (true) {
                    request = (Request)input.readObject();
                    server.newRequest(ServerConnection.this, request);
                 }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
    }
}
