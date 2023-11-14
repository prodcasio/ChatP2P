package chatbeta1.pkg0;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainBomber extends Thread {
    MainBomber() {
    }

    @Override
    public void run() {
        String ip = IP.ip;
        Bomber[] b = new Bomber[6500];
        int counter = 0;
        for (int i = 22; i < 25; i++) {
            for (int j = 1; j < 254; j++) {
                if (i == Integer.valueOf(ip.split("\\.")[2]) && j == Integer.valueOf(ip.split("\\.")[3]))
                    continue;
                b[counter] = new Bomber(ip.split("\\.")[0] + "." + ip.split("\\.")[1] + "." + i + "." + j, Username.username);
                b[counter].start();
                counter++;
            }
        }
        System.out.println("Richieste inviate.");
    }

}
