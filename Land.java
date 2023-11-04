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

public class Land extends Thread {

    Land() {
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
                String ipReceived = request.split("/")[0];
                System.out.println(request);

                
                if (request.charAt(0) == '!') {
                    System.out.println("Ho ricevuta una richiesta di disconnessione");
                    request = request.replace("!", "");
                    int len = UserList.userList.size();
                    for (int i = 0; i < len; i++) {
                        if (UserList.userList.get(i).get(0).equals(ipReceived)) {
                            myPanel.rimuoviUser(i);
                            myPanel.chatOutput.setText(myPanel.chatOutput.getText() + "\n" + UserList.userList.get(i).get(1) + " si è disconnesso.");
                            UserList.userList.remove(i);
                            myPanel.aggiornaUserDisponibili();
                            break;
                        }
                    }
                    s.close();
                    ss.close();
                    continue;
                }
                boolean flag = false;
                for (int i = 0; i < UserList.userList.size(); i++) {
                    System.out.println("L'ip " + ipReceived + " è gia' stato trovato.");
                    if (UserList.userList.get(i).get(0).equals(ipReceived)) {
                        flag = true;
                    }
                }
                if (flag) {
                    s.close();
                    ss.close();
                    continue;
                }
                
                UserList.addUser(request);
                System.out.println("Reperito un ip: " + request);

                Socket s1 = new Socket(ipReceived,1235);
                OutputStream os = s1.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println(IP.ip + "/" + Bomber.username);
                myPanel.aggiornaUserDisponibili();
                s.close();
                s1.close();
                ss.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Land.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
