package chatbeta1.pkg0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRemoto extends Thread {

    Socket socket;

    UserRemoto(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(UserRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                String risposta = br.readLine();
                if(risposta != null){
                    System.out.println(risposta);
                    myPanel.chatOutput.setText(myPanel.chatOutput.getText() + "\n" + risposta);
                }
            } catch (IOException ex) {
                break;
            }
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UserRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
