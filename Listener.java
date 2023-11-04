package chatbeta1.pkg0;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener extends Thread{
    private static ServerSocket serverSocket;
    
    private Socket socket;
    Listener(ServerSocket serverSocket, Socket socket){
        this.serverSocket = serverSocket;
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try {
            this.socket = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
