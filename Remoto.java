package chatbeta1.pkg0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Remoto extends Thread {
    private static ServerSocket serverSocket = null;

    Remoto() {
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException ex) {
            Logger.getLogger(Remoto.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            if(!serverSocket.isClosed()){
                try {
                    Socket socket = serverSocket.accept();
                    UserRemoto userRemoto = new UserRemoto(socket);
                    userRemoto.start();
                } catch (IOException ex) {
                    Logger.getLogger(Remoto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    
    public void closeConnection() throws IOException{
        serverSocket.close();
    }
}
