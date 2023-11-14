package chatbeta1.pkg0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Retriever extends Thread {

    Retriever() {
    }

    @Override
    public void run() {
        try {
            while (true) {
                ServerSocket ss = new ServerSocket(1235);
                System.out.println("Sto ascoltando la richiesta");
                Socket s = ss.accept();
                System.out.println("Socket accettato.");
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String request = br.readLine();
                ListaUtenti.lista.add(request);
                ss.close();
                s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Land.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
