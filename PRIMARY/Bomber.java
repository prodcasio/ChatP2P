package chatbeta1.pkg0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Bomber extends Thread {

    String host;

    Bomber(String host) {
        this.host = host;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, 1235), 5000);
            if (socket.isConnected()) {
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println(IP.ip + "/" + Username.username);
                socket.close();
                System.out.println("Ho inviato le mie informazioni all'ip " + host);
            }
        } catch (Exception e) {
        }

    }
}
